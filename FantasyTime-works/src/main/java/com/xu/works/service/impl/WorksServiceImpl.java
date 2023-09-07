package com.xu.works.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.common.TO.es.WorksEsModel;
import com.xu.common.constant.systemEnum;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.common.utils.R;
import com.xu.security.utils.TokenManager;
import com.xu.works.constant.WorksEnum;
import com.xu.works.dao.WorksDao;
import com.xu.works.entity.*;
import com.xu.works.feign.SearchFeignService;
import com.xu.works.service.*;
import com.xu.works.to.CartoonWorksDetailsEntityTo;
import com.xu.works.to.ReviewWorksTo;
import com.xu.works.to.SaveBookToShelfTo;
import com.xu.works.to.WorksTo;
import com.xu.works.vo.DownListVo;
import com.xu.works.vo.WorksInfoVo;
import com.xu.works.vo.WorksVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


@Service("worksService")
public class WorksServiceImpl extends ServiceImpl<WorksDao, WorksEntity> implements WorksService {

    // region service
    /*人气表*/
    @Resource
    private PopularityService popularityService;
    /*分类表*/
    @Resource
    private CategoryService categoryService;
    /*历史观看表*/
    @Resource
    private WorksWatchHistoryService worksWatchHistoryService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;
    @Autowired
    private WorksUploadService worksUploadService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private WorksDefaultImageService worksDefaultImageService;
    // 漫画章节信息表
    @Autowired
    CartoonWorksDetailsService cartoonWorksDetailsService;
    // 书架表
    @Autowired
    WorksBookshelfService worksBookshelfService;
    // endregion
    // region feignService
    @Autowired
    SearchFeignService searchFeignService;
    // endregion

    @Autowired
    ThreadPoolExecutor executor;

    /**
     * @return com.xu.common.utils.PageUtils
     * @Description 最近发布 可以根据地区和题材的分页查询
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @Override
    public PageUtils LatestCreationqueryPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit) {
        IPage<WorksEntity> data = getData(worksType, area, worksCategory, page, limit, "works_create_time");
        return new PageUtils(data);
    }

    /**
     * @return com.xu.common.utils.PageUtils
     * @Description 最近更新 可以根据地区和题材的分页查询
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @Override
    public PageUtils getRecentlyUpdatedPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit) {
        IPage<WorksEntity> data = getData(worksType, area, worksCategory, page, limit, "edit_time");
        return new PageUtils(data);
    }

    /**
     * @return com.xu.common.utils.PageUtils
     * @Description 人气最高
     * @Author F3863479
     * @Date 2023/1/9 上午 10:51
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @Override
    public PageUtils getPopularPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit) {
        IPage<WorksEntity> data = getData(worksType, area, worksCategory, page, limit, "works_popularity");
        return new PageUtils(data);
    }

    /**
     * @return com.xu.common.utils.PageUtils
     * @Description 评分最高
     * @Author F3863479
     * @Date 2023/1/9 上午 10:53
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @Override
    public PageUtils getHighScorePage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit) {
        IPage<WorksEntity> data = getData(worksType, area, worksCategory, page, limit, "works_score");
        return new PageUtils(data);
    }

    /**
     * @return com.xu.search.entity.WorksEntity
     * @Description 作品详情信息
     * @Author F3863479
     * @Date 2023/1/9 上午 11:22
     * @Params [worksId]
     */
    @Override
    public WorksInfoVo getWorksInfo(Integer worksId) {
        WorksInfoVo worksInfoVos = this.baseMapper.selectWorksInfo(worksId);
        return worksInfoVos;
    }

    /**
     * @return java.util.List<com.xu.search.vo.WorksInfoVo>
     * @Description 获取该漫画的推荐漫画 根据漫画题材查询十本
     * @Author F3863479
     * @Date 2023/1/10 上午 10:00
     * @Params [worksCategory]
     */
    @Override
    public List<WorksInfoVo> getRecommendedWorks(String worksCategory, Integer worksType) {
        // 获取分类 然后截取字符串
        String[] worksCategorys = worksCategory.split(",");
        // 查询十本该分类的作品
        return this.baseMapper.getRecommendedWorks(worksCategorys, worksType);
    }

