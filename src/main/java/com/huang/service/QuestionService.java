package com.huang.service;

import com.huang.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = quesstioMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        } else if (page >paginationDTO.getCount()) {
            page = paginationDTO.getCount();
        }
        Integer offset = size * (page - 1);
        List<Question> list = quesstioMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        System.out.println(questionDTOS);
        System.out.println(list);
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO getIdfindylist(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalCount=quesstioMapper.getIdCount(id);
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }else if(page>paginationDTO.getCount()){
            page = paginationDTO.getCount();
        }
        Integer offset=size*(page-1);
        List<Question> list=quesstioMapper.getIdfindylist(id,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question:list){
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
