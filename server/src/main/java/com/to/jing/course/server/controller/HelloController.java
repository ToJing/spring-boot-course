package com.to.jing.course.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.to.jing.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return JSON.toJSONString(userService.findUserById(1), SerializerFeature.BrowserCompatible);
    }
}
