package com.xu.works.controller;

import com.xu.common.utils.R;
import com.xu.works.entity.WorksWatchHistoryEntity;
import com.xu.works.service.UserService;
import com.xu.works.to.WorksHistoryTo;
import com.xu.works.to.WorksTo;
import com.xu.works.to.WorksWatchHistoryTo;
import com.xu.works.vo.WorksWatchHistoryVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xu.works.service.WorksWatchHistoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;


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
    @Autowired
    private UserService userService;

    /**
     * @Description 新增或者修改历史记录
     * @Author F3863479
     * @Date 2023/2/13 上午 10:45
     * @Params [worksWatchHistoryTo, request]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "新增或者修改历史记录", notes = "新增或者修改历史记录")
    @ApiImplicitParam(name = "worksWatchHistoryTo", value = "历史记录To ", paramType = "body", required = true,
            dataType = "WorksWatchHistoryTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/record")
    public R Record(@RequestBody WorksWatchHistoryTo worksWatchHistoryTo,HttpServletRequest request) {
            worksWatchHistoryService.Record(worksWatchHistoryTo,request);
        return R.ok();
    }

    /**
     * @Description 獲取歷史記錄
     * @Author F3863479
     * @Date 2023/2/13 上午 10:45
     * @Params [worksWatchHistoryTo, request]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "獲取歷史記錄", notes = "獲取歷史記錄")
//    @ApiImplicitParam(name = "worksWatchHistoryTo", value = "历史记录To ", paramType = "body", required = true,
//            dataType = "WorksWatchHistoryTo")
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @PostMapping("/getWorksWatchHistory")
    public R GetWorksWatchHistory(HttpServletRequest request) {
        List<WorksHistoryTo>WorksWatchHistoryEntityS=  worksWatchHistoryService.GetWorksWatchHistory(request);
        return R.ok().data("WorksWatchHistory",WorksWatchHistoryEntityS);
    }

    /**
     * @Description 获取该作品看到那了的连接
     * @Author F3863479
     * @Date 2023/2/13 上午 10:45
     * @Params [worksWatchHistoryTo, request]
     * @return com.xu.common.utils.R
     *
     */
    @ApiOperation(value = "获取该作品看到那了的连接", notes = "获取该作品看到那了的连接")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "WorksId", value = "作品ID", paramType = "query", dataType = "Integer", defaultValue = "1", allowEmptyValue = true)
    })
    @ApiResponses({
            @ApiResponse(code = 20000, message = "請求成功", response = R.class)
    })
    @GetMapping("/getRecordInfo")
    public R GetRecordInfo(
            @RequestParam Integer WorksId,
            HttpServletRequest request) {
      WorksWatchHistoryVo worksWatchHistoryVo= worksWatchHistoryService.GetRecordInfo(WorksId,request);
      if (worksWatchHistoryVo!=null){
          return R.ok().data("data",worksWatchHistoryVo);
      }
        return R.error();
    }
}
