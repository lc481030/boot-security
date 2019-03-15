package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.boot.security.server.model.Product;

@Mapper
public interface ProductDao {

    @Select("select * from product t where t.id = #{id}")
    Product getById(Long id);

    @Delete("delete from product where id = #{id}")
    int delete(Long id);

    int update(Product product);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into product(createTime, updateTime, title, detail, productMode, productType, startTime, imgs, price, startCity, targetCountry, days, afterCity, vehicle, brightSpot, costDescription, reservationNotes, deleted) values(#{createTime}, #{updateTime}, #{title}, #{detail}, #{productMode}, #{productType}, #{startTime}, #{imgs}, #{price}, #{startCity}, #{targetCountry}, #{days}, #{afterCity}, #{vehicle}, #{brightSpot}, #{costDescription}, #{reservationNotes}, #{deleted})")
    int save(Product product);
    
    int count(@Param("params") Map<String, Object> params);

    List<Product> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
