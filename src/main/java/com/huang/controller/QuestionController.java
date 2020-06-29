package com.huang.controller;

import com.huang.dto.CommentCreateDTO;
import com.huang.dto.CommentDTO;
import com.huang.dto.QuestionDTO;
import com.huang.service.CommentService;
import com.huang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Long id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);

        List<CommentDTO> comments=commentService.listByQuestionId(id);


        //累加阅读数
        questionService.incView(id);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
