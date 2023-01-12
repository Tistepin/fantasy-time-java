package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.NovelWorksDetailsEntity;

import java.util.Map;

/**
 * 小说作品章节数量
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface NovelWorksDetailsService extends IService<NovelWorksDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

