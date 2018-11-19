package com.garyhu.config;

import com.garyhu.authentication.MyAuthenctiationFailureHandler;
import com.garyhu.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: garyhu
 * @since: 2018/11/13 0013
 * @decription: security的配置
 * 一定要设置好不需要拦截的接口或页面，通过如antMatchers("/authentication/require","/login.html").permitAll()来允许任何人都可以访问
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                 // 定义当需要登录的时候，跳转到登录界面
//                .loginPage("/login.html")// 设置登录界面(个性化设置的登录界面)
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")// 自定义的登录接口
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
                .and()
                .authorizeRequests()     // 定义哪些URL需要被保护，哪些不需要被保护
                .antMatchers("/authentication/require","/login",securityProperties.getBrowser().getLoginPage()).permitAll()// 设置所有人都可以访问登录页面
                .anyRequest()            // 任何请求登录后都可以访问
                .authenticated()
                .and()
                .csrf().disable();        // 关闭csrf防护
    }

    /**
     *  密码加密处理
     * @return 默认的实现类
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
