package com.garyhu.controller;

import com.garyhu.entity.Student;
import com.garyhu.feign.StudentFeignClient;
import com.garyhu.pojo.Result;
import com.garyhu.utils.ResponseUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: garyhu
 * @since: 2018/11/15 0015
 * @decription:
 */
@RestController
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private StudentFeignClient studentFeignClient;

    @GetMapping("/student")
    public Result<Student> getStduent(@RequestParam(value = "id",required = true)Integer id){
        return studentFeignClient.findById(id);
    }

    @GetMapping("/getStudent")
    public Result<Student> findByNameAndAge(@RequestParam("name") String name,@RequestParam("age") Integer age){
        return studentFeignClient.findByNameAndAge(name,age);
    }

    @GetMapping("/getStudent2")
    public Result findByNameAndAge2(@RequestParam("name") String name,@RequestParam("age") Integer age){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);

        return studentFeignClient.student(map);
    }

    @PostMapping("/getStudent3")
    public Result obtainStudent(@RequestBody Student student){
        return studentFeignClient.obtainStudent(student);
    }

    @HystrixCommand(fallbackMethod = "studentFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000"),
//            @HystrixProperty(name="execution.isolation.strategy",value="SEMAPHORE"),// 设置隔离策略，两种，默认为THREAD
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds",value="10000")
    },threadPoolProperties = {
            @HystrixProperty(name="coreSize",value="1"),
            @HystrixProperty(name="maxQueueSize",value="10")
    })
    @GetMapping("/student/get")
    public Result getStudentById(@RequestParam("id") Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return restTemplate.getForObject("http://PersonalProject/myStudent?id={id}",Result.class,map);
    }

    public Result studentFallback(Integer id){
        Student student = new Student();
        student.setName("默认用户");
        student.setId(-1);
        student.setAge(-1);

        return ResponseUtils.success(student);
    }
}
