package com.garyhu.controller;

import com.garyhu.entity.User;
import com.garyhu.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: garyhu
 * @since: 2018/11/9 0009
 * @decription:
 */
//@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/movie")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFeignClient userFeignClient;

//    private UserFeignClient userFeignClient;
//    private UserFeignClient adminFeignClient;

//    @Autowired
//    public MovieController (Decoder decoder, Encoder encoder, Client client, Contract contract){
//        this.userFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user","pass123"))
//                .target(UserFeignClient.class,"http://microservice-simple-provider-user/");
//
//        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("admin","pass456"))
//                .target(UserFeignClient.class,"http://microservice-simple-provider-user/");
//    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){

        User user = restTemplate.getForObject("http://microservice-simple-provider-user/user/" + id, User.class);

        return user;
    }

    /**
     * 获取microservice-simple-provider-user服务的信息并返回
     * @return
     */
    @GetMapping("/movie-instance")
    public List<ServiceInstance> showInfo(){
        return this.discoveryClient.getInstances("microservice-simple-provider-user");
    }

    @GetMapping("/log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-simple-provider-user");
        // 打印当前选择的是哪个节点
        LOGGER.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }

    // 测试Feign
    @GetMapping("/feign/{id}")
    public User findById(@PathVariable Integer id){
        return userFeignClient.findById(id);
    }

}
