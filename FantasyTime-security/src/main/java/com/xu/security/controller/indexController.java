package com.xu.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-上午 11:51
 */
@Controller
public class indexController {

    @RequestMapping("/success")
    @ResponseBody
    public String index(){
        return "success";
    }


    @RequestMapping("/findAll")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_管理员')")
    public String findAll(){
        return "findAll";
    }
}
