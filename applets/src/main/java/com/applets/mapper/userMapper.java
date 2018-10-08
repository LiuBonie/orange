package com.applets.mapper;

import com.applets.enetity.user;
import com.github.pagehelper.Page;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface userMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO user(name,password,telNum,email,shopId,root) VALUES (#{name},#{password},#{telNum},#{email},#{shop_id},#{root})")
	int	createUser(user user);              //新增用户

    @Update("UPDATE user SET name=#{name},password=#{password},telNum=#{telNum},email=#{email},shopId=#{shop_id},root=#{root} WHERE id =#{id}")
	int	updataUser(user user);			    //更新用户信息

    @Delete("DELETE FROM user WHERE id = #{id}")
	int	deleteUser(Integer id);			    //删除用户

    @Select("SELECT * FROM user")
	List< user>	findAllUser();			  	    //查询所有用户

    @Select("SELECT * FROM user")
    Page<user> findAllUserForPage();			  	    //查询所有用户

    @Select("SELECT * FROM user WHERE name=#{name} AND password = #{pwd}")
    user findAllUserByNamePwd(@Param("name")String name,@Param("pwd") String pwd);			  	    //查询相关用户


}
