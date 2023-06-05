package org.ytq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.ytq.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
