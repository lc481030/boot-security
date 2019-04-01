package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.Comment;
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

    @Select("select * from news ne where ne.headImg != '' and ne.isShow = 0 ")
    List<News> getByAllImg();
    @Select("select * from news ne where ne.detail != '' and  ne.isShow = 0  Limit  0,6")
    List<News> getByAllCont();

    @Select("select * from news ne where ne.detail != '' and  ne.isShow = 0  limit  #{pageCount},#{pageSize}")
    List<News> querAll(long pageCount,long pageSize);

    @Select("select * from news ne where ne.detail != '' and  ne.isShow = 0 Limit  0,9")
    List<News> querTen();
}
