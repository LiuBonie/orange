package com.applets.mapper;

import com.applets.enetity.shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface shopMapper {

    @Select("SELECT * FROM shop")
    List<shop> findShops();

    @Select("SELECT * FROM shop WHERE id = #{shopId}")
    shop findShopsById(Integer shopId);

    @Delete("DELETE FROM shop WHERE id = #{shopId}")
    int deleteShop(Integer shopId);
}
