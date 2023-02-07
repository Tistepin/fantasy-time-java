package com.xu.works.controller;

import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import com.xu.works.service.WorksService;
import com.xu.works.to.ReviewWorksTo;
import com.xu.works.to.WorksTo;
import com.xu.works.vo.WorksInfoVo;
import com.xu.works.vo.WorksVo;
import io.swagger.annotations.*;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * 作品信息
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("works/works")
@Api(tags = "作品信息")
public class WorksController {
    @Autowired
    private WorksService worksService;


    /**
     * @return com.xu.common.utils.R
     * @Description 最近更新 前100本 和今天更新的十本
     * @Author F3863479
     * @Date 2023/1/5 下午 03:58
     * @Params []
     */
    @ApiOperation(value = "最近更新 前100本 和今天更新的十本", notes = "最近更新 前100本 和今天更新的十本")
    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getRecentlyUpdated")
    public R getRecentlyUpdated(@RequestParam() Integer worksType) {
        Map<String, List<WorksVo>> worksEntities = worksService.getRecentlyUpdated(worksType);
        return R.ok().data("data", worksEntities);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 经典完结
     * @Author F3863479
     * @Date 2023/1/5 下午 04:32
     * @Params []
     */
    @ApiOperation(value = "经典完结 前十本", notes = "经典完结 前十本")
    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getClassicEnd")
    public R getClassicEnd(@RequestParam() Integer worksType) {
        List<WorksVo> worksEntities = worksService.getClassicEnd(worksType);
        return R.ok().data("data", worksEntities);
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 热门连载 前十本
     * @Author F3863479
     * @Date 2023/1/5 下午 04:32
     * @Params []
     */
    @ApiOperation(value = "热门连载 前十本", notes = "热门连载 前十本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "area", value = "国家ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getPopularSerial")
    public R getPopularSerial(@RequestParam() Integer area, Integer worksType) {
        List<WorksVo> worksEntities = worksService.getPopularSerial(area, worksType);
        return R.ok().data("data", worksEntities);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 最新作品 前十本 最近画的
     * @Author F3863479
     * @Date 2023/1/5 下午 04:32
     * @Params []
     */
    @ApiOperation(value = "最新作品 前十本", notes = "最新作品 前十本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getLatestCreation")
    public R getLatestCreation(@RequestParam Integer worksType) {
        List<WorksVo> worksEntities = worksService.getLatestCreation(worksType);
        return R.ok().data("data", worksEntities);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 今日推荐
     * @Author F3863479
     * @Date 2023/1/6 上午 09:56
     * @Params [worksType]
     */
    @ApiOperation(value = "今日推荐 前十本", notes = "今日推荐 前十本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getRecommendedToday")
    public R getRecommendedToday(@RequestParam Integer worksType) {
        List<WorksVo> worksEntities = worksService.getRecommendedToday(worksType);
        return R.ok().data("data", worksEntities);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 排行榜 前100本 今日 三天 本周 今月
     * @Author F3863479
     * @Date 2023/1/5 下午 04:32
     * @Params []
     */
    @ApiOperation(value = "排行榜 前100本", notes = "今日排行榜 前100本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getDailyLeaderboard")
    public R getLeaderboard(@RequestParam Integer worksType) {
        Map<String, List<WorksVo>> worksEntities = worksService.getLeaderboard(worksType);
        return R.ok().data("data", worksEntities);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 最新作品
     * @Author F3863479
     * @Date 2023/1/5 下午 04:32
     * @Params []
     */
    @ApiOperation(value = "最新作品", notes = "最新作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "area", value = "国家地区", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksCategory", value = "作品分类", paramType = "query", dataType = "String", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getLatestCreationPage")
    public R getLatestCreationPage(@RequestParam Integer worksType,
                                   @RequestParam Integer area,
                                   @RequestParam String worksCategory,
                                   @RequestParam Integer page,
                                   @RequestParam Integer limit) {
        PageUtils pages = worksService.LatestCreationqueryPage(worksType, area, worksCategory, page, limit);
        return R.ok().data("data", pages);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 最近更新作品
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @ApiOperation(value = "最近更新作品", notes = "最新作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "area", value = "国家地区", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksCategory", value = "作品分类", paramType = "query", dataType = "String", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getRecentlyUpdatedPage")
    public R getRecentlyUpdatedPage(@RequestParam Integer worksType,
                                    @RequestParam Integer area,
                                    @RequestParam String worksCategory,
                                    @RequestParam Integer page,
                                    @RequestParam Integer limit) {
        PageUtils pages = worksService.getRecentlyUpdatedPage(worksType, area, worksCategory, page, limit);
        return R.ok().data("data", pages);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 人气最旺作品
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @ApiOperation(value = "人气最旺作品", notes = "人气最旺作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "area", value = "国家地区", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksCategory", value = "作品分类", paramType = "query", dataType = "String", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getPopularPage")
    public R getPopularPage(@RequestParam Integer worksType,
                            @RequestParam Integer area,
                            @RequestParam String worksCategory,
                            @RequestParam Integer page,
                            @RequestParam Integer limit) {
        PageUtils pages = worksService.getPopularPage(worksType, area, worksCategory, page, limit);
        return R.ok().data("data", pages);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 评分最高作品
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @ApiOperation(value = "评分最高作品", notes = "评分最高作品")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "area", value = "国家地区", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksCategory", value = "作品分类", paramType = "query", dataType = "String", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getHighScorePage")
    public R getHighScorePage(@RequestParam Integer worksType,
                              @RequestParam Integer area,
                              @RequestParam String worksCategory,
                              @RequestParam Integer page,
                              @RequestParam Integer limit) {
        PageUtils pages = worksService.getHighScorePage(worksType, area, worksCategory, page, limit);
        return R.ok().data("data", pages);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 作品详情信息
     * @Author F3863479
     * @Date 2023/1/9 上午 10:49
     * @Params [worksType, area, worksCategory, page, limit]
     */
    @ApiOperation(value = "作品详情信息", notes = "作品详情信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "WorksId", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getWorksInfo")
    public R getWorksInfo(@RequestParam Integer WorksId) {
        WorksInfoVo worksEntity = worksService.getWorksInfo(WorksId);
        return R.ok().data("data", worksEntity);
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 获取该漫画的推荐漫画 根据漫画题材查询十本
     * @Author F3863479
     * @Date 2023/1/10 上午 09:58
     * @Params [WorksId]
     */
    @ApiOperation(value = "获取该漫画的推荐漫画", notes = "获取该漫画的推荐漫画")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "WorksCategory", value = "作品类型", paramType = "query", dataType = "String", defaultValue = "1,8", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getRecommendedWorks")
    public R getRecommendedWorks(@RequestParam String WorksCategory, @RequestParam Integer worksType) {
        List<WorksInfoVo> worksEntity = worksService.getRecommendedWorks(WorksCategory, worksType);
        return R.ok().data("data", worksEntity);
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 漫画题材ID转换为题材名字
     * @Author F3863479
     * @Date 2023/1/10 上午 10:08
     * @Params [WorksCategory]
     */
    @ApiOperation(value = "漫画题材ID转换为题材名字", notes = "漫画题材ID转换为题材名字")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "WorksCategory", value = "作品类型", paramType = "query", dataType = "String", defaultValue = "1,8", allowEmptyValue = true),
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getWorksCategoryName")
    public R getWorksCategoryName(@RequestParam String WorksCategory,
                                  @RequestParam Integer worksType) {
        String WorksCategoryNames = worksService.getWorksCategoryName(WorksCategory, worksType);
        return R.ok().data("data", WorksCategoryNames);
    }


    /**
     * @return com.xu.common.utils.R
     * @Description 上传作品
     * @Author F3863479
     * @Date 2023/1/10 上午 10:08
     * @Params [WorksCategory]
     */
    @ApiOperation(value = "上传作品", notes = "上传作品")
    @ApiImplicitParam(name = "worksTo", value = "上传章节数据To ", paramType = "body", required = true, dataType = "WorksTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PutMapping("/uploadWork")
    public R getWorksCategoryName(@RequestBody WorksTo worksTo, HttpServletRequest request) {
        try {
            worksService.uploadWork(worksTo, request);
        } catch (ExecutionException | InterruptedException e) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 上传作品到Es
     * @Author F3863479
     * @Date 2023/1/10 上午 10:08
     * @Params [WorksCategory]
     */
    @ApiOperation(value = "上传作品到Es", notes = "上传作品到Es")
//    @ApiImplicitParam(name = "WorksID", value = "作品ID", paramType = "query", dataType = "Integer", defaultValue = "123", allowEmptyValue = true)
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/UpEs/{WorksID}")
    public R UpEs(@PathVariable("WorksID") Long WorksID) {
        try {
            worksService.UpEs(WorksID);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * @Description 审核作品数据
     * @Author F3863479
     * @Date 2023/2/1 下午 01:18
     * @Params [page, limit]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "审核作品数据", notes = "审核作品数据")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page", value = "页", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "页面个数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/list")
    public R list(Integer page,Integer limit){
        PageUtils pages =worksService.queryPage(page,limit);
        return R.ok().data("page", pages);
    }/**
     * @Description 作品审核
     * @Author F3863479
     * @Date 2023/2/1 下午 01:18
     * @Params [worksTo, request]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "作品审核", notes = "作品审核")
    @ApiImplicitParam(name = "reviewWorksTo", value = "作品审核 ", paramType = "body", required = true, dataType = "ReviewWorksTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/review")
    public R review(@RequestBody ReviewWorksTo reviewWorksTo, HttpServletRequest httpRequest) {
        try {
            worksService.review(reviewWorksTo,httpRequest);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }
}
