package com.garyhu.config;

import com.garyhu.pojo.BrowserProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: garyhu
 * @since: 2018/11/13 0013
 * @decription: 自定义的配置文件，从配置文件中读取
 * 这里的类名称与配置中的名称对应不上，所以要再写个类来读取配置类，这里写了一个SecurityCoreConfig类
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
