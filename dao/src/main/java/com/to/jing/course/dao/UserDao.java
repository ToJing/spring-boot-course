package com.to.jing.course.dao;

import com.to.jing.course.sdk.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findByUser(User user);
    User findUserById(Integer id);
}
