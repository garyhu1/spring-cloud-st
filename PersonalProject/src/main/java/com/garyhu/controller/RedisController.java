package com.garyhu.controller;

import com.garyhu.listener.MyRedisChannelListener;
import com.garyhu.pojo.Animal;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Administrator
 * @since : 2018/11/4
 * @decripetion : 测试Redis StringRedisTemplate
 * StringRedisTemplate操作的都是字符串
 **/
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    StringRedisTemplate srt;

    // 必须使用@Qualifier("redisTemplate")，否则Spring boot 会误认为有可能是StringRedisTemplate而报错
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisClient;

    @Autowired
    @Qualifier("strKeyRedisTemplate")
    private RedisTemplate redis;

    // 设置单一的字符串操作
    @GetMapping("/setget")
    public @ResponseBody String testRedis(@RequestParam("param") String param){
        srt.opsForValue().set("testparam",param);
        String testparam = srt.opsForValue().get("testparam");
        return testparam;
    }

    // 设置字符串集合
    @GetMapping("list")
    public @ResponseBody List<String> list(){
        srt.opsForList().leftPush("platform:message","hello,lilei");
        srt.opsForList().leftPush("platform:message","hello,spring boot");

        List<String> range = srt.opsForList().range("platform:message", 0, -1);
        return range;
    }

    // 设置Hash字符串
    @GetMapping("/hash")
    public @ResponseBody Map<Object,Object> hashMap(){

        srt.opsForHash().put("user:cache","garyhu","redis::123");
        srt.opsForHash().put("user:cache","jully","ll9101");

        Map<Object, Object> entries = srt.opsForHash().entries("user:cache");


        // 根据key 获取单个值
        String p = (String) srt.opsForHash().get("user:cache","garyhu");
        System.out.println("密码 ："+p);
        return entries;
    }

    // 绑定key,每次不用再添加key的操作
    @GetMapping("/bound")
    public @ResponseBody List<String> bound(){

        BoundListOperations<String, String> gg = srt.boundListOps("gg");

        gg.leftPush("a");
        Long b = gg.leftPush("b");

        System.out.println(" b ：： "+b);

        return gg.range(0,-1);
    }

    // 测试Redis的Pub/Sub，通过convertAndSend来发送消息
    @GetMapping("/pubsub")
    public @ResponseBody String sub(){

        // 发送一条消息，在MyRedisChannelListener中接收发送的消息
        srt.convertAndSend("news","hello,world");

        return "success";
    }

    @GetMapping("/animal")
    public @ResponseBody Animal getAnimal(){
        redisClient.opsForValue().set("key-0","hello");
        redisClient.opsForValue().set("key-1",Animal.getSampleAnimal());

        Animal animal = (Animal) redisClient.opsForValue().get("key-1");

        return animal;
    }

    // 测试自定义序列化
    @GetMapping("/getRe")
    @ResponseBody
    public String getRe(){

        redis.opsForValue().set("key-2","自定义");

        String va = (String) redis.opsForValue().get("key-2");

        return va;
    }
}
