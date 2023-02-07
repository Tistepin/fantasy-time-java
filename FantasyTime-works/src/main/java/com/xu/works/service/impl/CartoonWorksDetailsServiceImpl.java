package com.xu.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.works.dao.CartoonWorksDetailsDao;
import com.xu.works.entity.*;
import com.xu.works.service.*;
import com.xu.works.to.CartoonWorksDetailsEntityTo;
import com.xu.security.utils.TokenManager;
import com.xu.works.to.ReviewCartoonWorksTo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("cartoonWorksDetailsService")
public class CartoonWorksDetailsServiceImpl extends ServiceImpl<CartoonWorksDetailsDao, CartoonWorksDetailsEntity> implements CartoonWorksDetailsService {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;
    @Autowired
    private WorksUploadService worksUploadService;
    @Autowired
    private WorksChapterDetailedViewingContentService worksChapterDetailedViewingContentService;
    @Autowired
    private WorksService worksService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CartoonWorksDetailsEntity> page = this.page(
                new Query<CartoonWorksDetailsEntity>().getPage(params),
                new QueryWrapper<CartoonWorksDetailsEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @return java.util.List<com.xu.search.entity.CartoonWorksDetailsEntity>
     * @Description 作品章节目录查询
     * @Author F3863479
     * @Date 2023/1/9 下午 03:19
     * @Params [worksId]
     */
    @Override
    public List<CartoonWorksDetailsEntity> getWorksChapterList(Integer worksId) {
        QueryWrapper<CartoonWorksDetailsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("works_id", worksId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public String saveUploadChapterData(CartoonWorksDetailsEntityTo cartoonWorksDetailsEntityTo, HttpServletRequest request) {
        // 先查询该漫画是否以及审核完成
        Long worksId = cartoonWorksDetailsEntityTo.getWorksId();
        WorksEntity works = worksService.getOne(new QueryWrapper<WorksEntity>().eq("works_id", worksId));
        if (works != null && works.getReviewStatus() == 1) {
            // 保存章节信息
            String cartoonChapterId = cartoonWorksDetailsEntityTo.getCartoonChapterId();
            // 判断该章节是否已经存在
            CartoonWorksDetailsEntity cartoonWorksDetailsEntity1 = this.baseMapper.selectOne(new QueryWrapper<CartoonWorksDetailsEntity>()
                    .eq("works_id", worksId).eq("cartoon_chapter_id", cartoonChapterId)
            );
            if (cartoonWorksDetailsEntity1 == null) {
                CartoonWorksDetailsEntity cartoonWorksDetailsEntity = new CartoonWorksDetailsEntity();
                cartoonWorksDetailsEntity.setWorksId(worksId);
                cartoonWorksDetailsEntity.setCartoonChapterId(cartoonChapterId);
                cartoonWorksDetailsEntity.setCartoonChapterName(cartoonWorksDetailsEntityTo.getCartoonChapterName());
                int size = cartoonWorksDetailsEntityTo.getWorksChapterLocations().size();
                cartoonWorksDetailsEntity.setCartoonPages(String.valueOf(size));
                // 如果是插画直接审核完成
                if (works.getWorksType()==3){
                    cartoonWorksDetailsEntity.setReviewStatus(1L);
                }
                baseMapper.insert(cartoonWorksDetailsEntity);
                // 保存 该章节的内容显示的位置信息
                // 获取用户 id
                ArrayList<WorksChapterDetailedViewingContentEntity> worksChapterDetailedViewingContentEntitieLsit = new ArrayList<>();
                String token = request.getHeader("FantasyTimetoken");
                String userFromToken = tokenManager.getUserFromToken(token);
                UserEntity userInfo = userService.getUserInfo(userFromToken);
                Long userID = userInfo.getId();
                Long i=1L;
                for (String worksChapterLocation : cartoonWorksDetailsEntityTo.getWorksChapterLocations()) {
                    WorksChapterDetailedViewingContentEntity worksChapterDetailedViewingContentEntity = new WorksChapterDetailedViewingContentEntity();
                    worksChapterDetailedViewingContentEntity.setWorksChapterId(cartoonWorksDetailsEntity.getId());
                    worksChapterDetailedViewingContentEntity.setUserId(userID);
                    worksChapterDetailedViewingContentEntity.setWorksId(worksId);
                    worksChapterDetailedViewingContentEntity.setWorksChapterLocation(worksChapterLocation);
                    if (works.getWorksType()==3){
                        worksChapterDetailedViewingContentEntity.setReviewStatus(1L);
                    }else{

                        worksChapterDetailedViewingContentEntity.setReviewStatus(0L);
                    }
                    worksChapterDetailedViewingContentEntity.setImageId(i);
                    worksChapterDetailedViewingContentEntitieLsit.add(worksChapterDetailedViewingContentEntity);
                    i++;
                }
                worksChapterDetailedViewingContentService.saveBatch(worksChapterDetailedViewingContentEntitieLsit);

                return "OK";
            }
            return "FAIL";
        } else {
            return "FAIL";
        }
    }

    @Override
    public  List<ReviewCartoonWorksTo>  getReviewList() {
        List<CartoonWorksDetailsEntity> cartoonWorksDetailsEntities = this.baseMapper.selectList(
                new QueryWrapper<CartoonWorksDetailsEntity>().eq("review_status", 0));
        List<ReviewCartoonWorksTo> cartoonWorksTos = cartoonWorksDetailsEntities.stream().map(item -> {
            ReviewCartoonWorksTo reviewCartoonWorksTo=new ReviewCartoonWorksTo();
            BeanUtils.copyProperties(item,reviewCartoonWorksTo);
            WorksEntity worksEntity = worksService.getOne(new QueryWrapper<WorksEntity>().eq("works_id", item.getWorksId()));
            reviewCartoonWorksTo.setWorksName(worksEntity.getWorksName());
            return reviewCartoonWorksTo;
        }).collect(Collectors.toList());
        return cartoonWorksTos;
    }

    @Override
    @Transactional
    public void review(ReviewCartoonWorksTo reviewCartoonWorksTo) {
        this.baseMapper.review(reviewCartoonWorksTo);
        worksChapterDetailedViewingContentService.reviewCartoonWorks(reviewCartoonWorksTo);
    }

}