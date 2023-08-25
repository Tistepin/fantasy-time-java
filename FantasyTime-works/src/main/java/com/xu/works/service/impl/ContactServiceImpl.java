package com.xu.works.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.works.dao.ContactDao;
import com.xu.works.entity.ContactEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.service.ContactService;
import com.xu.works.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("contactService")
public class ContactServiceImpl extends ServiceImpl<ContactDao, ContactEntity> implements ContactService {

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContactEntity> page = this.page(
                new Query<ContactEntity>().getPage(params),
                new QueryWrapper<ContactEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ArrayList<HashMap<String, Object>> GetContactState(HttpServletRequest request) {

        // 获取关系列表
        UserEntity userEntity = userService.getUserEntity(request);
        List<ContactEntity> contactEntityList = this.baseMapper.selectList(new QueryWrapper<ContactEntity>().eq("owner_id", userEntity.getId()));
        // 获取 好友状态
        // 获取ids
        List<Long> Ids = contactEntityList.stream().map(ContactEntity::getTargetId).collect(Collectors.toList());
        // 获取好友信息
        List<UserEntity> userEntities = userService.listByIds(Ids);
        // 访问go 查找状态
        String s = httpGet("http://localhost:8883/GetContactStates?ids=" + Ids);
        JSONObject jsonObject = JSONObject.parseObject(s, Feature.OrderedField);
        String data = jsonObject.get("Data").toString();
        // json 转换为map
        HashMap<String, Object> map = JSON.parseObject(data, HashMap.class);
        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        map.forEach((key, value) -> {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("UserID", userEntity.getId());
            userEntities.forEach(item -> {
                if (item.getId() == Long.parseLong(key)) {
                    map1.put("targetInfo", item);
                }
            });
            map1.put("state", value);
            map1.put(key, value);
            hashMaps.add(map1);
        });
        return hashMaps;
    }

    public static String httpGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        return result;
    }


}