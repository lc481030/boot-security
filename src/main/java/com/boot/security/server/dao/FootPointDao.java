package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.FootPoint;

@Mapper
public interface FootPointDao {

    @Select("select * from footPoint t where 1=1 and deleted = 0  and  t.id = #{id} ")
    FootPoint getById(Long id);

    @Delete("update  footPoint set deleted = 1 where id = #{id}")
    int delete(Long id);

    int update(FootPoint footPoint);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into footPoint(createTime, updateTime, title, footPointType, img, detail,deleted) values(#{createTime}, #{updateTime}, #{title}, #{footPointType}, #{img}, #{detail}, #{deleted})")
    int save(FootPoint footPoint);
    
    int count(@Param("params") Map<String, Object> params);

    List<FootPoint> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<FootPoint> getByPcFootPoint(FootPoint footPoint);
}
