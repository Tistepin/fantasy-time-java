package com.xu.search.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.xu.search.entity.CategoryEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.search.entity.AreaEntity;
import com.xu.search.service.AreaService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 国家区域
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("search/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @ApiOperation(value = "获取国家区域", notes = "获取国家区域")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "worksType", value = "作品类型", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
            }
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
    })
    @GetMapping("/getArea")
    public R getArea() {
        List<AreaEntity> categoryEntities= areaService.getarea();
        return R.ok().data("data", categoryEntities);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		AreaEntity area = areaService.getById(catId);

        return R.ok().data("area", area);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public R save(@RequestBody AreaEntity area){
		areaService.save(area);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody AreaEntity area){
		areaService.updateById(area);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
		areaService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
