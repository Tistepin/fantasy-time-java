package com.xu.works.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.works.dao.WorksWatchHistoryDao;
import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.service.UserService;
import com.xu.works.service.WorksService;
import com.xu.works.service.WorksWatchHistoryService;
import com.xu.works.to.SaveBookToShelfTo;
import com.xu.works.to.WorksHistoryTo;
import com.xu.works.vo.WorksInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;

import com.xu.works.dao.WorksBookshelfDao;
import com.xu.works.entity.WorksBookshelfEntity;
import com.xu.works.service.WorksBookshelfService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("worksBookshelfService")
public class WorksBookshelfServiceImpl extends ServiceImpl<WorksBookshelfDao, WorksBookshelfEntity> implements WorksBookshelfService {

    @Autowired
    WorksService worksService;
    @Autowired
    UserService userService;
    @Resource
    WorksWatchHistoryDao worksWatchHistoryDao;

    @Override
    public PageUtils queryPage(Integer worksType, Integer page, Integer limit, HttpServletRequest request) {
        // 获取该用户id
        UserEntity userEntity = userService.getUserEntity(request);
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        IPage<WorksBookshelfEntity> pages = this.page(
                new Query<WorksBookshelfEntity>().getPage(params),
                new QueryWrapper<WorksBookshelfEntity>().eq("works_type",worksType)
                        .eq("delete_status",1).eq("user_id",userEntity.getId())
        );
        List<WorksBookshelfEntity> records = pages.getRecords();
        List<WorksHistoryTo> collect = records.stream().map(item -> {
            WorksHistoryTo worksHistoryTo = new WorksHistoryTo();
            BeanUtils.copyProperties(item, worksHistoryTo);
            return worksHistoryTo;
        }).collect(Collectors.toList());
        // 获取作品最后一次的更新信息
        List<WorksHistoryTo> collect1 = collect.stream().peek(item -> {
            // 查询并存储
            CartoonWorksDetailsEntity cartoonWorksDetailsEntities = worksWatchHistoryDao.GetMaxWorksHistoryInfo(item.getWorksId());
            item.setCreateTime(cartoonWorksDetailsEntities.getCreateTime());
            item.setLatestChapter(cartoonWorksDetailsEntities.getCartoonChapterId());
        }).collect(Collectors.toList());
        IPage<WorksHistoryTo> pagess=new Page<>();
        pagess.setRecords(collect1);
        pagess.setTotal(pages.getTotal());
        pagess.setSize(pages.getSize());
        pagess.setCurrent(pages.getCurrent());
        return new PageUtils(pagess);
    }

    @Override
    public void saveBookToShelf(SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request) {
        WorksBookshelfEntity worksBookshelfEntity = new WorksBookshelfEntity();
        // 1.获取作品id查询作品详细信息
        Long worksId = saveBookToShelfTo.getWorksId();
        WorksEntity worksInfo = worksService.getById(worksId);
        // 2.获取用户ID
        UserEntity userEntity = userService.getUserEntity(request);
        Long userId = userEntity.getId();
        WorksBookshelfEntity worksBookshelfEntity1 = this.baseMapper.selectOne(new QueryWrapper<WorksBookshelfEntity>()
                .eq("works_id", worksId).eq("user_id", userId));
        if (worksBookshelfEntity1==null){
            // 添加
            // 3.填充数据
            BeanUtils.copyProperties(worksInfo,worksBookshelfEntity);
            worksBookshelfEntity.setUserId(userId);
            worksBookshelfEntity.setWorksUpdateTime(worksInfo.getEditTime());
            worksBookshelfEntity.setEditTime(null);
            worksBookshelfEntity.setCreateTime(null);
            this.save(worksBookshelfEntity);
        }else{
            // 修改
            worksBookshelfEntity1.setDeleteStatus(1L);
            worksBookshelfEntity1.setWorksUpdateTime(worksInfo.getEditTime());
            this.updateById(worksBookshelfEntity1);
        }

    }

    @Override
    public void unsubscribe(SaveBookToShelfTo saveBookToShelfTo, HttpServletRequest request) {
        Long worksId = saveBookToShelfTo.getWorksId();
        // 1.获取用户ID
        UserEntity userEntity = userService.getUserEntity(request);
        Long userId = userEntity.getId();
        WorksBookshelfEntity worksBookshelfEntity = this.getOne(new QueryWrapper<WorksBookshelfEntity>().eq("works_id", worksId)
                .eq("delete_status", 1).eq("user_id", userId));

        worksBookshelfEntity.setDeleteStatus(0L);
        worksBookshelfEntity.setEditTime(new Date(System.currentTimeMillis()));
        this.updateById(worksBookshelfEntity);
    }

    @Override
    public Boolean GetYesOrNoFavorite(Integer worksId, HttpServletRequest request) {
        UserEntity userEntity = userService.getUserEntity(request);
        WorksBookshelfEntity worksBookshelfEntity = this.baseMapper.selectOne(new QueryWrapper<WorksBookshelfEntity>().eq("works_id", worksId)
                .eq("user_id", userEntity.getId()).eq("delete_status",1));
        return worksBookshelfEntity!=null;
    }

}