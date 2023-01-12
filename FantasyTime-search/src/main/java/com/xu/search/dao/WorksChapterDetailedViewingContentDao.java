package com.xu.search.dao;

import com.xu.search.entity.WorksChapterDetailedViewingContentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作品章节详细观看内容 例如小说第几章位置,漫画第一话的第一张图
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
@Mapper
public interface WorksChapterDetailedViewingContentDao extends BaseMapper<WorksChapterDetailedViewingContentEntity> {
	
}
