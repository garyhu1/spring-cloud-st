package com.garyhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: garyhu
 * @since: 2018/11/19 0019
 * @decription:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args)throws Exception{
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
