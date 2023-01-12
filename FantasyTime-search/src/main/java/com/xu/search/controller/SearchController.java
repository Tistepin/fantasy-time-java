package com.xu.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-下午 02:50
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @RequestMapping("/list")
    public String list(){
        return "list";
    }
}
