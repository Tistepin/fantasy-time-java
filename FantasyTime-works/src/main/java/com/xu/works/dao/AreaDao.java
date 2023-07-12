package com.xu.works.dao;

import com.xu.works.entity.AreaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.works.vo.DownListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 国家区域
 * 
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
@Mapper
public interface AreaDao extends BaseMapper<AreaEntity> {

    List<DownListVo> getList();
}
