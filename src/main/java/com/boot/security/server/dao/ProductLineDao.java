package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.TIndexImg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ProductLine;

@Mapper
public interface ProductLineDao {

    @Select("select * from product_line t where t.id = #{id}")
    ProductLine getById(Long id);

    @Delete("delete from product_line where id = #{id}")
    int delete(Long id);

    int update(ProductLine productLine);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into product_line(title, detail, img, remark) values(#{title}, #{detail}, #{img}, #{remark})")
    int save(ProductLine productLine);
    
    int count(@Param("params") Map<String, Object> params);

    List<ProductLine> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from product_line order by id asc")
    List<ProductLine> getAllList();
}
