package com.applets.controller;

import com.applets.enetity.goods;
import com.applets.enetity.shop;
import com.applets.service.GoodsService;
import com.applets.service.ShopService;
import com.applets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/applets")
public class AppletsCon {

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    GoodsService goodsService;

    //小程序测试
    @GetMapping(value = "/bindtext",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object bindtext(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //获取微信小程序get的参数值并打印
        String username = request.getParameter("username");
        String passsword = request.getParameter("password");
        System.out.println("username:" + username + "passsword:" + passsword);

        //返回值给微信小程序
//        Writer out = response.getWriter();
//        out.write("进入后台了go gogo");
//        out.flush();
        userService.findUser(1);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("fd", "郝鹏");
        map.put("dd", "郝鹏");
        return map;
    }

    @GetMapping(value = "/getHotShop",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<shop> getHotShop(){
        List<shop> shopList = new ArrayList<>();
        shopList.add( shopService.findShopsById(2));
        shopList.add( shopService.findShopsById(3));
        shopList.add( shopService.findShopsById(4));
        return shopList;
    }

    @GetMapping(value = "/getMyLoveShop",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<shop> getMyLoveShop(){
        List<shop> shopList = new ArrayList<>();
        shopList.add( shopService.findShopsById(5));
        shopList.add( shopService.findShopsById(6));
        shopList.add( shopService.findShopsById(7));
        return shopList;
    }

    //根据关键字返回top5的商品
    @GetMapping(value = "/getTop5GoodsByKeywords",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<goods> getTop5GoodsByKeywords(@RequestParam("keywords") String keywords){
        List<goods> goodsList  = goodsService.findTop5GoodsByKeywords(keywords);

            return goodsList;
    }

    //根据商品名搜索所有商品
    @GetMapping(value = "/getAllGoodsByGoodsName",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<goods> getAllGoodsByGoodsName(@RequestParam("GoodsName") String goodsName){
        System.out.println(goodsName);
        try {
            List<goods> goodsList = goodsService.findAllGoodsByGoodsName(goodsName);
            return goodsList;
        }catch (Exception e) {
            return null;
        }
    }
    //根据goodid返回goods和shop
    @GetMapping(value = "/getGoodsByGoodId",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> getGoodsByGoodId(@RequestParam("goodId") Integer goodId) {
        goods good = goodsService.findGoodsByGoodsId(goodId);
        shop shop= shopService.findShopsById(good.getShopId());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("good",good);
        map.put("shop",shop);
        return map;
    }


}
