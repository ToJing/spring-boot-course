package com.to.jing.course.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.to.jing.course.sdk.domain.Response;
import com.to.jing.course.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CachePassController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cache/pass/{id}" ,produces = "application/json;charset=UTF-8")
    public String cachePass(@PathVariable("id") Integer id){

        //定义接口返回的数据格式
        Response response = Response.SUCCESS();
        try {
            response.setData(userService.getUserInfo(id));
        }catch (Exception e){
            response.failed("失败"+e.getMessage());
        }
        return JSON.toJSONString(response, SerializerFeature.BrowserCompatible);
    }
}
