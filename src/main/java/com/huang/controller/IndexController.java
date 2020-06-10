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



@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"/"})
    public String hello(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        System.out.println("index");
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
