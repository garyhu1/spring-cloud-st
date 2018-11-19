package com.garyhu.controller;

import com.garyhu.entity.User;
import com.garyhu.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: garyhu
 * @since: 2018/11/7 0007
 * @decription:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public User findUserById(@PathVariable Integer id){
        User user = userRepository.findOne(id);

        return user;
    }

    // 安全认证测试
    @GetMapping("/security/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            for (GrantedAuthority authority: authorities) {
                // 打印当前登录用户信息
                LOGGER.info("当前用户是{},角色是{}",user.getUsername(),authority.getAuthority());
            }
        }else{
            // do other things
        }

        User user = userRepository.findOne(id);
        return user;
    }
}
