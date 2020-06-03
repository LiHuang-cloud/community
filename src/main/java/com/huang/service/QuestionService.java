package com.huang.service;

import com.huang.dto.QuestionDTO;
import com.huang.mapper.QuesstioMapper;
import com.huang.mapper.UserMapper;
import com.huang.model.Question;
import com.huang.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuesstioMapper quesstioMapper;
    public List<QuestionDTO> list() {
        List<Question> list = quesstioMapper.list();
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for (Question question:list){
          User user=userMapper.findById(question.getCreator());
          QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
