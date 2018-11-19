package com.garyhu.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author : Administrator
 * @since : 2018/10/29
 * @decripetion : Environment是Spring Boot最早初始化的一个类。因此在应用的任何地方都可以用；
 *    env.getgetProperty("user.dir")程序运行的目录
 *    env.getgetProperty("user.home")指定程序的用户的home目录
 *    env.getgetProperty("JAVA_HOME")读取设置的环境变量（不区分大小写）
 *    env.getgetProperty("server.port")读取应用端口号
 **/
@Configuration
public class EnvConfiguration {
    @Autowired
    Environment env;

    /**
     * 获取应用端口号
     */
    public int getServerPort(){
        return env.getProperty("server.port",Integer.class);
    }
}
