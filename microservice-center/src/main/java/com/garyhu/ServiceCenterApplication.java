package com.garyhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: garyhu
 * @since: 2018/11/9 0009
 * @decription:
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceCenterApplication {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(ServiceCenterApplication.class,args);
    }
}
