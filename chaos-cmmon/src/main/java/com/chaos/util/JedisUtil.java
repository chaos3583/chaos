package com.chaos.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-09-12 16:45
 **/
public class JedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String k,String v){
        stringRedisTemplate.opsForValue().set(k,v);
    }

    public void multi(){
        stringRedisTemplate.multi();
    }

    public void exec(){
        stringRedisTemplate.exec();
    }
}
