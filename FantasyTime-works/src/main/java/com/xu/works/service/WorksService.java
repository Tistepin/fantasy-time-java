package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.WorksEntity;
import com.xu.works.to.ReviewWorksTo;
import com.xu.works.to.SaveBookToShelfTo;
import com.xu.works.to.WorksTo;
import com.xu.works.vo.WorksInfoVo;
import com.xu.works.vo.WorksVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 作品信息
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface WorksService extends IService<WorksEntity> {



    Map<String, List<WorksVo>> getRecentlyUpdated(Integer worksType);

    List<WorksVo> getClassicEnd(Integer worksType);

    List<WorksVo> getPopularSerial(Integer area, Integer worksType);

    Map<String,List<WorksVo>> getLeaderboard(Integer worksType);

    List<WorksVo> getLatestCreation(Integer worksType);

    List<WorksVo> getRecommendedToday(Integer worksType);

    PageUtils LatestCreationqueryPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit);

    PageUtils getRecentlyUpdatedPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit);

    PageUtils getPopularPage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit);

    PageUtils getHighScorePage(Integer worksType, Integer area, String worksCategory, Integer page, Integer limit);

    WorksInfoVo getWorksInfo(Integer worksId);

    List<WorksInfoVo> getRecommendedWorks(String worksCategory, Integer worksType);

    String getWorksCategoryName(String worksCategory, Integer worksType);

    List<WorksVo> getWorksInfo(List<Long> worksIds);

    void uploadWork(WorksTo worksTo, HttpServletRequest request) throws ExecutionException, InterruptedException;

    List<WorksVo> getWorksPage(Integer worksType, List<Long> worksList, Integer page, Integer limit);

    void UpEs(Long worksID) throws Exception;

    PageUtils queryPage(Integer page, Integer limit, Integer reviewStatus, Integer delete_status);

    void review(ReviewWorksTo reviewWorksTo, HttpServletRequest httpRequest) throws Exception;

    void WorksInBookshelfUpdate(SaveBookToShelfTo saveBookToShelfTo);
}

