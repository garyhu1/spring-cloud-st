package com.garyhu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription:
 */
@Controller
public class IndexController {

    //获取自定的配置值
    @Value("${water.height}")
    private int height;

    @GetMapping("/att/getName/{userId}")
    @ResponseBody
    public String getName(@PathVariable("userId")Integer userId){
        return userId+":garyhu";
    }

    @GetMapping("/att/test")
    public String redirectUrl(){
        return "redirect:/user/login.html";
    }
}
