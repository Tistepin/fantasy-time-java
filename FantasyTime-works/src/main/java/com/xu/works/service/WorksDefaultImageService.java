package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.WorksDefaultImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 作品封面图片服务请求路径存储
 *
 * @author xuguoji
 * @email 2532878663@qq.com
 * @date 2023-02-06 10:47:44
 */
public interface WorksDefaultImageService extends IService<WorksDefaultImageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    WorksDefaultImageEntity getWorksDefaultImage(Long worksId);

}

