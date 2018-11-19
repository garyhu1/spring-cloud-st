package com.garyhu.controller;

import com.garyhu.pojo.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: garyhu
 * @since: 2018/11/13 0013
 * @decription:
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login.html")
    public String login(){

        return "login";
    }

    @GetMapping("/login")
    @ResponseBody
    public BaseResponse signIn(@RequestParam(value = "username",required = true) String username,
                               @RequestParam(value = "password",required = true) String password,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
       username = username.trim();
       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
       try{
           Authentication authenticate = authenticationManager.authenticate(authenticationToken);
           SecurityContextHolder.getContext().setAuthentication(authenticate);
           HttpSession session = request.getSession();
           session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());

           RequestCache requestCache = new HttpSessionRequestCache();
           SavedRequest savedRequest = requestCache.getRequest(request, response);
           if(savedRequest  != null){
               String redirectUrl = savedRequest.getRedirectUrl();
               LOGGER.info("重新定向的url是：{}",redirectUrl);
               response.sendRedirect(redirectUrl);
           }
           return new BaseResponse("登录成功");
       }catch (AuthenticationException e){
           return new BaseResponse("登录失败");
       }
    }
}
