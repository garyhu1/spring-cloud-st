package com.garyhu.controller;

import com.garyhu.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Administrator
 * @since : 2018/10/30
 * @decripetion : 用来测试RestTemplate
 *
 **/

@Controller
@RequestMapping("/test")
public class BookController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Value("${api.book}")
    String base;

    // 获取
    @GetMapping("/get/{bookId}")
    @ResponseBody
    public Book getBookById(@PathVariable String bookId) throws Exception{
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = base + "/book/{bookId}";
        /*
        参数一：URI模板
        参数二：期望返回的对象
        参数三：URI模板对应的参数类型，也可以是数组或者Map集合
         */
        Book book = restTemplate.getForObject(url, Book.class, bookId);

        // 或者
        Map map = new HashMap<>();
        map.put("bookId",bookId);
        Book book1 = restTemplate.getForObject(url, Book.class, map);

        // 如果想返回HTTP头相关信息，可使用getForEntity
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(url,Book.class,bookId);
        Book book2 = responseEntity.getBody();
        HttpHeaders headers = responseEntity.getHeaders();
        return book;
    }

    // 添加
    @GetMapping("/addbook")
    @ResponseBody
    public String addBook()throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uri  = base + "/order";
        Book book = new Book();
        book.setAuthor("金庸");
        book.setName("射雕英雄传");
        book.setId(1);
        /*
        参数一：URI
        参数二：post参数，可以是HttpEntity,也可以是某个对象
        参数三：期望返回类型
         */
        String ret = restTemplate.postForObject(uri,book,String.class);

        // 或者
        HttpEntity<Book> body = new HttpEntity<>(book);
        String ret1 = restTemplate.postForObject(uri,body,String.class);
        return ret;
    }

    /*
     如果期望返回的是个列表，如List,不能直接使用XXXXForObject,因为存在泛型的类型擦除，
     可以使用ParameterizedTypeReference来包含,会保留期望返回的泛型
     */
    @GetMapping("/getbooks")
    @ResponseBody
    public List<Book> getBooks(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // 根据条件查询一组书
        String uri = base + "/books?offset={offset}";
        Integer offset = 1;
        // 无参数
        HttpEntity body = null;
        ParameterizedTypeReference<List<Book>> typeRef = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> rs = restTemplate.exchange(uri, HttpMethod.GET, body, typeRef, offset);
        List<Book> books = rs.getBody();
        return books;
    }
}
