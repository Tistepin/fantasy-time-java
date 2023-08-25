package com.xu.works.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.common.utils.PageUtils;
import com.xu.works.entity.ContactEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户关系表 谁是谁的好友 谁关注的谁
 *
 * @author xuguoji
 * @email 2532878663
 * @date 2023-08-24 10:50:16
 */
public interface ContactService extends IService<ContactEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ArrayList<HashMap<String, Object>> GetContactState(HttpServletRequest request);
}

