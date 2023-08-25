package com.xu.works.dao;

import com.xu.works.entity.ContactEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关系表 谁是谁的好友 谁关注的谁
 * 
 * @author xuguoji
 * @email 2532878663
 * @date 2023-08-24 10:50:16
 */
@Mapper
public interface ContactDao extends BaseMapper<ContactEntity> {
	
}
