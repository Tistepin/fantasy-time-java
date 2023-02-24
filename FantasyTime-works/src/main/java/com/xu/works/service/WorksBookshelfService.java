package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.WorksBookshelfEntity;
import com.xu.works.to.SaveBookToShelfTo;

import javax.servlet.http.HttpServletRequest;

/**
 * 书架
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-14 13:52:45
 */
public interface WorksBookshelfService extends IService<WorksBookshelfEntity> {

    PageUtils queryPage(Integer worksType, Integer page, Integer limit);

    void saveBookToShelf(SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request);

    void unsubscribe(SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request);
}

