package com.huang.controller;

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


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private QuesstioMapper quesstioMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping(value = {"/"})
    public String hello(HttpServletRequest request, Model model) {
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
        List<QuestionDTO> questions=questionService.list();
        for (QuestionDTO questionDTO:questions){
            questionDTO.setDescription("cahng");
        }
        model.addAttribute("questions",questions);
        return "index";
    }
}
