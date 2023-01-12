package com.xu.search.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.search.constant.AreaEnum;
import com.xu.search.dao.AreaDao;
import com.xu.search.entity.AreaEntity;
import com.xu.search.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("areaService")
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaEntity> implements AreaService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AreaEntity> page = this.page(
                new Query<AreaEntity>().getPage(params),
                new QueryWrapper<AreaEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @return java.util.List<com.xu.search.entity.AreaEntity>
     * @Description 获取国家地区  因为国家地区这东西不会变 就有缓存时间为永久 新增国家地区的时候删除缓存就OK
     * @Author F3863479
     * @Date 2023/1/7 下午 04:09
     * @Params [worksType]
     */
    @Override
    public List<AreaEntity> getarea() {
        BoundValueOperations<String, String> GetAreaOps = stringRedisTemplate.boundValueOps(AreaEnum.Area_Cache_key.getMsg());
        String Area = GetAreaOps.get();
        List<AreaEntity> areaEntities = JSONObject.parseArray(Area, AreaEntity.class);
        if (areaEntities == null || areaEntities.size() == 0) {
            areaEntities = this.baseMapper.selectList(null);
            String s = JSONObject.toJSON(areaEntities).toString();
            GetAreaOps.set(s);
        }
        return areaEntities;
    }

}