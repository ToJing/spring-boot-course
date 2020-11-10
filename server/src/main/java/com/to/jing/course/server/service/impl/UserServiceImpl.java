package com.to.jing.course.server.service.impl;

import com.to.jing.course.dao.UserDao;
import com.to.jing.course.sdk.domain.User;
import com.to.jing.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }
}
