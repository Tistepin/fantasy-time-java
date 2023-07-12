package com.xu.works.controller;

import com.xu.works.entity.UserEntity;
import com.xu.works.to.UserTo;
import com.xu.works.to.userUpdateTo;
import com.xu.works.vo.UpdateUserPwd;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.service.UserService;
import com.xu.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户信息表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("works/user")
@Api(tags = "用户信息表")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @return com.xu.common.utils.R
     * @Description 注册用户信息
     * @Author F3863479
     * @Date 2023/1/10 上午 10:08
     * @Params [WorksCategory]
     */
    @ApiOperation(value = "注册用户信息", notes = "注册用户信息")
    @ApiImplicitParam(name = "userTou", value = "注册用户信息To ", paramType = "body", required = true, dataType = "UserTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PutMapping("/regist")
    public R saveUserInfo(@RequestBody UserTo userTou) {
         userService.saveUserInfo(userTou);
        return R.ok();
    }


    // 3.根据请求获取用户实体类
    @ApiOperation(value = "获取用户实体类", notes = "获取用户实体类")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getUserEntity")
    public R getUserEntity(HttpServletRequest request) {
        UserEntity userEntity=userService.getUserEntity(request);
        return R.ok().data("data",userEntity);
    }

    // 3.根据请求获取用户实体类
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParam(name = "userUpdateTo", value = "更新用户 ", paramType = "body",
            required = true, dataType = "userUpdateTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/updateUser")
    public R updateUser(@RequestBody userUpdateTo userUpdateTo) {
        userService.updateUser(userUpdateTo);
        return R.ok();
    }

    // 3.修改用户密码
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParam(name = "userUpdateTo", value = "更新用户 ", paramType = "body",
            required = true, dataType = "userUpdateTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/updateUserPwd")
    public R updateUserPwd(@RequestBody UpdateUserPwd updateUserPwd) {
        try {
            userService.updateUserPwd(updateUserPwd);
        } catch (Exception e) {
            return R.error().message(e.getMessage());
        }
        return R.ok();
    }
}
