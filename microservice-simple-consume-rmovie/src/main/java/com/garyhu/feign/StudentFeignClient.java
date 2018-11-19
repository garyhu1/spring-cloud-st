package com.garyhu.feign;

import com.garyhu.entity.Student;
import com.garyhu.pojo.Result;
import config.FeignConfiguration;
import config.FeignDisableHystrixConfiguration;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: garyhu
 * @since: 2018/11/15 0015
 * @decription:
 */
// feign声明式用+回退配置，FeignClientCallback实现回退的方法
//@FeignClient(name = "PersonalProject",fallback = FeignClientCallback.class,configuration = FeignConfiguration.class)
// feign声明式用+回退+打印日志配置
@FeignClient(name = "PersonalProject",fallbackFactory = FeignClientCallbackFactory.class,configuration = FeignConfiguration.class)
// feign禁用hystrix
//@FeignClient(name = "PersonalProject",configuration = FeignDisableHystrixConfiguration.class)
public interface StudentFeignClient {

    @RequestMapping(value = "/getStudent",method = RequestMethod.GET)
    public Result<Student> findById(@RequestParam(value = "id",required = true) Integer id);

    @RequestMapping(value = "/myStudent",method = RequestMethod.GET)
    public Result<Student> getStudent(@RequestParam(value = "id",required = true) Integer id);

    /**
     * 这里不可以使用@RequestBody
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value="/student",method = RequestMethod.GET)
    public Result<Student> findByNameAndAge(@RequestParam("name")String name,@RequestParam("age")Integer age);

    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public Result<Student> student(@RequestParam Map<String,Object> map);

    @RequestMapping(value = "/obtainStudent",method = RequestMethod.POST)
    public Result obtainStudent(@RequestBody Student student);
}
