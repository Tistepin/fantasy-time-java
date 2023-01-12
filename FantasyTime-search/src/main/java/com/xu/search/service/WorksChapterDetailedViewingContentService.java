package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.WorksChapterDetailedViewingContentEntity;

import java.util.Map;

/**
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
public interface WorksChapterDetailedViewingContentService extends IService<WorksChapterDetailedViewingContentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    WorksChapterDetailedViewingContentEntity getImageData(Integer worksId, Integer worksChapterId, Integer imageId);
}

