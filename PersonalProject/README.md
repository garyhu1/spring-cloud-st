# pesonal
个人主页搭建

## 使用其他WEB服务器
  spring boot 内置Tomcat,同时还支持Jetty、Undertow作为web服务器

  **Undertow :**
  ```
  <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-undertow</artifactId>
  </dependency>
  ```

  **Jetty ：**

  ```
    <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
  ```

  **同时，还需要在Spring-boot-starter-web中去除Tomcat依赖 ：**

  ```
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
      <exclusion>
        <artifactId>org.springframework.boot</artifactId>
        <groupId>spring-boot-starter-tomcat</groupId>
      </exclusion>
    </exclusions>
  </dependency>
  ```
其中Undertow性能优于tomcat和jetty,推荐使用Undertow作为SpringBoot应用服务器

## 修改控制台的输出信息：

1、可以在Spring Boot项目的resources下新建一个banner.txt文件，也可以为banner.png(gif、jpg)
2、关于banner的配置：
`banner.location=classpath:banner.txt`
**如果使用图片，图片位置可以使用jpg/png**
`banner.image.location=classpath:banner.gif`
**图片宽度，这里指转为字符的个数，越多越清楚**
`banner.image.width=76`
**图片长度**
`banner.image.height=76`
**图片与左边的边距，默认为2个字符**
`banner.image.margin=2`

## 配置浏览器显示的ico

1、更换为自定义的需要在resources下创建static/images目录，然后放入自己的图片
**使用：**
`<link rel="shortcut icon" href="/images/自定义的图片"`

## 日志配置

**指定对应包下面的日志级别**
`logging.level.org=info`
`logging.level.root=info`
`logging.level.com.yourpage=info`

**指定日志输出到文件中，默认未设置**
`logging.file=mylog.log`

**指定日志存放目录，默认该文件存放在spring boot应用运行的当前目录下**
`logging.path=d:/temp/log`
当日志文件达到10MB时，会自动重新生成一个新日志文件


## 多环境部署
 **备注**
先注释了devtools依赖