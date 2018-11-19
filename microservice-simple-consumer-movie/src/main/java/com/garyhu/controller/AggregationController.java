package com.garyhu.controller;

import com.garyhu.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  com.garyhu.service.AggregationService;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

import java.util.HashMap;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/11/18
 **/
@RestController
public class AggregationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String,Student>> aggregate(@PathVariable Integer id){
        Observable<HashMap<String, Student>> hashMapObservable = this.aggregateObservable(id);
        return this.toDeferredResult(hashMapObservable);
    }

    public Observable<HashMap<String,Student>> aggregateObservable(Integer id){
        // 合并两个或者多个Observables发出的数据项，根据指定的函数转换它们
        return Observable.zip(
                aggregationService.getStudentById(id),
                aggregationService.getStudentByOther(id),
                new Func2<Student, Student, HashMap<String, Student>>() {
                    @Override
                    public HashMap<String, Student> call(Student student, Student student2) {
                        HashMap<String,Student> map = new HashMap<>();
                        map.put("user",student);
                        map.put("movieUser",student2);
                        return map;
                    }
                }
        );
    }

    public DeferredResult<HashMap<String,Student>> toDeferredResult(Observable<HashMap<String,Student>> details){
        final DeferredResult<HashMap<String,Student>> result = new DeferredResult<>();
        // 订阅
        details.subscribe(new Observer<HashMap<String, Student>>() {
            @Override
            public void onCompleted() {
                LOGGER.info("完成...");
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("发生错误...",throwable);
            }

            @Override
            public void onNext(HashMap<String, Student> map) {
                result.setResult(map);
            }
        });
        return result;
    }
}
