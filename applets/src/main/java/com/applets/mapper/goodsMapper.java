package com.applets.mapper;

import com.applets.enetity.goods;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface goodsMapper {

    @Select("SELECT * FROM goods WHERE shop_id = #{shopId}")
    Page<goods> findAllGoodsByShopId(Integer shopId);//查看所属商铺的商品

    @Select("SELECT * FROM goods WHERE id = #{Id}")
    goods findGoodsByGoodsId(@Param("Id") Integer Id);//根据ID查询商品

    @Select("SELECT * FROM goods WHERE categoryId = #{category_id}")
    Page<goods> findGoodsByCategoryId(Integer categoryId);//查询不同类别的商品

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO goods(category_id,shop_id,name,image_url,detail,status,create_time,update_time,stock,price) " +
            "VALUES(#{categoryId},#{shopId},#{name},#{ImageUrl},#{detail},#{status},#{createTime},#{updateTime},#{stock},#{price})")
    int	createGoods(goods goods);		   // 新增商品

    @Update("UPDATE goods SET category_id=#{categoryId},shop_id=#{shopId},name=#{name},image_url=#{ImageUrl},detail=#{detail}," +
            "status=#{status},create_time=#{createTime},update_time=#{updateTime},price=#{price},stock=#{stock} WHERE id=#{id}")
    int	updateaGoods(goods goods);	   // 更新商品信息

    @Delete("DELETE FROM goods WHERE id = #{id}")
    int deleteGoods(Integer id);		    //删除商品

    @Update("UPDATE goods SET category_id = #{categoryId} WHERE id = #{goodsId}")
    int updateCategoryById(Integer goodsId,Integer categoryId);   //修改某件商品的类别

    @Update("UPDATE goods SET status = #{status} WHERE id = #{goodsId}")
    void changeStatusById(@Param("goodsId") Integer goodsId, @Param("status") Integer status);//更改商品状态

    @Select("SELECT * FROM goods WHERE update_time = #{updateTime}")
    List<goods> findGoodsByUpdateTime(@Param("updateTime") Date updateTime);//根据日期差找商品

}
