package com.garyhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: garyhu
 * @since: 2017/12/13 0013
 * @decription: spring boot的启动
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args)throws Exception {
        SpringApplication.run(Application.class,args);
    }
}
