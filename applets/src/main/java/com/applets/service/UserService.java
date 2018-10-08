package com.applets.service;

import com.applets.enetity.user;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

 	Boolean	createUser(user user);             // 新增用户
	Boolean	updataUser(user user);			    //更新用户信息
	Boolean	deleteUser(Integer id);			   // 删除用户
	Page<user> findAllUserForPage(int pageNo, int pageSize);			  	    //查询所有用户
	Boolean	findAllUserByNamePwd(String name,String pwd);			  	    //查询相关用户
	List< user>	findAllUser();			  	    //查询所有用户
}