    @Override
    public String getWorksCategoryName(String worksCategory, Integer worksType) {
        // 获取分类 然后截取字符串
        String[] split = worksCategory.split(",");
        // 查询题材然后用/连接
        String CategorysName = categoryService.getCategorysName(split, worksType);
        return CategorysName;
    }

    @Override
    public List<WorksVo> getWorksInfo(List<Long> worksIds) {
        return this.baseMapper.getWorksInfo(worksIds);
    }
    //资源映射路径 前缀
    @Value("${IP}")
    public String IP;
    @Override
    @Transactional
    public void uploadWork(WorksTo worksTo, HttpServletRequest request) throws ExecutionException, InterruptedException, UnknownHostException {
        CompletableFuture<WorksEntity> fantasyTimetoken = CompletableFuture.supplyAsync(() -> {
            // 转换数据把to数据转换到Entity中
            WorksEntity worksEntity = new WorksEntity();
            BeanUtils.copyProperties(worksTo, worksEntity);
            // 获取用户 id
            String token = request.getHeader("FantasyTimetoken");
            String userFromToken = tokenManager.getUserFromToken(token);
            UserEntity userInfo = userService.getUserInfo(userFromToken);
            Long userID = userInfo.getId();
            worksEntity.setCreator(userID.toString());
            // 设置评分
            worksEntity.setWorksScore(0F);
            // 作品更新至多少 刚上传作品肯定是0 因为要审核
            worksEntity.setWorksRenew(0L);
            // 设置人气
            worksEntity.setWorksPopularity(0L);
            worksEntity.setStatus(1);
            this.baseMapper.insert(worksEntity);
            // 远程调用作品上传信息 保存上传记录
            WorksUploadEntity worksUploadEntity = new WorksUploadEntity();
            worksUploadEntity.setUserId(userID);
            worksUploadEntity.setWorksId(worksEntity.getWorksId());
            worksUploadEntity.setReviewStatus(0L);
            worksUploadService.save(worksUploadEntity);
            return worksEntity;
        }, executor);
        WorksEntity works = fantasyTimetoken.get();
        if (works.getWorksId() != null) {
            // 上传封面图片
            CompletableFuture<Void> worksDefaultImageEntityCompletableFuture = CompletableFuture.runAsync(() -> {
                WorksDefaultImageEntity worksDefaultImageEntity = new WorksDefaultImageEntity();
                worksDefaultImageEntity.setWorksId(works.getWorksId());
                worksDefaultImageEntity.setWorksDefaultImage(works.getDefaultImage());
                worksDefaultImageEntity.setReviewStatus(0L);
                worksDefaultImageService.save(worksDefaultImageEntity);
            }, executor);
            CompletableFuture.allOf(fantasyTimetoken, worksDefaultImageEntityCompletableFuture).get();
        }
        //
        works.setDefaultImage("http://" + IP + ":8084/api/oss/getWorkContent?ImageDefaultStatus=1&WorksId=" + works.getWorksId());
        this.baseMapper.updateById(works);
    }

    @Override
    public List<WorksVo> getWorksPage(Integer worksType, List<Long> worksList, Integer page, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        // 分页
        IPage<WorksEntity> pages = this.page(
                new Query<WorksEntity>().getPage(params),
                new QueryWrapper<WorksEntity>().eq("works_type", worksType).in("works_id", worksList)
        );
        // 转移数据
        List<WorksVo> Works = pages.getRecords().stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
        return Works;
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 上传作品到Es
     * @Author F3863479
     * @Date 2023/1/10 上午 10:08
     * @Params [WorksCategory]
     */
    @Override
    public void UpEs(Long worksID) throws Exception {
        WorksEsModel worksEsModel = GetWorksEsModel(worksID);
        // 数据收集成功 开始上传到es
//        System.out.println(worksEsModel);
        R r = searchFeignService.WorksUp(worksEsModel);
        if (r.getCode() == 20000) {
            System.out.println("成功");
        } else {
            throw new RuntimeException("失败");
        }
    }

    @Override
    public PageUtils queryPage(Integer page, Integer limit, Integer reviewStatus, Integer delete_status) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());

