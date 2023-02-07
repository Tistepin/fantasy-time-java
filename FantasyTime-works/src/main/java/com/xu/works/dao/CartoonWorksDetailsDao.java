package com.xu.works.dao;

import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.works.to.ReviewCartoonWorksTo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 漫画作品章节数量
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface CartoonWorksDetailsDao extends BaseMapper<CartoonWorksDetailsEntity> {

    void review(@Param("reviewCartoonWorksTo") ReviewCartoonWorksTo reviewCartoonWorksTo);
}
