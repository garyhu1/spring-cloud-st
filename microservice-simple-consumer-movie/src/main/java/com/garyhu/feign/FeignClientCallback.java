package com.garyhu.feign;

import com.garyhu.entity.Student;
import com.garyhu.pojo.Result;
import com.garyhu.utils.ResponseUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : garyhu
 * @decripetion : feign的回退实现 , 实现StudentFeignClient
 * @since : 2018/11/17
 **/
@Component
public class FeignClientCallback implements StudentFeignClient {

    @Override
    public Result<Student> findById(Integer id) {
        Student student = new Student();
        student.setId(-1);
        student.setName("默认用户");
        student.setAge(-1);
        Result result = ResponseUtils.success(student);
        return result;
    }

    @Override
    public Result<Student> getStudent(Integer id) {
        Student student = new Student();
        student.setId(-1);
        student.setName("默认用户");
        student.setAge(-1);
        Result result = ResponseUtils.success(student);
        return result;
    }

    @Override
    public Result<Student> findByNameAndAge(String name, Integer age) {
        Student student = new Student();
        student.setId(-1);
        student.setName("默认用户");
        student.setAge(-1);
        Result result = ResponseUtils.success(student);
        return result;
    }

    @Override
    public Result<Student> student(Map<String, Object> map) {
        Student student = new Student();
        student.setId(-1);
        student.setName("默认用户");
        student.setAge(-1);
        Result result = ResponseUtils.success(student);
        return null;
    }

    @Override
    public Result obtainStudent(Student student) {
        Student s = new Student();
        s.setId(-1);
        s.setName("默认用户");
        s.setAge(-1);
        Result result = ResponseUtils.success(student);
        return result;
    }
}
