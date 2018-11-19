package config;

import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: garyhu
 * @since: 2018/11/12 0012
 * @decription: 该类为Feign配置类，注意：该类不应该出现在@ComponentScan中
 */
@Configuration
public class FeignConfiguration {

    /**
     * 将契约改成Feign原生默认契约，这样就可以用Feign自带的注解了
     * @return 默认的feign契约
     */
//    @Bean
//    public Contract feignContract(){
//        return new feign.Contract.Default();
//    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","pass123");
    }
}
