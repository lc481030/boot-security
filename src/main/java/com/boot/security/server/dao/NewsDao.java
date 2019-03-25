package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.News;

@Mapper
public interface NewsDao {

    @Select("select * from news t where t.id = #{id}")
    News getById(Long id);

    @Delete("delete from news where id = #{id}")
    int delete(Long id);

    int update(News news);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into news(createTime, updateTime, pushTime, title, detail, headImg, sort, deleted, isShow) values(#{createTime}, #{updateTime}, #{pushTime}, #{title}, #{detail}, #{headImg}, #{sort}, #{deleted}, #{isShow})")
    int save(News news);
    
    int count(@Param("params") Map<String, Object> params);

    List<News> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
