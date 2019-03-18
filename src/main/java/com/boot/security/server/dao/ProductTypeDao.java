package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.ProductType;

@Mapper
public interface ProductTypeDao {

    @Select("select * from product_type t where t.id = #{id} and deleted = 0 ")
    ProductType getById(Long id);

    @Delete("update product_type set deleted = 1 where id = #{id}")
    int delete(Long id);

    int update(ProductType productType);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into product_type(productType, isShow, sort,deleted) values(#{productType}, #{isShow},#{sort}, 0)")
    int save(ProductType productType);
    
    int count(@Param("params") Map<String, Object> params);

    List<ProductType> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from product_type t where  deleted = 0 ")
    List<ProductType> getAllList();
}
