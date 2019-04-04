package com.boot.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.boot.security.server.model.Customized;

@Mapper
public interface CustomizedDao {

    @Select("select * from customized t where t.id = #{id}")
    Customized getById(Long id);

    @Delete("delete from customized where id = #{id}")
    int delete(Long id);

    int update(Customized customized);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into customized(createTime, updateTime, dzTypeShow, startDateShow, dayStrShow, objectiveLocalShow, startCityShow, childrenNumShow, adultNumShow, selectFriendShow, hotelShow, budgetShow, userNameShow, phoneShow, emailShow, 0) values(#{createTime}, #{updateTime}, #{dzTypeShow}, #{startDateShow}, #{dayStrShow}, #{objectiveLocalShow}, #{startCityShow}, #{childrenNumShow}, #{adultNumShow}, #{selectFriendShow}, #{hotelShow}, #{budgetShow}, #{userNameShow}, #{phoneShow}, #{emailShow}, #{isCl})")
    int save(Customized customized);

    int count(@Param("params") Map<String, Object> params);

    List<Customized> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Update("update customized set isCl = 1 where id = #{id}")
    void updateIsCl(Long id);
}
