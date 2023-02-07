package com.xu.works.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.service.WorksWatchHistoryService;


/**
 * 观看历史记录
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-06 11:25:56
 */
@RestController
@RequestMapping("works/workswatchhistory")
@Api(tags = "观看历史记录")
public class WorksWatchHistoryController {
    @Autowired
    private WorksWatchHistoryService worksWatchHistoryService;



}
