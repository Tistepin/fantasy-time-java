package com.xu.works.dao;

import com.xu.works.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.works.vo.DownListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<DownListVo> GetCategoryDownList(@Param("worksTyp") Integer worksTyp);
}
