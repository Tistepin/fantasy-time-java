package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 作品类型
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> getCategory(Integer worksType);

    String getCategorysName(String[] split, Integer worksType);
}