        QueryWrapper<WorksEntity> wrapper = new QueryWrapper<WorksEntity>();
        if (reviewStatus != null) {
            wrapper.eq("review_status", reviewStatus);
        }
        if (delete_status != null) {
            wrapper.eq("delete_status", delete_status);
        }

        IPage<WorksEntity> pages = this.page(
                new Query<WorksEntity>().getPage(params), wrapper
        );
        List<AreaEntity> getarea = areaService.getarea();
        List<WorksEntity> collect = pages.getRecords().stream().map((item) -> {
            for (AreaEntity areaEntity : getarea) {
                String worksArea = item.getWorksArea();
                String id = areaEntity.getId().toString();
                if (worksArea.equals(id)) {
                    item.setWorksArea(areaEntity.getName());
                    break;
                }
            }
            String worksCategory = item.getWorksCategory();
            String categorysName = categoryService.getCategorysName(worksCategory.split(","), item.getWorksType().intValue());
            item.setWorksCategory(categorysName);
            return item;
        }).collect(Collectors.toList());
        pages.setRecords(collect);
        return new PageUtils(pages);
    }

    @Override
    @Transactional
    public void review(ReviewWorksTo reviewWorksTo, HttpServletRequest httpRequest) throws Exception {

        // 获取作品id
        Long worksId = reviewWorksTo.getWorksId();
        // 更新作品 审核状态
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            // 修改作品的审核状态
            this.baseMapper.review(reviewWorksTo);
        }, executor);

        // 更新作品上传记录 审核状态
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
            // 修改上传作品记录的审核状态
            // 先获取用户id
            UserEntity userEntity = userService.getUserEntity(httpRequest);
            Long user_id = userEntity.getId();
//        worksUploadService.updateReview(user_id,worksId);
            worksUploadService.update(new UpdateWrapper<WorksUploadEntity>()
                    .eq("user_id", user_id)
                    .eq("works_id", worksId)
                    .set("review_status", 1)
            );
        }, executor);
        voidCompletableFuture.get();
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
            WorksEntity worksEntity = this.baseMapper.selectById(worksId);
            if (worksEntity.getWorksType() == 3) {
                // 插图 直接更新漫画章节信息的表格
                CartoonWorksDetailsEntityTo cartoonWorksDetailsEntity = new CartoonWorksDetailsEntityTo();
                cartoonWorksDetailsEntity.setWorksId(worksEntity.getWorksId());
                cartoonWorksDetailsEntity.setCartoonChapterId(1L);
                cartoonWorksDetailsEntity.setCartoonChapterName(worksEntity.getWorksName());
                ArrayList<String> strings = new ArrayList<>();
                strings.add(worksEntity.getDefaultImage());
                cartoonWorksDetailsEntity.setWorksChapterLocations(strings);
                cartoonWorksDetailsService.saveUploadChapterData(cartoonWorksDetailsEntity, httpRequest);
            }
        }, executor);
        CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.runAsync(() -> {
            worksDefaultImageService.update(
                    new UpdateWrapper<WorksDefaultImageEntity>().set("review_status", 1).eq("works_id", worksId)
            );
        }, executor);
        Void unused = CompletableFuture.allOf(voidCompletableFuture, voidCompletableFuture1, voidCompletableFuture2, voidCompletableFuture3).get();
        // 审核完成 把作品上传到es
