package com.to.jing.course.server.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setObject(String key,Object o){
        redisTemplate.opsForValue().set(key,o);
    }

    public void setString(String key,Object o){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(o));
    }
    public void setString(String key, Object o, Long time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(o),time,timeUnit);
    }
    public Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T getString(String key , Class<T> clazz){
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null || Strings.isNullOrEmpty(value)){
            return null;
        }
        return JSON.parseObject(value,clazz);
    }

    public Boolean hasKey(String key){
        return  redisTemplate.hasKey(key);
    }
}
