package com.garyhu.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Administrator
 * @decripetion : 统一处理自定义的配置文件
 * @since : 2018/10/29
 **/
@ConfigurationProperties("teacher")
@Configuration
public class TeacherConfig {

    private String name;
    private int id;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
