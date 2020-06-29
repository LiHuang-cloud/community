package com.huang.service;


import com.huang.mapper.userMapper;

import com.huang.model.user;
import com.huang.model.userExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private userMapper userMapper;

    public void createOrUpdate(user user) {
        userExample userExample=new userExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<com.huang.model.user> users=userMapper.selectByExample(userExample);
        if(users.size()==0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            user dbUser= users.get(0);
            user updateuser=new user();
            updateuser.setAvatarUrl(user.getAvatarUrl());
            updateuser.setName(user.getName());
            updateuser.setToken(user.getToken());
            updateuser.setBio(user.getBio());
            updateuser.setGmtModified(user.getGmtModified());
            userExample userExample1=new userExample();
            userExample.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateuser,userExample);
            //            //更新
        }
    }
}
