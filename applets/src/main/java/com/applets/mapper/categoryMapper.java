package com.applets.mapper;

import com.applets.enetity.category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface categoryMapper {

	    @Options(useGeneratedKeys = true,keyProperty = "id")
		@Insert("INSERT INTO category(parentId,name,status,createTime,updateTime) VALUES (#{parentId},#{name},#{status},#{createdTime},#{updateTime})")
    	int createCategory(category category);

		@Update("UPDATE category SET parentId=#{parentId},name=#{name},status=#{status},createdTime=#{createdTime},updateTime=#{updateTime} WHERE id=#{id}")
		int updateCategory(category category);

		@Delete("DELETE FROM category WHERE id=#{id}")
		int deleteCateg(Integer id);

		@Select("SELECT * from category")
		List<category> findAllCategory();

		@Select("SELECT id from category WHERE name=#{name}")
		Integer findIdByName(@Param("name") String name);

}
