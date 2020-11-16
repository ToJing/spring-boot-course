package com.to.jing.course.service;

import com.to.jing.course.sdk.domain.User;

public interface UserService {
    User findUserById(Integer id);
    User getUserInfo(Integer id);
}
