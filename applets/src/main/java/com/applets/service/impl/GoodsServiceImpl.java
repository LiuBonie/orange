package com.applets.service.impl;

import com.applets.enetity.goods;
import com.applets.mapper.goodsMapper;
import com.applets.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required=true)
    goodsMapper goodsMapper;


    @Override
    public Page<goods> findByPageAndShopId(int pageNo, int pageSize,int shopId) {
        PageHelper.startPage(pageNo, pageSize);
        return goodsMapper.findAllGoodsByShopId(shopId);
    }

    @Override
    public goods findGoodsByGoodsId(Integer Id) {
        return goodsMapper.findGoodsByGoodsId(Id);
    }

    @Override
    public Page<goods> findByPageAndCategoryId(int pageNo, int pageSize, int categoryId) {
        PageHelper.startPage(pageNo, pageSize);
        return goodsMapper.findGoodsByCategoryId(categoryId);
    }

    @Override
    public Boolean createGoods(goods goods) {
        int length = goodsMapper.createGoods(goods);
        if(length==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean updateaGoods(goods goods) {
        int i = goodsMapper.updateaGoods(goods);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean deleteGoods(Integer id) {
        int i = goodsMapper.deleteGoods(id);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean updateCategoryById(Integer goodsId, Integer categoryId) {
        int i = goodsMapper.updateCategoryById(goodsId, categoryId);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void changeStatusById(Integer goodsId, Integer status) {
        goodsMapper.changeStatusById(goodsId,status);
    }

    @Override
    public List<goods> findGoodsByUpdateTime(Date updateTime) {
        return goodsMapper.findGoodsByUpdateTime(updateTime);
    }
}
