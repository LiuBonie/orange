package com.applets.service.impl;

import com.applets.enetity.shop;
import com.applets.mapper.shopMapper;
import com.applets.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired(required=true)
    shopMapper shopMapper;

    @Override
    public List<shop> findShops() {
        return shopMapper.findShops();
    }

    @Override
    public shop findShopsById(Integer shopId) {
        return shopMapper.findShopsById(shopId);
    }

    @Override
    public Boolean deleteShop(Integer shopId) {

        int i = shopMapper.deleteShop(shopId);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }
}
