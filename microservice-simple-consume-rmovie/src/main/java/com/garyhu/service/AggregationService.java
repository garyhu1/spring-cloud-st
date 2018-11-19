package com.garyhu.service;

import com.garyhu.entity.Student;
import com.garyhu.feign.StudentFeignClient;
import com.garyhu.pojo.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/11/18
 **/
@Service
public class AggregationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregationService.class);

    @Autowired
    private StudentFeignClient studentFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<Student> getStudentById(final Integer id){
        // 创建一个被观察者
        return Observable.create(new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                // 请求用户微服务的一个端点
                // 采用两种方式都可以请求到数据
//                Result<Student> result = studentFeignClient.findById(id);
                Student student = restTemplate.getForObject("http://PersonalProject/api/student?id={id}",Student.class,id);
//                LOGGER.info("result1 : ",result);
//                Student student = (Student) result.getResult();
                subscriber.onNext(student);
                subscriber.onCompleted();
            }
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<Student> getStudentByOther(final Integer id){
        return Observable.create(new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                // 请求用户微服务的另一个接口，这里没有那么多微服务，多的情况下可以请求另一个微服务的端点
                // 采用两种方式都可以请求到数据
//                Result<Student> result = studentFeignClient.getStudent(2);
                Student student = restTemplate.getForObject("http://personalproject/api/student?id={id}",Student.class,2);
//                LOGGER.info("result2 : ",result);
//                Student student = (Student) result.getResult();
                subscriber.onNext(student);
                subscriber.onCompleted();
            }
        });
    }

    public Student fallback(Integer id){
        Student s  = new Student();
        s.setId(-1);
        s.setAge(-1);
        s.setName("默认用户");
        return s;
    }
}
