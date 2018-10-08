package com.applets.service;

import com.applets.enetity.category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {

    	Boolean createCategory(category category);
        Boolean updateCategory(category category);
        Boolean deleteCateg(Integer id);
	    List< category > findAllCategory();
        Integer findIdByName(String name);
}
