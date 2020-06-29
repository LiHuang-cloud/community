package com.huang.service;

import com.huang.dto.CommentDTO;
import com.huang.enums.CommentTypeEnum;
import com.huang.exception.CustomizeErrorCode;
import com.huang.exception.CustomizeException;
import com.huang.mapper.CommentMapper;
import com.huang.mapper.questionExtMapper;
import com.huang.mapper.questionMapper;
import com.huang.mapper.userMapper;
import com.huang.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private questionMapper questionMapper;

    @Autowired
    private questionExtMapper questionExtMapper;

    @Autowired
    private userMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId()==null||comment.getParentId()==0){
            throw  new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){//回复评论
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(comment1==null){
                throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
            }
        }else{
            //回复问题
            question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            comment.setLikeCount(Long.valueOf(0));
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }


    public List<CommentDTO> listByQuestionId(Long id) {
        CommentExample example=new CommentExample();
        example.createCriteria().andParentIdEqualTo(id)
        .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(example);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        userExample userExample=new userExample();
        userExample.createCriteria().andIdIn(userIds);
        List<user> users = userMapper.selectByExample(userExample);
        Map<Long, user> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为commentDTO
        List<CommentDTO> collectDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return  collectDTOS;
    }
}
