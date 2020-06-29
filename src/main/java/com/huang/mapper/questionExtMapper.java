package com.huang.mapper;

import com.huang.model.question;
import com.huang.model.questionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface questionExtMapper {

    int incView(question record);

    int incCommentCount(question record);

}