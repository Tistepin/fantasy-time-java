package com.xu.works.controller;

import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;
import com.xu.works.entity.WorksBookshelfEntity;
import com.xu.works.service.WorksBookshelfService;
import com.xu.works.to.SaveBookToShelfTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 书架
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-14 13:52:45
 */
@RestController
@Api(tags = "书架")
@RequestMapping("works/worksbookshelf")
public class WorksBookshelfController {
    @Autowired
    private WorksBookshelfService worksBookshelfService;

    /**
     * 获取书架
     */
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "page", value = "显示页数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
                    @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiOperation(value = "获取书架", notes = "获取书架")
    @GetMapping("/getWorksBookshelf")
    public R getWorksBookshelf(@RequestParam Integer worksType,
                               @RequestParam Integer page,
                               @RequestParam Integer limit, HttpServletRequest request) {
        PageUtils pages = worksBookshelfService.queryPage(worksType, page, limit, request);
        return R.ok().data("page", pages);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        WorksBookshelfEntity worksBookshelf = worksBookshelfService.getById(id);

        return R.ok().data("worksBookshelf", worksBookshelf);
    }


    @ApiImplicitParam(name = "saveBookToShelfTo", value = "收藏作品To ", paramType = "body", required = true, dataType = "SaveBookToShelfTo")
    @ApiOperation(value = "收藏作品", notes = "收藏作品")
    @PutMapping("/saveBookToShelf")
    public R save(@RequestBody SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request) {
        worksBookshelfService.saveBookToShelf(saveBookToShelfTo, request);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody WorksBookshelfEntity worksBookshelf) {
        worksBookshelfService.updateById(worksBookshelf);

        return R.ok();
    }

    @ApiImplicitParam(name = "saveBookToShelfTo", value = "收藏作品To ", paramType = "body", required = true, dataType = "SaveBookToShelfTo")
    @ApiOperation(value = "取消订阅指定作品", notes = "取消订阅指定作品")
    @DeleteMapping("/unsubscribe")
    public R unsubscribe(@RequestBody SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request) {
        worksBookshelfService.unsubscribe(saveBookToShelfTo, request);
        return R.ok();
    }

    /**
     * @return com.xu.common.utils.R
     * @Description 该用户是否收藏概述及
     * @Author F3863479
     * @Date 2023/7/6 下午 03:59
     * @Params [saveBookToShelfTo, request]
     */
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksId", value = "作品iD", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true),
            }
    )
    @ApiOperation(value = "查询订阅指定作品", notes = "查询订阅指定作品")
    @GetMapping("/getYesOrNoFavorite")
    public R GetYesOrNoFavorite(@RequestParam Integer worksId, HttpServletRequest request) {
        Boolean bool = worksBookshelfService.GetYesOrNoFavorite(worksId, request);
        if (bool) {
            return R.ok();
        }
        return R.error();
    }

}
