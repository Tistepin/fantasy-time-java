package com.xu.search.controller;


import com.xu.common.TO.es.WorksEsModel;
import com.xu.common.constant.BizCodeEnume;
import com.xu.common.utils.R;
import com.xu.search.service.MallSearchService;
import com.xu.search.vo.SearchParam;
import com.xu.search.vo.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 徐国纪
 * @createTime: 2020-06-12 18:07
 **/

@RestController
@RequestMapping("/search")
@Api(tags = "es搜索")
@Slf4j
public class SearchController {

    @Autowired
    private MallSearchService mallSearchService;


    /**
     * @return javax.naming.directory.SearchResult
     * @Description 根据条件从es中获取数据
     * @Author F3863479
     * @Date 2023/2/3 下午 02:26
     * @Params [searchParam, request]
     */
    @ApiOperation(value = "根据条件从es中获取数据", notes = "根据条件从es中获取数据")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping(value = "/getWorks")
    public SearchResult listPage(SearchParam searchParam) {
        SearchResult result = mallSearchService.search(searchParam);
        return result;
    }


    @PostMapping("WorksUp")
    public R WorksUp(@RequestBody WorksEsModel worksEsModels) {
        Boolean b = true;
        try {
            b = mallSearchService.WorksUp(worksEsModels);
        } catch (IOException e) {
            log.error("es商品上架错误信息{}", e);
            return R.error().code(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode()).message(BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if (!b) {
            return R.ok();
        } else {
            return R.error().code(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode()).message(BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
        }

    }


    // 修改es的數據

    //    @PostMapping("UpdateEs")
//    public R WorksUp(){
//        Boolean b=true;
//        try {
//            b = mallSearchService.WorksUp(worksEsModels);
//        } catch (IOException e) {
//            log.error("es商品上架错误信息{}",e);
//            return R.error().code(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode()).message(BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
//        }
//        if (!b){
//            return R.ok();
//        }else{
//            return R.error().code(BizCodeEnume.PRODUCT_UP_EXCEPTION.getCode()).message(BizCodeEnume.PRODUCT_UP_EXCEPTION.getMsg());
//        }
//
//    }
}
