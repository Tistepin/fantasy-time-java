package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.WorksUploadEntity;
import com.xu.works.vo.WorksVo;

import java.util.List;
import java.util.Map;

/**
 * 作品上传信息
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-01-10 14:34:10
 */
public interface WorksUploadService extends IService<WorksUploadEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WorksVo> getUserUploadWorks(Integer userID, Integer worksType, Integer page, Integer limit);
}

