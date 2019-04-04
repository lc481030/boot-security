package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.TResourceSupportImg;

@Mapper
public interface TResourceSupportImgDao {

    @Select("select * from t_resource_support_img t where t.id = #{id}")
    TResourceSupportImg getById(Long id);

    @Delete("delete from t_resource_support_img where id = #{id}")
    int delete(Long id);

    int update(TResourceSupportImg tResourceSupportImg);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_resource_support_img(img, sort, linkAddress) values(#{img}, #{sort}, #{linkAddress})")
    int save(TResourceSupportImg tResourceSupportImg);
    
    int count(@Param("params") Map<String, Object> params);

    List<TResourceSupportImg> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_resource_support_img ")
    List<TResourceSupportImg> imgList();
}
