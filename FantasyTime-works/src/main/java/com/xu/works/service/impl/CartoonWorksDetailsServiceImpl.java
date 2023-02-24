package com.xu.works.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.security.utils.TokenManager;
import com.xu.works.dao.CartoonWorksDetailsDao;
import com.xu.works.entity.CartoonWorksDetailsEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.entity.WorksChapterDetailedViewingContentEntity;
import com.xu.works.entity.WorksEntity;
import com.xu.works.service.*;
import com.xu.works.to.CartoonWorksDetailsEntityTo;
import com.xu.works.to.ReviewCartoonWorksTo;
import com.xu.works.to.SaveBookToShelfTo;
import com.xu.works.utils.FileTOZip;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
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
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;


    @Autowired
    ThreadPoolExecutor executor;

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
    public List<CartoonWorksDetailsEntity> getWorksChapterList(Integer worksId, Integer order) {
        QueryWrapper<CartoonWorksDetailsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("works_id", worksId);
        if (order == 0) {
            // 升序
            queryWrapper.orderByAsc("cartoon_chapter_id");
        } else if (order == 1) {
            // 降序
            queryWrapper.orderByDesc("cartoon_chapter_id");
        }
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
            Long cartoonChapterId = cartoonWorksDetailsEntityTo.getCartoonChapterId();
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
                if (works.getWorksType() == 3) {
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
                Long i = 1L;
                for (String worksChapterLocation : cartoonWorksDetailsEntityTo.getWorksChapterLocations()) {
                    WorksChapterDetailedViewingContentEntity worksChapterDetailedViewingContentEntity = new WorksChapterDetailedViewingContentEntity();
                    worksChapterDetailedViewingContentEntity.setWorksChapterId(cartoonWorksDetailsEntity.getCartoonChapterId());
                    worksChapterDetailedViewingContentEntity.setUserId(userID);
                    worksChapterDetailedViewingContentEntity.setWorksId(worksId);
                    worksChapterDetailedViewingContentEntity.setWorksChapterLocation(worksChapterLocation);
                    if (works.getWorksType() == 3) {
                        worksChapterDetailedViewingContentEntity.setReviewStatus(1L);
                    } else {

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
    public List<ReviewCartoonWorksTo> getReviewList(Integer reviewStatus, Integer deleteStatus) {
        List<CartoonWorksDetailsEntity> cartoonWorksDetailsEntities = this.baseMapper.selectList(
                new QueryWrapper<CartoonWorksDetailsEntity>().eq("review_status", 0));
        List<ReviewCartoonWorksTo> cartoonWorksTos = cartoonWorksDetailsEntities.stream().map(item -> {
            ReviewCartoonWorksTo reviewCartoonWorksTo = new ReviewCartoonWorksTo();
            BeanUtils.copyProperties(item, reviewCartoonWorksTo);
            WorksEntity worksEntity = worksService.getOne(new QueryWrapper<WorksEntity>().eq("works_id", item.getWorksId()));
            reviewCartoonWorksTo.setWorksName(worksEntity.getWorksName());
            return reviewCartoonWorksTo;
        }).collect(Collectors.toList());
        return cartoonWorksTos;
    }

    @Override
    @Transactional
    public void review(ReviewCartoonWorksTo reviewCartoonWorksTo) {
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
             /*
             手动开启事务
             */
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // 事物隔离级别，开启新事务，这样会比较安全些。
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            // 获得事务状态
            TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
            try {
                // 更新漫画作品章节表的审核状态
                this.baseMapper.review(reviewCartoonWorksTo);
                // 再次更新作品图表的所有章节图片的审核状态
                worksChapterDetailedViewingContentService.reviewCartoonWorks(reviewCartoonWorksTo);
                dataSourceTransactionManager.commit(status);
            } catch (Exception e) {
                // 事务回滚
                dataSourceTransactionManager.rollback(status);
                throw new RuntimeException("review的runAsync线程执行失败", e);
            }
        }, executor);
        try {
            voidCompletableFuture1.get();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("审核操作失敗");
        }

        // TODO 发送RabbitMQ消息 告诉所有有依赖作品信息表的表更新时间 告知这个作品更新了
        SaveBookToShelfTo saveBookToShelfTo = new SaveBookToShelfTo();
        saveBookToShelfTo.setWorksId(reviewCartoonWorksTo.getWorksId());
        String s = JSONObject.toJSON(saveBookToShelfTo).toString();
        ;
        String uuid = UUID.randomUUID().toString();
        Message message = MessageBuilder.withBody(s.getBytes())
                .setMessageId(uuid)
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        rabbitTemplate.convertAndSend("works-update-exchange", "works.update", saveBookToShelfTo,
                new CorrelationData(uuid));

    }

    /**
     * 获取文件路径把章节文件压缩
     *
     * @return void
     * @Description
     * @Author F3863479
     * @Date 2023/2/20 上午 08:50
     * @Params [worksChapterId]
     */
    @Override
    public void getChapterWorksZip(Integer worksChapterId, String zipFilePath) {
        CartoonWorksDetailsEntity cartoonWorksDetailsEntity = this.baseMapper.selectById(worksChapterId);
        if (cartoonWorksDetailsEntity != null) {
            Long worksId = cartoonWorksDetailsEntity.getWorksId();
            WorksEntity worksEntity = worksService.getById(worksId);
            String sourceFilePath = "C:/Users/F3863479/Desktop/Test/" + worksEntity.getWorksName() + "/" + cartoonWorksDetailsEntity.getCartoonChapterId();
            // 判断存储的路径的最后以为是不是/ 或者 \ 是就不需要添加
            String substring = zipFilePath.substring(zipFilePath.length() - 2);
            if (substring.equals("\\")) {
                zipFilePath = zipFilePath + worksEntity.getWorksName() + "/" + cartoonWorksDetailsEntity.getCartoonChapterId();
                FileTOZip.fileToZip(sourceFilePath, zipFilePath, worksEntity.getWorksName());
            }else{
                zipFilePath = zipFilePath +"\\"+ worksEntity.getWorksName() + "\\" + cartoonWorksDetailsEntity.getCartoonChapterId();
                File file = new File(zipFilePath);
                boolean mkdirs = file.mkdirs();
                FileTOZip.fileToZip(sourceFilePath, zipFilePath, cartoonWorksDetailsEntity.getCartoonChapterName());
            }
        } else {
            throw new RuntimeException("没有章节信息");
        }

    }
}