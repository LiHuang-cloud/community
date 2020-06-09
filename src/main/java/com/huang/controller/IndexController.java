package com.huang.controller;

import com.huang.dto.PaginationDTO;
import com.huang.dto.QuestionDTO;
import com.huang.mapper.QuesstioMapper;
import com.huang.mapper.UserMapper;
import com.huang.model.Question;
import com.huang.model.User;
import com.huang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuesstioMapper quesstioMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"/"})
    public String hello(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        System.out.println("index");
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
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
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
