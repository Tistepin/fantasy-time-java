package com.xu.works.service.impl;

import com.xu.works.entity.UserEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.service.UserService;
import com.xu.works.service.WorksService;
import com.xu.works.to.SaveBookToShelfTo;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("worksBookshelfService")
public class WorksBookshelfServiceImpl extends ServiceImpl<WorksBookshelfDao, WorksBookshelfEntity> implements WorksBookshelfService {

    @Autowired
    WorksService worksService;
    @Autowired
    UserService userService;

    @Override
    public PageUtils queryPage(Integer worksType, Integer page, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        IPage<WorksBookshelfEntity> pages = this.page(
                new Query<WorksBookshelfEntity>().getPage(params),
                new QueryWrapper<WorksBookshelfEntity>().eq("works_type",worksType)
                        .eq("delete_status",1)
        );

        return new PageUtils(pages);
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
        // 3.填充数据
        BeanUtils.copyProperties(worksInfo,worksBookshelfEntity);
        worksBookshelfEntity.setUserId(userId);
        worksBookshelfEntity.setWorksUpdateTime(worksInfo.getEditTime());
        worksBookshelfEntity.setEditTime(null);
        worksBookshelfEntity.setCreateTime(null);
        this.save(worksBookshelfEntity);
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

}