package com.huang.mapper;

import com.huang.dto.QuestionDTO;
import com.huang.model.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuesstioMapper {

    @Insert("insert into question(title,DESCRIPTION,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Delete("delete from question")
    void delete();

    @Select("select * from question")
    List<Question> list();
}
