package com.xu.works.dao;

import com.xu.works.entity.WorksChapterDetailedViewingContentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.works.to.ReviewCartoonWorksTo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
@Mapper
public interface WorksChapterDetailedViewingContentDao extends BaseMapper<WorksChapterDetailedViewingContentEntity> {

    void reviewCartoonWorksTo(@Param("reviewCartoonWorksTo") ReviewCartoonWorksTo reviewCartoonWorksTo);
}
