package com.xu.works.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xu.works.entity.RolesEntity;
import com.xu.works.service.RolesService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 角色信息表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("works/roles")
@Api(tags = "角色信息表")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rolesService.queryPage(params);

        return R.ok().data("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		RolesEntity roles = rolesService.getById(id);

        return R.ok().data("roles", roles);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RolesEntity roles){
		rolesService.save(roles);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RolesEntity roles){
		rolesService.updateById(roles);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		rolesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
