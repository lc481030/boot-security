package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import com.boot.security.server.model.ProductModeNum;
import com.boot.security.server.model.StoreSearch;
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
    @Insert("insert into product(createTime, updateTime, title, detail, productMode, productType, startTime, imgs, price, startCity, targetCountry, days, afterCity, vehicle, brightSpot, costDescription, reservationNotes, deleted,recommendIndex) values(#{createTime}, #{updateTime}, #{title}, #{detail}, #{productMode}, #{productType}, #{startTime}, #{imgs}, #{price}, #{startCity}, #{targetCountry}, #{days}, #{afterCity}, #{vehicle}, #{brightSpot}, #{costDescription}, #{reservationNotes}, #{deleted},0)")
    int save(Product product);
    
    int count(@Param("params") Map<String, Object> params);

    List<Product> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * @ 根据类型ID查询当前类型下的旅游地点
     * @param id
     * @return
     */
    @Select("select * from product t where t.productType = #{id} order by updateTime desc limit 8")
    List<Product> getByProductTypeId(Long id);

    @Select("SELECT productMode,COUNT(1) as productNum FROM `product` WHERE deleted is null or deleted = 0   GROUP BY productMode ")
    List<ProductModeNum> getNumByModeType();

    /*前段列表查询*/
    List<Product> storeList(@Param("search") StoreSearch search, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
