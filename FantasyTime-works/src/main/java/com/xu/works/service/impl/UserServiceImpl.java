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
import com.xu.works.entity.RoleUserEntity;
import com.xu.works.entity.UserEntity;
import com.xu.works.service.RoleUserService;
import com.xu.works.service.UserService;
import com.xu.works.to.UserTo;
import com.xu.works.to.userUpdateTo;
import com.xu.works.vo.UpdateUserPwd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    TokenManager tokenManager;
    @Autowired
    RoleUserService roleUserService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @return void
     * @Description 注册用户信息
     * @Author F3863479
     * @Date 2023/1/10 上午 10:21
     * @Params [userTou]
     */
    @Override
    public void saveUserInfo(UserTo userTo) {
        // 获取用户信息后先加密密码 MD5加密
        String encrypt = MD5.encrypt(userTo.getPassword());
        // 把TO数据转换到数据库实体类上
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userTo, userEntity);
        // 设置用户来源
        userEntity.setSourceType(UserEnum.USER_SOURCE_TYPE_OFFICIAL.getCode());
        // 设置加密密码
        userEntity.setPassword(encrypt);
        // 默认头像
        userEntity.setHeader("");
        baseMapper.insert(userEntity);
        RoleUserEntity roleUserEntity = new RoleUserEntity();
        roleUserEntity.setUid(userEntity.getId());
        roleUserEntity.setRid(1L);
        roleUserService.save(roleUserEntity);
    }

    /**
     * @return com.xu.search.entity.UserEntity
     * @Description 查询用户信息 username可以是手机号和邮箱 而却都是唯一的
     * @Author F3863479
     * @Date 2023/1/10 下午 02:14
     * @Params [username]
     */
    @Override
    public UserEntity getUserInfo(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().eq("phone", username).or().eq("email", username)
                .or().eq("username", username);
        return this.baseMapper.selectOne(queryWrapper);
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
        // 1.获取手机号 查询User数据
        String phone = userUpdateTo.getPhone();
        UserEntity userEntity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("phone", phone));
        String username = userUpdateTo.getUsername();
        if (!StringUtils.isEmpty(username)) {
            userEntity.setUsername(username);
        }
        String city = userUpdateTo.getCity();
        if (!StringUtils.isEmpty(username)) {
            userEntity.setCity(city);
        }
        Date birth = userUpdateTo.getBirth();
        if (!StringUtils.isEmpty(birth)) {
            userEntity.setBirth(birth);
        }
        String email = userUpdateTo.getEmail();
        if (!StringUtils.isEmpty(email)) {
            userEntity.setEmail(email);
        }
        String header = userUpdateTo.getHeader();
        if (!StringUtils.isEmpty(header)) {
            userEntity.setHeader(header);
        }
        String job = userUpdateTo.getJob();
        if (!StringUtils.isEmpty(job)) {
            userEntity.setJob(job);
        }
        String nickname = userUpdateTo.getNickname();
        if (!StringUtils.isEmpty(nickname)) {
            userEntity.setNickname(nickname);
        }
        String sign = userUpdateTo.getSign();
        if (!StringUtils.isEmpty(sign)) {
            userEntity.setSign(sign);
        }
        Integer gender = userUpdateTo.getGender();
        if (!(gender == null)) {
            userEntity.setGender(gender);
        }
        this.baseMapper.updateById(userEntity);
    }

    @Override
    public void updateUserPwd(UpdateUserPwd updateUserPwd) throws Exception {
        // 获取手机号
        String phone = updateUserPwd.getPhone();
        // 获取旧密码
        String pwd1 = updateUserPwd.getPwd1();
        // 获取新密码
        String pwd2 = updateUserPwd.getPwd2();
        // 密码对比是否一样
        if (pwd1.equals(pwd2)) {
            throw new Exception("密码一样");
        }
        ;
        // 查询手机号用户信息
        UserEntity userEntity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("phone", phone));
        String encrypt = MD5.encrypt(pwd1);
        // 密码对比 是否是这个用户的
        if (!userEntity.getPassword().equals(encrypt)) {
            throw new Exception("密码错误");
        }
        // 更新密码
        userEntity.setPassword(encrypt);
        this.baseMapper.updateById(userEntity);
    }

}