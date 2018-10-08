package com.applets.service;

import com.applets.enetity.goods;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsService {

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<goods> findByPageAndShopId(int pageNo, int pageSize, int shopId);

    goods findGoodsByGoodsId(Integer Id);//根据ID查询商品

    Page<goods> findByPageAndCategoryId(int pageNo, int pageSize, int categoryId);

    Boolean	createGoods(goods goods);		   // 新增商品

    Boolean	updateaGoods(goods goods);	   // 更新商品信息

    Boolean deleteGoods(Integer id);		    //删除商品

    Boolean updateCategoryById(Integer goodsId,Integer categoryId);   //修改某件商品的类别

    void changeStatusById( Integer goodsId,Integer status);//更改商品状态

    List<goods> findGoodsByUpdateTime(Date updateTime);//根据日期差找商品
}

