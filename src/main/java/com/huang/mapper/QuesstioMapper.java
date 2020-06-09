package com.huang.mapper;

import com.huang.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuesstioMapper {

    @Insert("insert into question(title,DESCRIPTION,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Delete("delete from question")
    void delete();

    @Select("select * from question limit #{param1},#{param2}")
    List<Question> list( Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator=#{id}")
    Integer getIdCount(Integer id);

    @Select("select * from question  where creator=#{param1} limit #{param2},#{param3}  ")
    List<Question> getIdfindylist(Integer id, Integer offset, Integer size);
}
