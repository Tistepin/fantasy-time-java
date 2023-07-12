package com.xu.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.works.constant.WorksEnum;
import com.xu.works.dao.PopularityDao;
import com.xu.works.entity.PopularityEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.service.PopularityService;
import com.xu.works.service.UserService;
import com.xu.works.service.WorksService;
import com.xu.works.to.worksPopularityTodayTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;


@Service("popularityService")
public class PopularityServiceImpl extends ServiceImpl<PopularityDao, PopularityEntity> implements PopularityService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    UserService userService;
    @Autowired
    WorksService worksService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PopularityEntity> page = this.page(
                new Query<PopularityEntity>().getPage(params),
                new QueryWrapper<PopularityEntity>()
        );

        return new PageUtils(page);
    }


    /**
     * @return java.util.List<java.lang.String>
     * @Description 在人气表中 根据作品的分类 漫画或者小说 获取完本的 最高人气的前一百本  然后 sql算法拿出十本 的id
     * @Author F3863479
     * @Date 2023/1/5 下午 05:30
     * @Params [worksType]
     */
    @Override
    public List<String> getPopularityTop(Integer worksType) {
        return this.baseMapper.getPopularityTop(worksType);
    }

    /**
     * @return void
     * @Description 今日添加作品人气 第二天清空
     * @Author F3863479
     * @Date 2023/1/7 上午 11:03
     * @Params [worksId, userId]
     */
    @Override
    @Transactional
    public void saveWorksPopularity(Integer worksType, Integer worksId,
                                    HttpServletRequest request) {
        UserEntity userEntity = userService.getUserEntity(request);
        Long userId = userEntity.getId();
        // 判断是漫画还是小说
        if (worksType.equals(WorksEnum.Works_mh_Enum.works_mh.getCode())) {
            BoundSetOperations<String, String> mhSetBound = stringRedisTemplate.boundSetOps(WorksEnum.Works_mh_Enum.works_popularity_today_user.getMsg() + "_"+worksId);
            // 先判断这个作品 该用户有没有添加过人气
            Boolean member = mhSetBound.isMember(String.valueOf(userId));
            // true 有 false 没有
            if (!member) {
                // 查询人气表有无这条数据
                PopularityEntity data = this.getOne(new QueryWrapper<PopularityEntity>().eq("works_id", worksId));
                boolean bool =false;
                if (data==null){
                    // 添加人气表默认数据
                    WorksEntity worksEntity = worksService.getById(worksId);
                    PopularityEntity popularityEntity=new PopularityEntity();
                    popularityEntity.setWorksId(Long.valueOf(worksId));
                    popularityEntity.setName(worksEntity.getWorksName());
                    popularityEntity.setWorksType(worksEntity.getWorksType().intValue());
                    popularityEntity.setWorksStatus(worksEntity.getWorksStatus());
                    popularityEntity.setWorksPopularityCount(0L);
                    popularityEntity.setWorksPopularityToday(0L);
                    popularityEntity.setWorksPopularityThreeDays(0L);
                    popularityEntity.setWorksPopularityThisweek(0L);
                    popularityEntity.setWorksPopularityThismonth(0L);
                    popularityEntity.setDeleteStatus(worksEntity.getDeleteStatus());
                    bool = this.baseMapper.insert(popularityEntity) >= 1;
                }else{
                    data.setWorksPopularityCount(data.getWorksPopularityCount()+1L);
                    data.setWorksPopularityToday(data.getWorksPopularityToday()+1L);
                    data.setWorksPopularityThreeDays(data.getWorksPopularityThreeDays()+1L);
                    data.setWorksPopularityThisweek(data.getWorksPopularityThisweek()+1L);
                    data.setWorksPopularityThismonth(data.getWorksPopularityThismonth()+1L);
                    data.setEditTime(new Date(System.currentTimeMillis()));
                    bool = this.baseMapper.updateById(data)>=1;
                }

                if (bool){
                    //  没有则给作品添加人气
                    stringRedisTemplate.opsForZSet().incrementScore(WorksEnum.Works_mh_Enum.works_popularity_today_mh.getMsg(), String.valueOf(worksId),1);
                    mhSetBound.add(String.valueOf(userId));
                }

            }
        } else if (worksType.equals(WorksEnum.Works_xs_Enum.works_xs.getCode())) {
            BoundSetOperations<String, String> mhSetBound = stringRedisTemplate.boundSetOps(WorksEnum.Works_xs_Enum.works_popularity_today_xs.getMsg()  + "_"+worksId);
            // 先判断这个作品 该用户有没有添加过人气
            Boolean member = mhSetBound.isMember(String.valueOf(userId));
            // true 有 false 没有
            if (!member) {
                //  没有则给作品添加人气
                stringRedisTemplate.opsForZSet().incrementScore(WorksEnum.Works_xs_Enum.works_popularity_today_xs.getMsg(), String.valueOf(worksId),1);
                mhSetBound.add(String.valueOf(userId));
            }
        }

    }

    /**
     * @Description 根据worksID更新 三日  本周  本月加上人气
     * @Author F3863479
     * @Date 2023/1/7 下午 02:07
     * @Params [worksPopularityTodayTos]
     * @return void
     *
     */
    @Override
    public Integer updateByWorksId(ArrayList<worksPopularityTodayTo> worksPopularityTodayTos) {
       return this.baseMapper.updateByWorksId(worksPopularityTodayTos);
    }

}