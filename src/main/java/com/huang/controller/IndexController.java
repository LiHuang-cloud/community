package com.huang.controller;

import com.huang.mapper.UserMapper;
import com.huang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = {"/"})
    public String hello(HttpServletRequest request) {
        System.out.println("index");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    String token = cookies[i].getValue();
                    User user = userMapper.findByToken(token);
                    System.out.println(user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return "index";
    }
}
