package com.xu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.search.entity.CartoonWorksDetailsEntity;
import com.xu.search.to.CartoonWorksDetailsEntityTo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 漫画作品章节数量
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-05 15:51:40
 */
public interface CartoonWorksDetailsService extends IService<CartoonWorksDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CartoonWorksDetailsEntity> getWorksChapterList(Integer worksId);

    String saveUploadChapterData(CartoonWorksDetailsEntityTo cartoonWorksDetailsEntityTo, HttpServletRequest request);
}

