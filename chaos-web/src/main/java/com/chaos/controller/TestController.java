package com.chaos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chaos.model.User;
import com.chaos.mq.MessageConstants;
import com.chaos.util.ControllerUtil;
import com.chaos.util.RabbitMqProductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-04-30 16:09
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    public RabbitMqProductor rabbitMqProductor;

    @RequestMapping("/testMQ")
    @ResponseBody
    public Object testMQ(HttpServletRequest request, HttpServletResponse response,@RequestBody String jsonStr) {
        User user = ControllerUtil.strJsonToObject(jsonStr, User.class);
        try {
            JSONObject map = new JSONObject();
            map.put("name", user.getUserName());
            rabbitMqProductor.send(MessageConstants.EXCHANGE_NAME,MessageConstants.CHAOS_QUEUE_NAME,MessageConstants.ROUTING_KEY_NAME);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
