package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.Message;

@Mapper
public interface MessageDao {

    @Select("select * from message t where t.id = #{id}")
    Message getById(Long id);

    @Delete("delete from message where id = #{id}")
    int delete(Long id);

    int update(Message message);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into message(createTime, updateTime, nickName, message, phoneOrEmail, isContact) values(#{createTime}, #{updateTime}, #{nickName}, #{message}, #{phoneOrEmail}, #{isContact})")
    int save(Message message);
    
    int count(@Param("params") Map<String, Object> params);

    List<Message> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
