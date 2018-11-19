# service-user
用户服务

## spring security

### 1、通过实现WebSecurityConfigurerAdapter来配置Security,
其中一些接口或页面需要设置不用拦截

### 2、完成Security用到以下类：
MyAuthenctiationFailureHandler： 认证失败的回调
MyAuthenticationSuccessHandler： 认证成功的回调
BrowerSecurityConfig： 实现了WebSecurityConfigurerAdapter，配置登录页面,不拦截的接口或页面，还有密码的加密处理
MyUserDetailsService： 用户认证
SecurityProperties： 配置类，参数值都在yml中，主要设置了登录界面的地址，配合BrowserProperties和SecurityCoreConfig，
SecurityCoreConfig： 是用来读取配置类（SecurityProperties），因为SecurityProperties中的类名，跟yml不匹配，需要通过读取才能自动加载
BaseResponse： 简单的响应处理
BrowserSecurityController