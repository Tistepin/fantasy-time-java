package com.xu.search.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.xu.search.vo.WorksVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.search.entity.WorksWatchHistoryEntity;
import com.xu.search.service.WorksWatchHistoryService;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.R;



/**
 * 观看历史记录
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-06 11:25:56
 */
@RestController
@RequestMapping("search/workswatchhistory")
public class WorksWatchHistoryController {
    @Autowired
    private WorksWatchHistoryService worksWatchHistoryService;



}
