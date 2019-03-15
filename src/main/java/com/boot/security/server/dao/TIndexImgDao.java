package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.TIndexImg;

@Mapper
public interface TIndexImgDao {

    @Select("select * from t_index_img t where t.id = #{id}")
    TIndexImg getById(Long id);

    @Delete("delete from t_index_img where id = #{id}")
    int delete(Long id);

    int update(TIndexImg tIndexImg);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_index_img(title, filePath, remark, sort) values(#{title}, #{filePath}, #{remark}, #{sort})")
    int save(TIndexImg tIndexImg);
    
    int count(@Param("params") Map<String, Object> params);

    @Select("select * from t_index_img t order by t.sort desc")
    List<TIndexImg> getAllList();

    List<TIndexImg> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
