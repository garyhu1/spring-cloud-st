package com.garyhu.controller;

import com.garyhu.entity.Student;
import com.garyhu.pojo.Result;
import com.garyhu.repository.StudentRepository;
import com.garyhu.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription:
 */
@RestController
public class StudentController {

    @Resource
    StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getStudent")
    public Result<Student> getStudent(@RequestParam(value = "id")int id){
        Student st = studentRepository.findOne(id);

        Result success = ResponseUtils.success(st);

        return success;
    }

    @GetMapping("/addStudent")
    public Result addStudent(@RequestParam(value = "name")String name,
                             @RequestParam(value = "age")int age){
        Student st = new Student();
        st.setAge(age);
        st.setName(name);

        Student save = studentRepository.save(st);

        Result success = ResponseUtils.success(save);

        return success;
    }

    @GetMapping("/student")
    public Result student(@RequestParam("name")String name,@RequestParam("age")Integer age){
        Student student = studentRepository.getStudentByNameAndAge(name, age);
        Result result = ResponseUtils.success(student);

        return result;
    }

    @PostMapping("/obtainStudent")
    public Result obtainStudent(@RequestBody Student student){
        Student st = studentRepository.save(student);

        Result result = ResponseUtils.success(st);

        return result;
    }

    @GetMapping("/myStudent")
    public Result myStudent(@RequestParam("id") Integer id){
        Student student = studentRepository.findOne(id);

        Result result = ResponseUtils.success(student);
        return result;
    }

    @GetMapping("/api/sayHello")
    public void sayHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("hello world!");
    }

    /**
     * 该接口测试通过JVM微服务调用非JVM微服务接口
     */
    @GetMapping("/test")
    public String findNode(){
        return restTemplate.getForObject("http://microservice-simple-consumer-movie/",String.class);
    }

    @GetMapping("/api/student")
    public Student getStudentById(@RequestParam(value = "id",required = true)Integer id){
        Student s = studentRepository.findOne(id);
        return s;
    }
}
