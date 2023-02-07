package com.xu.works.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.common.utils.MD5;
import com.xu.common.utils.PageUtils;
import com.xu.common.utils.Query;
import com.xu.security.utils.TokenManager;
import com.xu.works.constant.UserEnum;
import com.xu.works.dao.UserDao;
import com.xu.works.entity.UserEntity;
import com.xu.works.service.UserService;
import com.xu.works.to.UserTo;
import com.xu.works.to.userUpdateTo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    TokenManager tokenManager;
    @Autowired


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @Description 注册用户信息
     * @Author F3863479
     * @Date 2023/1/10 上午 10:21
     * @Params [userTou]
     * @return void
     *
     */
    @Override
    public void saveUserInfo(UserTo userTo) {
        // 获取用户信息后先加密密码 MD5加密
        String encrypt = MD5.encrypt(userTo.getPassword());
        // 把TO数据转换到数据库实体类上
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userTo,userEntity);
        // 设置用户来源
        userEntity.setSourceType(UserEnum.USER_SOURCE_TYPE_OFFICIAL.getCode());
        // 设置加密密码
        userEntity.setPassword(encrypt);
        // 默认头像
        userEntity.setHeader("");
        baseMapper.insert(userEntity);
    }

    /**
     * @Description 查询用户信息 username可以是手机号和邮箱 而却都是唯一的
     * @Author F3863479
     * @Date 2023/1/10 下午 02:14
     * @Params [username]
     * @return com.xu.search.entity.UserEntity
     *
     */
    @Override
    public UserEntity getUserInfo(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().eq("phone", username).or().eq("email", username)
                .or().eq("username", username);
        return  this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public UserEntity getUserEntity(HttpServletRequest request) {
        String token = request.getHeader("FantasyTimetoken");
        String userFromToken = tokenManager.getUserFromToken(token);
        UserEntity userInfo = this.getUserInfo(userFromToken);
        return userInfo;
    }

    @Override
    public void updateUser(userUpdateTo userUpdateTo) {
        this.baseMapper.updateUser(userUpdateTo);
    }

}