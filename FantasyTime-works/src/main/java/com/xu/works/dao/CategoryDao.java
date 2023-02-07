package com.xu.works.dao;

import com.xu.works.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 作品类型
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    String getCategorysName(@Param("catIds") String[] catIds, @Param("worksType") Integer worksType);
}
