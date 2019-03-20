package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.Comment;

@Mapper
public interface CommentDao {

    @Select("select * from comment t where t.id = #{id}")
    Comment getById(Long id);

    @Delete("delete from comment where id = #{id}")
    int delete(Long id);

    int update(Comment comment);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into comment(createTime, updateTime, detail, commentType, img, deleted, commentTime) values(#{createTime}, #{updateTime}, #{detail}, #{commentType}, #{img}, #{deleted}, #{commentTime})")
    int save(Comment comment);
    
    int count(@Param("params") Map<String, Object> params);

    List<Comment> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from comment t order by  t.commentTime Desc")
    List<Comment> getByAll();
}
