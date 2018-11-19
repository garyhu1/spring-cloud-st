package com.garyhu.conf;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Administrator
 * @since : 2018/10/30
 * @decripetion : 定制RestTemplate
 * 创建一个配置类实现RestTemplateCustomizer接口的customize方法
 * RestTemplate默认使用JDK的URLConnection作为底层的HTTP工具，
 * 如果想使用OKHttp,需要添加依赖
 **/
public class RestConf implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        // 使用默认的
//        SimpleClientHttpRequestFactory jdkHttp =
//                (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
//        jdkHttp.setConnectTimeout(3000);
        // 添加OKHttp依赖后，可以使用
        OkHttp3ClientHttpRequestFactory okHttp =
                (OkHttp3ClientHttpRequestFactory) restTemplate.getRequestFactory();
        okHttp.setConnectTimeout(3000);
        okHttp.setWriteTimeout(5000);
    }
}
