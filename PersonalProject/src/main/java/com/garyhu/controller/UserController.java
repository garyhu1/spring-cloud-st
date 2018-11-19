package com.garyhu.controller;

import com.garyhu.pojo.*;
import com.garyhu.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : garyhu
 * @Since : 2018/10/27
 * @Decription : 用户接口
 */
@Controller
@RequestMapping("/user")
public class UserController  {

    @PostMapping("/register")
    public Result register(@RequestBody RegisterBean bean){
        String username = bean.getUsername();
        ResultCode resultCode;
        if(username == null || "".equals(username)){
            resultCode = ResultCode.USERNAME_NULL;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }
        String password = bean.getPassword();
        if(password  == null || "".equals(password)){
            resultCode  = ResultCode.PASSWORD_NULL;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }

        String sureword = bean.getSureword();
        if(sureword  == null || "".equals(sureword)){
            resultCode  = ResultCode.SUREWORD_NULL;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }

        if(!password.equals(sureword)){
            resultCode  = ResultCode.PASSWORD_NOMATCH;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }

        return ResponseUtils.success(bean);
    }

    @PostMapping("/login")
    public @ResponseBody Result login(@RequestBody LoginBean bean){
        String username = bean.getUsername();
        ResultCode resultCode;
        if(username == null || "".equals(username)){
            resultCode = ResultCode.USERNAME_NULL;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }
        String password = bean.getPassword();
        if(password  == null || "".equals(password)){
            resultCode  = ResultCode.PASSWORD_NULL;

            return ResponseUtils.warn(resultCode.getMsg(),resultCode.getCode());
        }

        AccessTokenBean accessTokenBean = new AccessTokenBean();
        return ResponseUtils.success(accessTokenBean);
    }

    @GetMapping("/login.html")
    public String loginPage(){
        return "user/login";
    }
}
