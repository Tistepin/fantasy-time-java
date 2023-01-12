package com.xu.search.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.search.entity.CategoryEntity;
import com.xu.search.service.CategoryService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 作品类型
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("search/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @Description 根据作品类型获取分类 列表 配置缓存使用 redis存储 使用 spring-cache
     * @Author F3863479
     * @Date 2023/1/6 下午 01:44
     * @Params [params]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "根据作品类型获取分类", notes = "根据作品类型获取分类")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
    })
    @GetMapping("/getCategory")
    public R getCategory(@RequestParam Integer worksType) {
        List<CategoryEntity> categoryEntities= categoryService.getCategory(worksType);
        return R.ok().data("data", categoryEntities);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().data("category", category);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }



}
