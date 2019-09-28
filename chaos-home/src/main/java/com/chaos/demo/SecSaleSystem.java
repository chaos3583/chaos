package com.chaos.demo;

import com.chaos.util.JedisUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.concurrent.*;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-09-12 15:37
 **/
public class SecSaleSystem {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,100, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5));
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.set("k","v");
//        System.out.println("黒巧克力风味糖酱1000ml/瓶".equals("黑巧克力风味糖酱1000ml/瓶"));

    }

    static class ClientThread extends Thread{
        String clientName = "";
        public ClientThread(int num){
            clientName="顾客："+num;
        }
        JedisUtil jedisUtil = new JedisUtil();
        @Override
        public void run() {

            while (true){

            }

        }
    }
}
