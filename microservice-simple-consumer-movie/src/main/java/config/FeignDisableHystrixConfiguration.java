package config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author : Administrator
 * @decripetion : feign禁用hystrix
 * @since : 2018/11/17
 **/
// 这个先不使用，注释了自动装配
//@Configuration
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")// 每次获取bean的时候都会有一个新的实例
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
