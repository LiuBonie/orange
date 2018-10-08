package com.applets.service.impl;

import com.applets.enetity.goods;
import com.applets.enetity.user;
import com.applets.mapper.userMapper;
import com.applets.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired(required=true)
    userMapper userMapper;

    @Override
    public Boolean createUser(user user) {
        int i = userMapper.createUser(user);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean updataUser(user user) {
        int i = userMapper.updataUser(user);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean deleteUser(Integer id) {
        int i = userMapper.deleteUser(id);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Page<user> findAllUserForPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userMapper.findAllUserForPage();
    }

    @Override
    public Boolean findAllUserByNamePwd(String name, String pwd) {
        user user = userMapper.findAllUserByNamePwd(name, pwd);
        if (user != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<user> findAllUser() {
        return userMapper.findAllUser();
    }
}
