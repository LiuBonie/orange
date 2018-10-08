package com.applets.service.impl;

import com.applets.enetity.category;
import com.applets.mapper.categoryMapper;
import com.applets.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired(required=true)
    categoryMapper categoryMapper;

    @Override
    public Boolean createCategory(category category) {
        int length = categoryMapper.createCategory(category);
        if(length==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean updateCategory(category category) {
        int length = categoryMapper.updateCategory(category);
        if(length==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean deleteCateg(Integer id) {
        int length = categoryMapper.deleteCateg(id);
        if(length==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<category> findAllCategory() {
        List<category> categoryList = categoryMapper.findAllCategory();
            return categoryList;
    }

    @Override
    public Integer findIdByName(String name) {
        return categoryMapper.findIdByName(name);
    }
}
