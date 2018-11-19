package com.garyhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription: 1、打包成war包时需要集成SpringBootServletInitializer，实现configure方法
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PersonalApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(PersonalApplication.class);
    }

    public static void main(String[] args)throws Exception{
        SpringApplication.run(PersonalApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
