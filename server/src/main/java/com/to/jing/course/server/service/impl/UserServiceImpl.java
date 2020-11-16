package com.to.jing.course.server.service.impl;

import com.to.jing.course.dao.UserDao;
import com.to.jing.course.sdk.domain.User;
import com.to.jing.course.server.common.RedisPrefix;
import com.to.jing.course.server.service.RedisService;
import com.to.jing.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public User getUserInfo(Integer id) {
        final String key = RedisPrefix.COURSE_CACHE_USER + id;
        User user = null;
        if (redisService.hasKey(key)){
            log.info("从缓存中获取用户信息");
            user = redisService.getString(key,User.class);
        }else {
            log.info("从数据库中获取用户信息");
            user = userDao.findUserById(id);
            if (Objects.isNull(user)){
                //用户不存在，缓存其空对象
                user = User.Null();
                user.setUsername("无效用户");
                redisService.setString(key,user,30L, TimeUnit.MINUTES);
            }else{
                redisService.setString(key,user);
            }
        }
        return user;
    }
}