//        UpEs(worksId);
    }

    /**
     * 最近更新 最近发布 人气最高 评分最高 查询封装
     */
    public IPage<WorksEntity> getData(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit, String OrderByColumn) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        // 截取題材 用于函数查询
        IPage<WorksEntity> pages = this.page(
                new Query<WorksEntity>().getPage(params),
                new QueryWrapper<WorksEntity>().eq("works_type", worksType)
                        .eq("works_area", area)
                        .eq("review_status", 1)
                        .apply("works_category regexp '[" + worksCategory + "]'")
                        .orderByDesc(OrderByColumn)
        );
        return pages;
    }


    /**
     * @param worksType
     * @return java.util.List<com.xu.search.entity.WorksEntity>
     * @Description 最近更新 前100本 和今天更新的十本
     * @Author F3863479
     * @Date 2023/1/5 下午 04:00
     * @Params []
     */
    @Override
    public Map<String, List<WorksVo>> getRecentlyUpdated(Integer worksType) {

        LinkedHashMap<String, List<WorksVo>> map = new LinkedHashMap<>();
        // 获取今日更新的前十本
        // 获取当前时间
        long today = System.currentTimeMillis();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        List<WorksVo> todayUpdated = this.baseMapper.getRecentlyUpdatedTop(worksType, s.format(today));
        // 获取最近更新的前十本
        List<WorksVo> RecentlyUpdated = this.baseMapper.getRecentlyUpdated(worksType).stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
        map.put("todayUpdated", todayUpdated);
        map.put("RecentlyUpdated", RecentlyUpdated);
        return map;
    }

    /**
     * @param worksType
     * @return java.util.List<com.xu.search.entity.WorksEntity>
     * @Description 经典完结 前十本
     * @Author F3863479
     * @Date 2023/1/5 下午 04:33
     * @Params []
     */
    @Override
    public List<WorksVo> getClassicEnd(Integer worksType) {
        //  根据作品类型id区分 小说和漫画 在人气表中 获取完本的 最高人气的前一百本  然后 sql算法拿出十本作品(works)的id
        List<String> popularityIds = popularityService.getPopularityTop(worksType);
        // 去作品表格中拿取集合里面的id的数据
        return this.baseMapper.getClassicEnd(popularityIds).stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
    }

    /**
     * @param area
     * @param worksType
     * @return java.util.List<com.xu.search.entity.WorksEntity>
     * @Description 热门连载  前十本
     * @Author F3863479
     * @Date 2023/1/5 下午 04:53
     * @Params []
     */
    @Override
    public List<WorksVo> getPopularSerial(Integer area, Integer worksType) {
        return this.baseMapper.getPopularSerial(area, worksType).stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
    }

    /**
     * @return java.util.List<com.xu.search.entity.WorksEntity>
     * @Description 排行榜 前100本 今日 三天 本周 今月 异步请求 全部完成统一返回 报错不用管
     * @Author F3863479
     * @Date 2023/1/6 上午 09:21
     * @Params [worksType]
     */
    @Override
    public Map<String, Object> getLeaderboard(Integer worksType, Integer page, Integer limit) {
        // 设置map收集数据
        Map<String, Object> map = new LinkedHashMap<>();
        // 今天人气前一百 从redis中zset集合拿取 增删改查也是在zset
        CompletableFuture<Void> dailyLeaderboard = CompletableFuture.runAsync(() -> {
            // 今天人气前一百 从redis中zset集合拿取 增删改查也是在zset
            // 用list集合收集
            ArrayList<String> workList = new ArrayList<>();
            // 返回集合
            List<WorksVo> dailyLeaderboardList = null;
            Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(
                    WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg(),
                    0,
                    100
            );
            if (typedTuples.size() != 0 && typedTuples != null) {
                for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
                    String worksID = typedTuple.getValue();
                    workList.add(worksID);
                }
                dailyLeaderboardList = this.baseMapper.getDailyLeaderboardTop(worksType, workList).stream().map(item -> {
                    WorksVo worksVo = new WorksVo();
                    BeanUtils.copyProperties(item, worksVo);
                    return worksVo;
                }).collect(Collectors.toList());

            } else {
                // 拿取 默认数据
            }
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("page", page);
            map1.put("limit", limit);
            map1.put("count", dailyLeaderboardList == null ? 0 : dailyLeaderboardList.size());
            map1.put("list", dailyLeaderboardList == null ? null : dailyLeaderboardList.size() <= limit ? dailyLeaderboardList : dailyLeaderboardList.subList((page - 1) * limit, dailyLeaderboardList.size() >= page * limit ? page * limit : dailyLeaderboardList.size()));
            map.put("dailyLeaderboard", map1);
        }, executor);
        // 三天人气前一百
        CompletableFuture<Void> threeDaysLeaderboard = CompletableFuture.runAsync(() -> {
            String s = stringRedisTemplate.opsForValue().get(WorksEnum.Works_mh_Enum.works_popularity_three_days_mh.getMsg());
            // 三天人气前一百
            List<WorksVo> threeDaysLeaderboardList = JSONObject.parseArray(s, WorksVo.class);
            if (threeDaysLeaderboardList == null || threeDaysLeaderboardList.size() == 0) {
                threeDaysLeaderboardList = this.baseMapper.getthreeDaysLeaderboardTop(worksType).stream().map(item -> {
                    WorksVo worksVo = new WorksVo();
                    BeanUtils.copyProperties(item, worksVo);
                    return worksVo;
                }).collect(Collectors.toList());
                String s1 = JSONUtil.toJsonStr(threeDaysLeaderboardList);
                stringRedisTemplate.opsForValue().set(WorksEnum.Works_mh_Enum.works_popularity_three_days_mh.getMsg(), s1);
            }
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("page", page);
            map1.put("limit", limit);
            map1.put("count", threeDaysLeaderboardList == null ? 0 : threeDaysLeaderboardList.size());
            map1.put("list", threeDaysLeaderboardList.subList((page - 1) * limit, threeDaysLeaderboardList.size() >= page * limit ? page * limit : threeDaysLeaderboardList.size()));
            map.put("threeDaysLeaderboard", map1);
        }, executor);
        // 本周人气前一百
        CompletableFuture<Void> thisWeekLeaderboard = CompletableFuture.runAsync(() -> {
            // 本周人气前一百

            String s = stringRedisTemplate.opsForValue().get(
                    WorksEnum.Works_mh_Enum.works_popularity_thisWeek_mh.getMsg());
            List<WorksVo> thisWeekLeaderboardLust = JSONObject.parseArray(s, WorksVo.class);
            if (thisWeekLeaderboardLust == null || thisWeekLeaderboardLust.size() == 0) {
                thisWeekLeaderboardLust = this.baseMapper.getthisWeekLeaderboardTop(worksType).stream().map(item -> {
                    WorksVo worksVo = new WorksVo();
                    BeanUtils.copyProperties(item, worksVo);
                    return worksVo;
                }).collect(Collectors.toList());
                String s1 = JSONObject.toJSON(thisWeekLeaderboardLust).toString();
                stringRedisTemplate.opsForValue().set(WorksEnum.Works_mh_Enum.works_popularity_thisWeek_mh.getMsg(), s1);
            }
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("page", page);
            map1.put("limit", limit);
            map1.put("count", thisWeekLeaderboardLust.size());
            map1.put("list", thisWeekLeaderboardLust.subList((page - 1) * limit, thisWeekLeaderboardLust.size() >= page * limit ? page * limit : thisWeekLeaderboardLust.size()));
            map.put("thisWeekLeaderboard", map1);
        }, executor);
        // 今月人气前一百
        CompletableFuture<Void> thisMonthDaysLeaderboard = CompletableFuture.runAsync(() -> {
            // 今月人气前一百

            String s = stringRedisTemplate.opsForValue().get(
                    WorksEnum.Works_mh_Enum.works_popularity_thisMonth_mh.getMsg());
            List<WorksVo> thisMonthDaysLeaderboardList = JSONObject.parseArray(s, WorksVo.class);
            if (thisMonthDaysLeaderboardList == null || thisMonthDaysLeaderboardList.size() == 0) {
                thisMonthDaysLeaderboardList = this.baseMapper.getthisMonthDaysLeaderboardTop(worksType).stream().map(item -> {
                    WorksVo worksVo = new WorksVo();
                    BeanUtils.copyProperties(item, worksVo);
                    return worksVo;
                }).collect(Collectors.toList());
                String s1 = JSONObject.toJSON(thisMonthDaysLeaderboardList).toString();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    stringRedisTemplate.opsForValue().set(WorksEnum.Works_mh_Enum.works_popularity_thisMonth_mh.getMsg(), mapper.writeValueAsString(thisMonthDaysLeaderboardList));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("page", page);
            map1.put("limit", limit);
            map1.put("count", thisMonthDaysLeaderboardList.size());
            map1.put("list", thisMonthDaysLeaderboardList.subList((page - 1) * limit, thisMonthDaysLeaderboardList.size() >= page * limit ? page * limit : thisMonthDaysLeaderboardList.size()));
            map.put("thisMonthDaysLeaderboard", map1);
        }, executor);
        try {
            CompletableFuture.allOf(dailyLeaderboard, threeDaysLeaderboard, thisWeekLeaderboard, thisMonthDaysLeaderboard).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @return java.util.List<com.xu.search.vo.WorksVo>
     * @Description 最新作品根据作品类型查询最近出品的作品前十本
     * @Author F3863479
     * @Date 2023/1/6 上午 09:41
     * @Params [worksType]
     */
    @Override
    public List<WorksVo> getLatestCreation(Integer worksType) {
        List<WorksEntity> worksEntities = this.baseMapper.getLatestCreation(worksType);
        return worksEntities.stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
    }

    /**
     * @return java.util.List<com.xu.search.vo.WorksVo>
     * @Description 根据 历史记录 作品分类查询
     * @Author F3863479
     * @Date 2023/1/6 上午 09:58
     * @Params [worksType] 页面类型
     */
    @Override
    public List<WorksVo> getRecommendedToday(Integer worksType) {
        /*
        思路: 根据页面类型 判断是小说还是漫画来区分作品的分类 然后根据分类去作品表中查询作品分类属性中有该分类的作品 查询前十本 以随机数选出
        * */
        // 1.根据页面类型 去历史记录查询 所有作品中随机十本
        List<String> worksIds = worksWatchHistoryService.getRecommendedTodayTop(worksType);
        // 2.根据查询出来的十本作品id查询分类 用set集合收集起来
        Set<String> set = new HashSet<>();
        this.baseMapper.selectList(new QueryWrapper<WorksEntity>().select("works_category").eq("delete_status", 1).in("works_id", worksIds)).forEach(item -> {
            // 获取该作品的分类  用,分割set收集
            String worksCategory = item.getWorksCategory();
            String[] split = worksCategory.split(",");
            set.addAll(Arrays.asList(split));
        });
        // 根据分类 查询作品随机抽取十本
        List<WorksVo> collect = this.baseMapper.getRecommendedToday(worksType, set).stream().map(item -> {
            WorksVo worksVo = new WorksVo();
            BeanUtils.copyProperties(item, worksVo);
            return worksVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void WorksInBookshelfUpdate(SaveBookToShelfTo saveBookToShelfTo) {
        // 获取worksId
        Long worksId = saveBookToShelfTo.getWorksId();
        WorksEntity worksEntity = this.baseMapper.selectById(worksId);
        long WorksRenew = worksEntity.getWorksRenew() + 1;
        worksEntity.setWorksRenew(WorksRenew);
        worksEntity.setEditTime(new Date(System.currentTimeMillis()));
        this.baseMapper.updateById(worksEntity);
        worksBookshelfService.update(
                new UpdateWrapper<WorksBookshelfEntity>()
                        .eq("works_id", worksId)
                        .set("works_renew", WorksRenew)
                        .set("works_update_time", new Date(System.currentTimeMillis()))
        );
//        String s = UpdateWorksEs(worksId);
//        if (!s.equals("OK")) {
//            throw new RuntimeException("章节信息更新失败");
//        }
    }

    @Override
    public String UpdateWorksEs(Long worksID) {
//        WorksEsModel worksEsModel = GetWorksEsModel(worksID);
//        R r = searchFeignService.UpdateEs(worksEsModel);
//        if (r.getCode() == 20000) {
            return "OK";
//        } else {
//            return "es修改失败";
//        }

    }

    @Override
    public Integer GetWorksCount(HttpServletRequest request) {
        // 获取作品数量
        UserEntity userEntity = userService.getUserEntity(request);
        return this.baseMapper.selectCount(
                new QueryWrapper<WorksEntity>().eq("creator", userEntity.getId()));
    }

    @Override
    public Integer GeTCollectCount(HttpServletRequest request) {
        // 获取作品数量
        UserEntity userEntity = userService.getUserEntity(request);
        return worksBookshelfService.count(new QueryWrapper<WorksBookshelfEntity>()
                .eq("delete_status", 1).eq("user_id", userEntity.getId()));
    }

    @Override
    public List<DownListVo> GetWorksDownList(HttpServletRequest request) {

        // 获取作品数量
        UserEntity userEntity = userService.getUserEntity(request);
        return this.baseMapper.GetWorksDownList(userEntity.getId());
    }

    /* 获取插图集 */
    @Override
    public List<WorksEntity> GetIllustration(HttpServletRequest request) {
        return this.baseMapper.selectList(new QueryWrapper<WorksEntity>().eq("works_type", 3).eq("review_status", 1));
    }

    @Override
    public Map<String, List<WorksEntity>> GetIDTpWorks(Integer userId) {
        List<WorksEntity> worksEntities = this.baseMapper.selectList(new QueryWrapper<WorksEntity>().eq("creator", userId));
        HashMap<String, List<WorksEntity>> maps = new HashMap<>();
        List<WorksEntity> worksToschatu = new ArrayList<>();
        List<WorksEntity> worksTosmanhua = new ArrayList<>();
        worksEntities.forEach(
                item -> {
                    if (item.getWorksType() == 1) {
                        worksTosmanhua.add(item);
                    }
                    if (item.getWorksType() == 3) {
                        worksToschatu.add(item);
                    }
                }
        );
        maps.put("chatu", worksToschatu);
        maps.put("works", worksTosmanhua);
        return maps;
    }

    // 獲取es需要的WorksEsModel
    private WorksEsModel GetWorksEsModel(Long worksID) {
        // 查到作品
        WorksEntity worksEntity = this.baseMapper.selectOne(new QueryWrapper<WorksEntity>().eq("works_id", worksID));
        // new esModel
        WorksEsModel worksEsModel = new WorksEsModel();
        // copy值
        BeanUtils.copyProperties(worksEntity, worksEsModel);
        worksEsModel.setWorksCreateTime(worksEntity.getWorksCreateTime().toString());
        // 获取中文版本的国家地区
        CompletableFuture<Void> AreaEntityAsync = CompletableFuture.runAsync(() -> {
            AreaEntity areaEntity = areaService.getOne(new QueryWrapper<AreaEntity>().eq("id", worksEntity.getWorksArea()));
            worksEsModel.setWorksAreaName(areaEntity.getName());
        }, executor);
        // 获取 作品类别name
        CompletableFuture<Void> categorysNameAsync = CompletableFuture.runAsync(() -> {
            // 获取作品类型 小说还是漫画
            Long worksType = worksEntity.getWorksType();
            // 获取类别 集合
            String[] split = worksEntity.getWorksCategory().split(",");
            String categorysName = categoryService.getCategorysName(split, Math.toIntExact(worksType) == 3 ? 1 : Math.toIntExact(worksType) == 1 ? 1 : 2);
//            AreaEntity areaEntity = categoryService.getOne(new QueryWrapper<AreaEntity>().eq("id", worksEntity.getWorksArea()));
            worksEsModel.setWorksCategoryName(categorysName);
        }, executor);
        try {
            CompletableFuture.allOf(AreaEntityAsync, categorysNameAsync).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Date createTime = worksEntity.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        worksEsModel.setWorksCreateTime(simpleDateFormat.format(createTime));
        return worksEsModel;
    }
}