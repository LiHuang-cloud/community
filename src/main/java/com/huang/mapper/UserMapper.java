package com.huang.mapper;

import com.huang.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Delete("delete from user")
    int delete();

    @Select("select * from user where id=#{id}")
    User findById(@Param("id")Integer id);

    @Select("select * from user where account_id=#{accounid}")
    User findByAccountId(String accountId);

    @Update("update user set name=#{name}, token=#{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User User);
}
