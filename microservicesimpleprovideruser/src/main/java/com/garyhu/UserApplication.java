package com.garyhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: garyhu
 * @since: 2018/11/7 0007
 * @decription:
 */
//@SpringBootApplication(scanBasePackages = "com.garyhu") // 设置应用@ComponentScan扫描的包
@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
//})
public class UserApplication {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(UserApplication.class,args);
    }
}
