package com.garyhu.feign;

import com.garyhu.entity.Student;
import com.garyhu.pojo.Result;
import com.garyhu.utils.ResponseUtils;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : garyhu
 * @since : 2018/11/17
 * @decripetion : feign回退+打印回退的日志,实现FallbackFactory<StudentFeignClient>create方法;
 * 不仅仅是打印日志用，还可以根据异常的不同返回不同的数据
 **/
@Component
public class FeignClientCallbackFactory implements FallbackFactory<StudentFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientCallbackFactory.class);

    // 该cloud版本中（feign版本为9.3.1）cause有可能为空，在feign9.4.0中已经解决了
    @Override
    public StudentFeignClient create(final Throwable cause) {
        return new StudentFeignClient() {
            @Override
            public Result<Student> findById(Integer id) {
                FeignClientCallbackFactory.LOGGER.info("fallback reason was:",cause);
                Student student = new Student();
                student.setId(-1);
                student.setName("默认用户");
                student.setAge(-1);
                Result result = ResponseUtils.success(student);
                return result;
            }

            @Override
            public Result<Student> getStudent(Integer id) {
                FeignClientCallbackFactory.LOGGER.info("fallback reason was:",cause);
                Student student = new Student();
                student.setId(-1);
                student.setName("默认用户");
                student.setAge(-1);
                Result result = ResponseUtils.success(student);
                return result;
            }

            @Override
            public Result<Student> findByNameAndAge(String name, Integer age) {
                FeignClientCallbackFactory.LOGGER.info("fallback reason was:",cause);
                Student student = new Student();
                student.setId(-1);
                student.setName("默认用户");
                student.setAge(-1);
                Result result = ResponseUtils.success(student);
                return result;
            }

            @Override
            public Result<Student> student(Map<String, Object> map) {
                FeignClientCallbackFactory.LOGGER.info("fallback reason was:",cause);
                Student student = new Student();
                student.setId(-1);
                student.setName("默认用户");
                student.setAge(-1);
                Result result = ResponseUtils.success(student);
                return result;
            }

            @Override
            public Result obtainStudent(Student student) {
//                FeignClientCallbackFactory.LOGGER.info("fallback reason was:",cause);
                Student s = new Student();
                s.setId(-1);
                s.setName("默认用户");
                s.setAge(-1);
                Result result = ResponseUtils.success(student);
                return result;
            }
        };
    }
}
