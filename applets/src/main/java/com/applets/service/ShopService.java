package com.applets.service;

import com.applets.enetity.shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopService {

    List<shop> findShops();

    shop findShopsById( Integer shopId);

    Boolean deleteShop(Integer shopId);
}
