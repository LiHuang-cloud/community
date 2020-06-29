package com.huang.service;

import com.huang.dto.PaginationDTO;
import com.huang.dto.QuestionDTO;
import com.huang.exception.CustomizeErrorCode;
import com.huang.exception.CustomizeException;
import com.huang.mapper.questionExtMapper;
import com.huang.mapper.questionMapper;
import com.huang.mapper.userMapper;
import com.huang.model.question;
import com.huang.model.questionExample;
import com.huang.model.user;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private userMapper userMapper;

    @Autowired
    private questionExtMapper questionExtMapper;

    @Autowired
    private questionMapper quesstioMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount =(int)quesstioMapper.countByExample(new questionExample());
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        } else if (page >paginationDTO.getCount()) {
            page = paginationDTO.getCount();
        }
        Integer offset = size * (page - 1);
        List<question> list = quesstioMapper.selectByExampleWithRowbounds(new questionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (question question : list) {
            user user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO getIdfindylist(Long id, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        questionExample questionExample = new questionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount =(int)quesstioMapper.countByExample(questionExample);
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page=1;
        }else if(page>paginationDTO.getCount()){
            page = paginationDTO.getCount();
        }
        Integer offset=size*(page-1);
        questionExample questionExample1= new questionExample();
        questionExample1.createCriteria().andCreatorEqualTo(id);
        List<question> list = quesstioMapper.selectByExampleWithRowbounds(questionExample1,new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (question question:list){
            user user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        question question =quesstioMapper.selectByPrimaryKey(id);
        if(question==null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        user byId = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(byId);
        return questionDTO;
    }

    public void createOrUpdate(question question) {
        if(question.getId()==null){//插入
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            quesstioMapper.insert(question);
        }else{//更新
            questionExample questionExample=new questionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int i = quesstioMapper.updateByExampleSelective(question, questionExample);
            if(i!=1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        question question=new question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
