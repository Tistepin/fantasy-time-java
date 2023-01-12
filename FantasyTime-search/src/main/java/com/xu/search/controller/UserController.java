package com.xu.search.controller;

import java.util.Arrays;
import java.util.Map;

import com.xu.search.to.UserTo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.search.entity.UserEntity;
import com.xu.search.service.UserService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 用户信息表
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@RestController
@RequestMapping("search/user")
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
            @ApiResponse(code = 200, message = "請求成功", response = R.class)
    })
    @PutMapping("/regist")
    public R saveUserInfo(@RequestBody UserTo userTou) {
         userService.saveUserInfo(userTou);
        return R.ok();
    }

}
