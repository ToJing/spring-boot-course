package com.to.jing.course.dao;

import com.to.jing.course.sdk.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findUserById(Integer id);
}
