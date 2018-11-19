package com.garyhu;

import com.garyhu.zuul.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: garyhu
 * @since: 2018/11/7 0007
 * @decription:
 */
@SpringBootApplication
@EnableDiscoveryClient// 服务发现
@EnableFeignClients// feign
// 只要Hystrix在项目的classpath中，Feign就会使用短路器包裹Feign客户端的所有方法
@EnableHystrix// hystrix容错 或者使用注解@EnableCircuitBreaker
// 使用hystrix dashboard让监控数据图形化、可视化
@EnableHystrixDashboard
// 使用turbine监控多个微服务
//@EnableTurbine
@EnableZuulProxy
// 开启sidecar是用来整合异构语言的，最好重构一个项目来整合
//@EnableSidecar // 该注解整合了@EnableCircuitBreaker、@EnableZuulProxy、@EnableDiscoveryClient
public class MovieApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(MovieApplication.class,args);
    }

    @Bean
    @LoadBalanced // 客户端负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 自定义的Zuul过滤器
     * @return
     */
    @Bean
    public PreRequestLogFilter preRequestLogFilter(){
        return new PreRequestLogFilter();
    }
}
