package com.garyhu.feign;

import config.FeignConfiguration;
import com.garyhu.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: garyhu
 * @since: 2018/11/12 0012
 * @decription:
 */
//@FeignClient(name = "microservice-simple-provider-user")// 指定客户端名称
@FeignClient(name = "microservice-simple-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {

    // 备注： 此处的@PathVarivable没有添加“id”，就抛出PathVariable annotation was empty on param 0异常
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Integer id);

    /**
     * 使用feign自带的注解@RequestLine
     * @param id 用户id
     * @return 用户信息
     * @See https://github.com/OpenFeign/feign
     */
//    @RequestLine("GET /user/{id}")
//    public User findById2(@Param("id") Integer id);
}
