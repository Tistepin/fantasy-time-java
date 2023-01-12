package com.xu.search.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xu.search.entity.RoleUserEntity;
import com.xu.search.service.RoleUserService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 用户角色关联表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("search/roleuser")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = roleUserService.queryPage(params);

        return R.ok().data("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{uid}")
    public R info(@PathVariable("uid") Long uid){
		RoleUserEntity roleUser = roleUserService.getById(uid);

        return R.ok().data("roleUser", roleUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RoleUserEntity roleUser){
		roleUserService.save(roleUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody RoleUserEntity roleUser){
		roleUserService.updateById(roleUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] uids){
		roleUserService.removeByIds(Arrays.asList(uids));

        return R.ok();
    }

}
