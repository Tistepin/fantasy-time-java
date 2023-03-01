package com.xu.search.service.Impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.WildcardQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.alibaba.fastjson.JSON;
import com.xu.common.TO.es.WorksEsModel;
import com.xu.search.constant.EsConstant;
import com.xu.search.service.MallSearchService;
import com.xu.search.vo.SearchParam;
import com.xu.search.vo.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 徐国纪
 * @createTime: 2020-06-13 14:19
 **/

@Slf4j
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private ElasticsearchClient client;

    @Override
    public Boolean WorksUp(WorksEsModel worksEsModels) throws IOException {
        // 保存到Es
        // 建立索引 product 建立映射关系
        BulkOperation product = new BulkOperation.Builder().create(d -> d.document(worksEsModels).id(worksEsModels.getWorksId().toString()).index(EsConstant.WORKS_INDEX)).build();

        BulkResponse bulk = client.bulk(e -> e.index(EsConstant.WORKS_INDEX).operations(product));
        boolean errors = bulk.errors();
        List<BulkResponseItem> items = bulk.items();
        for (BulkResponseItem item : items) {
            if (item.error() == null) {
                log.info("存储完成打印id{}",
                        item.id());
            } else {
                log.error("存儲錯誤打印id{},错误信息{}",
                        item.id(), item.error().reason());
            }
        }
        return errors;
    }

    @Override
    public SearchResult search(SearchParam searchParam) {

        SearchResult searchResult = new SearchResult();
        // 构建查询条件
        SearchRequest builder=buildSearchRequrest(searchParam);
        SearchResponse<HashMap> search;
        try {
            search = client.search(builder, HashMap.class);
            searchResult = buildSearchResult(search, searchParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    @Override
    public void UpdateEs(WorksEsModel worksEsModels) throws IOException {
        UpdateRequest.Builder<Object, Object> defaultImage = new UpdateRequest.Builder<>().index(EsConstant.WORKS_INDEX);
        UpdateRequest<Object, Object> build = defaultImage.id(String.valueOf(1))
                .doc(worksEsModels)
                .build();
        UpdateResponse<Object> update = client.update(build, Object.class);
    }


    private SearchResult buildSearchResult(SearchResponse<HashMap> search, SearchParam searchParam) {
        SearchResult searchResult = new SearchResult();
        HitsMetadata<HashMap> hits = search.hits();
        /**
         * 1查询到的所有作品信息
         */
        if (!hits.hits().isEmpty()) {
            List<WorksEsModel> skuEsModels = new ArrayList<>();
            for (Hit<HashMap> hit : hits.hits()) {
                WorksEsModel worksEsModel = JSON.parseObject(JSON.toJSONString(hit.source()), WorksEsModel.class);
                skuEsModels.add(worksEsModel);
            }
            searchResult.setWorks(skuEsModels);
        }
        //当前页码
        searchResult.setPageNum(searchParam.getPageNum());
        /**
         * 总记录数
         */
        long totle = hits.total().value();
        searchResult.setTotal(totle);
        /**
         * 总页码
         */
        long TotalPages = totle == 0 ?
                totle : totle / searchParam.getPageSize() == 0 ?
                ((totle / searchParam.getPageSize()) + 1 ): totle % searchParam.getPageSize()==0?
                totle  / searchParam.getPageSize():(totle % searchParam.getPageSize())+1;
        searchResult.setTotalPages((int) TotalPages);
        return searchResult;
    }

    /**
     * 构建es搜索请求
     * @param searchParam
     * @return
     */
    private SearchRequest buildSearchRequrest(SearchParam searchParam) {
        SearchRequest.Builder builder = new SearchRequest.Builder().index(EsConstant.WORKS_INDEX);
        // region 查询
        /**
         * 1.1模糊匹配 按照搜索栏输入的內容 匹配 worksName
         */
        BoolQuery.Builder BoolQueryBuilder = new BoolQuery.Builder();
        String keyword = searchParam.getWorksName();
        if (!StringUtils.isEmpty(keyword)) {
            BoolQueryBuilder.must(
                    m -> m.match(match -> match.field("worksName").query(keyword))
            );
        }
        /**
         * 1.2 模糊匹配  查询含有这个题材的作品 题材可能含有多个
         */
        String worksCategory = searchParam.getWorksCategory();
        if (!StringUtils.isEmpty(worksCategory)&& !worksCategory.equals("1")) {
            String[] split = worksCategory.split(",");
            List<Query> wildcardQuerys=new ArrayList<>();
            for (String s : split) {
                WildcardQuery.Builder wildcard = new WildcardQuery.Builder();
                wildcard.field("worksCategory");
                wildcard.value("*"+s+"*");
                Query query = new Query(wildcard.build());
                wildcardQuerys.add(query);;
            }

            BoolQueryBuilder.must(wildcardQuerys);
        }

        /**
         * 1.3 精确匹配WorksType
         */
        Integer worksType = searchParam.getWorksType();
        if (!StringUtils.isEmpty(worksType)) {
            BoolQueryBuilder.must(
                    m -> m.match(match -> match.field("worksType").query(worksType))
            );
        }

        /**
         * 1.4 精确匹配地区
         */
        Integer worksArea = searchParam.getWorksArea();
        if (!StringUtils.isEmpty(worksArea)&&worksArea!=1) {
            BoolQueryBuilder.must(
                    m -> m.match(match -> match.field("worksArea").query(worksArea))
            );
        }
        /**
         * 1.5 精确匹配是否已完结
         */
        Integer worksStatus = searchParam.getWorksStatus();
        if (!StringUtils.isEmpty(worksStatus)&&worksStatus!=0) {
            BoolQueryBuilder.must(
                    m -> m.match(match -> match.field("worksStatus").query(worksStatus))
            );
        }
        /**
         * 排序  sort=评分最高/人气最旺/最近更新/最新发布
         */
        // 最新发布
        if (searchParam.isLatestRelease()) {
            FieldSort fieldSort = new FieldSort.Builder().field("createTime").order(SortOrder.Desc).build();
            builder.sort(new SortOptions.Builder().field(fieldSort).build());
        }
        // 最近更新
        if (searchParam.isRecentlyUpdated()) {
            FieldSort fieldSort = new FieldSort.Builder().field("editTime").order(SortOrder.Desc).build();
            builder.sort(new SortOptions.Builder().field(fieldSort).build());
        }
        // 人气最旺
        if (searchParam.isTheMostPopular()) {
            FieldSort fieldSort = new FieldSort.Builder().field("worksPopularity").order(SortOrder.Desc).build();
            builder.sort(new SortOptions.Builder().field(fieldSort).build());
        }
        // 评分最高
        if (searchParam.isHighestRated()) {
            FieldSort fieldSort = new FieldSort.Builder().field("worksScore").order(SortOrder.Desc).build();
            builder.sort(new SortOptions.Builder().field(fieldSort).build());
        }
        builder.query(new Query.Builder().bool(BoolQueryBuilder.build()).build());

        builder.from((searchParam.getPageNum() - 1) * searchParam.getPageSize());
        builder.size(searchParam.getPageSize());
        return builder.build();
    }
//
////    @Autowired
////    private ProductFeignService productFeignService;
//
//    /**
//     * 检索的结果
//     *
//     * @param searchParam 检索的参数
//     * @returnhasStock
//     */
//    @Override
//    public SearchResult search(SearchParam searchParam) {
//        SearchResult searchResult = new SearchResult();
//        SearchRequest builder = buildSearchRequrest(searchParam);
//        SearchResponse<HashMap> search;
//        try {
//            search = elasticsearchClient.search(builder, HashMap.class);
//            searchResult = buildSearchResult(search, searchParam);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return searchResult;
//    }
//
//    /**
//     * 转换为vo数据
//     *
//     * @param search
//     * @return
//     */
//    private SearchResult buildSearchResult(SearchResponse<HashMap> search, SearchParam searchParam) {
//
//        SearchResult searchResult = new SearchResult();
//        HitsMetadata<HashMap> hits = search.hits();
//        /**
//         * 1查询到的所有商品信息
//         */
//        if (!hits.hits().isEmpty()) {
//            List<SkuEsModel> skuEsModels = new ArrayList<>();
//            for (Hit<HashMap> hit : hits.hits()) {
//                SkuEsModel skuEsModel = JSON.parseObject(JSON.toJSONString(hit.source()), SkuEsModel.class);
//                skuEsModels.add(skuEsModel);
//                Map<String, List<String>> highlight = hit.highlight();
//                if (highlight != null && searchParam.getKeyword() != null) {
//                    String s = highlight.get("skuTitle").get(0);
//                    skuEsModel.setSkuTitle(s);
//                }
//            }
//            searchResult.setProduct(skuEsModels);
//        }
//        /**
//         * 2  聚合信息查询
//         */
//        /**
//         * 当前查询到的结果，所有涉及到的品牌
//         */
//        // 先获取全部的aggs
//        Map<String, Aggregate> aggregations = search.aggregations();
//        // 2.1 获取brand_agg的aggs
//        Aggregate brand_agg = aggregations.get("brand_agg");
//        // 2.1.1 获取buckets 的数组
//        List<LongTermsBucket> array = brand_agg.lterms().buckets().array();
//        ArrayList<SearchResult.BrandVo> brandVos = new ArrayList<>();
//        if (array != null && array.size() != 0) {
//            for (LongTermsBucket longTermsBucket : array) {
//                SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
//                // 获取 brandid
//                long BrandID = longTermsBucket.key();
//                brandVo.setBrandId(BrandID);
//                // 获取brand其他属性必须获取子聚合
//                Map<String, Aggregate> BrandAggs = longTermsBucket.aggregations();
//                // 获取BrandName 子集合的buckets获取必须在 sterms 里面  不是 lterms
//                String brandName = BrandAggs.get("brandName_agg").sterms().buckets().array().get(0).key();
//                brandVo.setBrandName(brandName);
//                // 获取brandimg
//                Aggregate brand_img_agg = BrandAggs.get("brand_img_agg");
//                String Brand_img = brand_img_agg.sterms().buckets().array().get(0).key();
//                brandVo.setBrandImg(Brand_img);
//                brandVos.add(brandVo);
//            }
//        }
//        searchResult.setBrands(brandVos);
//        /**
//         *  2.2  当前查询到的结果，所有涉及到的所有属性
//         */
//        // 获取属性聚合
//        Aggregate attr_agg = aggregations.get("attr_agg");
//        // 创建集合 存储属性数据
//        ArrayList<SearchResult.AttrVo> attrVos = new ArrayList<>();
//        // 循环 属性数据
//        List<LongTermsBucket> attrBuckets = ((NestedAggregate) attr_agg._get()).aggregations().get("attr_id_agg").lterms().buckets().array();
//        if (attrBuckets != null && attrBuckets.size() != 0) {
//            for (LongTermsBucket longTermsBucket : attrBuckets) {
//                // 创建属性vo
//                SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
//                // 获取属性ID
//                attrVo.setAttrId(longTermsBucket.key());
//                // 获取该属性的子聚合 (该属性的其他数据)
//                Map<String, Aggregate> attr_vo_2 = longTermsBucket.aggregations();
//                // 获取该属性的 name 和value
//                String attr_name = attr_vo_2.get("attr_name_agg").sterms().buckets().array().get(0).key();
//                attrVo.setAttrName(attr_name);
//                List<String> attr_values = attr_vo_2.get("attr_value_agg").sterms().buckets().array().stream().map(item -> item.key()).collect(Collectors.toList());
//                attrVo.setAttrValue(attr_values);
//                attrVos.add(attrVo);
////                longTermsBucket.
//            }
//        }
//        searchResult.setAttrs(attrVos);
//        /**
//         * 2.当前查询到的结果，所有涉及到的所有分类
//         */
//        // 获取分类聚合
//        Aggregate catalog_agg = aggregations.get("catalog_agg");
//        // 创建集合 存储分类数据
//        ArrayList<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
//        // 循环 分类数据
//        List<LongTermsBucket> catalogBuckets = catalog_agg.lterms().buckets().array();
//        if (catalogBuckets != null && catalogBuckets.size() != 0) {
//            for (LongTermsBucket longTermsBucket : catalogBuckets) {
//                // 创建分类vo
//                SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
//                // 获取分类ID
//                catalogVo.setCatalogId(longTermsBucket.key());
//                String catalog_name = longTermsBucket.aggregations().get("catalog_name_agg").sterms().buckets().array().get(0).key();
//                catalogVo.setCatalogName(catalog_name);
//                catalogVos.add(catalogVo);
//            }
//        }
//        searchResult.setCatalogs(catalogVos);
//        //当前页码
//        searchResult.setPageNum(searchParam.getPageNum());
//        /**
//         * 总记录数
//         */
//        long totle = hits.total().value();
//        searchResult.setTotal(totle);
//        /**
//         * 总页码
//         */
//        long TotalPages = totle == 0 ? totle : totle / EsConstant.WORKS_PAGE_SIZE == 0 ? (totle / EsConstant.WORKS_PAGE_SIZE) + 1 : (totle / EsConstant.WORKS_PAGE_SIZE);
//        searchResult.setTotalPages((int) TotalPages);
//
//        /**
//         * 构建面包屑导航
//         */
//        if (searchParam.getAttrs() != null && searchParam.getAttrs().size() != 0) {
//            List<SearchResult.NavVo> navVos = searchParam.getAttrs().stream().map(item -> {
//                SearchResult.NavVo navVo = new SearchResult.NavVo();
//                // 分割属性 3_2019:2018
//                String[] s = item.split("_");
//
//                searchResult.getAttrIds().add(Long.valueOf(s[0]));
//                navVo.setNavValue(s[1]);
////                R info = productFeignService.info(Long.valueOf(s[0]));
////                if (info.GetCode() == 0) {
////
////                    AttrResponseVo attr = info.getData("attr", new TypeReference<AttrResponseVo>() {
////                    });
////                    navVo.setNavName(attr.getAttrName());
////                } else {
////                    navVo.setNavName(s[0]);
////                }
//                String replace = searchParam.get_queryString().replace("&attrs=" + item, "");
//                navVo.setLink("http://localhost/search/list.html?" + replace);
//                return navVo;
//            }).collect(Collectors.toList());
//            searchResult.setNavs(navVos);
//        }
//
//        // 品牌或者分类也上面包屑
//        // 品牌
//
//        return searchResult;
//    }
//
//    /**
//     * 准备检索请求
//     *
//     * @return
//     */
//    private SearchRequest buildSearchRequrest(SearchParam searchParam) {
//        SearchRequest.Builder builder = new SearchRequest.Builder().index(EsConstant.WORKS_INDEX);
//        // region 查询
//        /**
//         * 1 查询  过滤 按照,,,,
//         */
//        /**
//         * 1.1模糊匹配
//         */
//        BoolQuery.Builder BoolQueryBuilder = new BoolQuery.Builder();
//        String keyword = searchParam.getKeyword();
//        if (!StringUtils.isEmpty(keyword)) {
//            BoolQueryBuilder.must(
//                    m -> m.match(match -> match.field("skuTitle").query(keyword))
//            );
//        }
//
//        /**
//         * 1.2分类
//         */
//        if (searchParam.getCatalog3Id() != null) {
//            BoolQueryBuilder.filter(
//                    f -> f.term(term -> term.field("catalogId").value(searchParam.getCatalog3Id()))
//            );
//        }
//        /**
//         * 1.3品牌
//         */
//        if (searchParam.getBrandId() != null && searchParam.getBrandId().size() > 0) {
//            ArrayList<FieldValue> fieldValues = new ArrayList<>();
//            for (Long aLong : searchParam.getBrandId()) {
//                FieldValue build = new FieldValue.Builder().longValue(aLong).build();
//                fieldValues.add(build);
//            }
//            BoolQueryBuilder.filter(
//                    f -> f.terms(terms -> terms.field("brandId").terms(tqf -> tqf.value(fieldValues)))
//            );
//        }
//        /**
//         * 1.4属性 每个属性都需要一个 nested 查询 里面 有 term 和 terms  对应 的id和value值
//         */
//        if (searchParam.getAttrs() != null && searchParam.getAttrs().size() != 0) {
//            ArrayList<FieldValue> AttrsFieldValues = new ArrayList<>();
//            for (String item : searchParam.getAttrs()) {
//                Query.Builder attrbuilder = new Query.Builder();
//                //"1_5寸:7寸";
//                // 第一个是属性ID
//                String[] s2 = item.split("_");
//                // 存储terms需要的vlues值的集合
//                String[] AttrValue = s2[1].split(":");
//                List<FieldValue> collect = Arrays.stream(AttrValue).map(
//                        data -> new FieldValue.Builder().stringValue(data).build()
//                ).collect(Collectors.toList());
//                Query build = attrbuilder.bool(
//                        b -> b.must(
//                                m -> m.term(t -> t.field("attrs.attrId").value(s2[0]))
//
//                        ).must(
//                                M -> M.terms(t -> t.field("attrs.attrValue").terms(terms -> terms.value(collect)))
//                        )
//                ).build();
//                NestedQuery nestedQuery = new NestedQuery.Builder().path("attrs")
//                        .query(q -> q.bool(b -> b.must(build))).build();
//                BoolQueryBuilder.filter(
//                        f -> f.nested(nestedQuery)
//                );
//            }
//        }
//
//        /**
//         * 1.5库存
//         */
//        Integer hasStock = searchParam.getHasStock();
//        if (hasStock != null) {
//            BoolQueryBuilder.filter(
//                    f -> f.term(
//                            term -> term.field("hasStock").value(hasStock == 1)
//                    ));
//        }
//        /**
//         * 价格区间
//         */
//        if (!StringUtils.isEmpty(searchParam.getSkuPrice())) {
//            String[] s = searchParam.getSkuPrice().split("_");
//            // 1_500 的结果是  1,500
//            RangeQuery.Builder rangeQueryBuilder = new RangeQuery.Builder()
//                    .field("skuPrice");
//            if (s.length == 2 && !StringUtils.isEmpty(s[0])) {
//                rangeQueryBuilder.gte(JsonData.of(s[0])).lte(JsonData.of(s[1]));
//            } else if (s.length == 2 && StringUtils.isEmpty(s[0])) {
//                rangeQueryBuilder.lte(JsonData.of(s[1]));
//            } else if (s.length == 1) {
//                rangeQueryBuilder.gte(JsonData.of(s[0]));
//            }
//            RangeQuery rangeQuery = rangeQueryBuilder.build();
//            BoolQueryBuilder.filter(f -> f.range(rangeQuery));
//        }
//
//        // endregion
//
//        // region  排序 分页  高亮
//        /**
//         * 排序  sort=price/salecount/hotscore_desc/asc
//         */
//        String sort = searchParam.getSort();
//        if (!StringUtils.isEmpty(sort)) {
//            // hotscore_des 以_分割 前面是字段 后面是升降序
//            String[] sort_value = sort.split("_");
//            SortOrder order = sort_value[1].equalsIgnoreCase("asc") ? SortOrder.Asc : SortOrder.Desc;
//            FieldSort fieldSort = new FieldSort.Builder().field(sort_value[0]).order(order).build();
//            builder.sort(new SortOptions.Builder().field(fieldSort).build());
//        }
//        /**
//         * 分页
//         * pagenum 1 from 0  size 5  [0,1,2,3,4]
//         * pagenum 2 from 5  size 5   [5,6,7,8,9]
//         * pagenum 3 from 10  size 5   [10,11,12,13,14]
//         */
//
//        builder.from((searchParam.getPageNum() - 1) * EsConstant.WORKS_PAGE_SIZE);
//        builder.size(EsConstant.WORKS_PAGE_SIZE);
//        /**
//         * 高亮 如果没有keyword高亮就没有意义
//         */
//        if (!StringUtils.isEmpty(searchParam.getKeyword())) {
//
//            Highlight.Builder skuTitle = new Highlight.Builder()
//                    .fields("skuTitle", T -> T.preTags("<b style='color: red'>")
//                            .postTags("</b>"));
//            builder.highlight(skuTitle.build());
//        }
//        builder.query(new Query.Builder().bool(BoolQueryBuilder.build()).build());
//        // endregion
////        String s = builder.toString();
////        System.out.println("构建的dsl"+s);
//        /**
//         * 聚合分析
//         */
//        Map<String, Aggregation> map = new HashMap<>();
//        // brand_agg 品牌聚合
//        Aggregation brandId_aggs = new Aggregation.Builder().terms(t -> t.field("brandId").size(100))
//                .aggregations(
//                        "brandName_agg", brandName_agg -> brandName_agg.terms(t -> t.field("brandName").size(1))
//                )
//                .aggregations(
//                        "brand_img_agg", brandName_agg -> brandName_agg.terms(t -> t.field("brandImg").size(1))
//                ).build();
//        // catalog_agg 分类聚合
//        Aggregation catalog_agg = new Aggregation.Builder().terms(t -> t.field("catalogId").size(100))
//                .aggregations(
//                        "catalog_name_agg", brandName_agg -> brandName_agg.terms(t -> t.field("catalogName").size(1))
//                ).build();
//        // attr_agg 属性聚合
//        Aggregation attr_agg = new Aggregation.Builder().nested(nest -> nest.path("attrs"))
//                .aggregations(
//                        "attr_id_agg", brandName_agg -> brandName_agg.terms(t -> t.field("attrs.attrId").size(100))
//                                .aggregations(
//                                        "attr_name_agg",
//                                        attr_name_agg -> attr_name_agg.terms(terms -> terms.field("attrs.attrName").size(1))
//                                )
//                                .aggregations("attr_value_agg",
//                                        attr_value_agg -> attr_value_agg.terms(attr_value_agg_terms -> attr_value_agg_terms.field("attrs.attrValue").size(100))
//                                )
//                ).build();
//        map.put("brand_agg", brandId_aggs);
//        map.put("catalog_agg", catalog_agg);
//        map.put("attr_agg", attr_agg);
//        builder.aggregations(map);
//        SearchRequest build = builder.build();
//        return build;
//    }
}
