package com.applets.controller;

import com.applets.enetity.category;
import com.applets.enetity.goods;
import com.applets.enetity.shop;
import com.applets.enetity.user;
import com.applets.service.CategoryService;
import com.applets.service.GoodsService;
import com.applets.service.ShopService;
import com.applets.service.UserService;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/home")
    public String home(ModelMap map){
        return "home";
    }

    @GetMapping(value = "/Products_List")
    public String Products_List(ModelMap map){
        List<category> allCategory = categoryService.findAllCategory();
        map.addAttribute("allCategory",allCategory);
        Page<goods> goodsPage = goodsService.findByPageAndShopId(1, 10, 1);
        List<goods> goodsList = goodsPage.getResult();
        map.addAttribute("goodsList",goodsList);
        return "Products_List";
    }

    @RequestMapping(value = "/Category_Manage")
    public String categoryMan(ModelMap map){
        return "Category_Manage";
    }

    @RequestMapping(value = "/Shops")
    public String shops(ModelMap map){
        return "Shops";
    }

    @RequestMapping(value = "/administrator")
    public String administrator(ModelMap map){
        return "administrator";
    }

    @RequestMapping(value = "/admin_info")
    public String admin_info(ModelMap map){
        return "admin_info";
    }

    @GetMapping(value = "/productAdd")
    public String productAdd(ModelMap map, HttpSession session){
        int shopId =0;
        //查询当前登录用户的商店，以此来选择商店分类
        String userName = session.getAttribute("loginUser").toString();
        List<user> allUser = userService.findAllUser();
        for (user user: allUser) {
            if(user.getName().equals(userName)){
                 shopId = user.getShopId();
            }
        }
        if (shopId !=0) {
            shop shopsById = shopService.findShopsById(shopId);
            map.addAttribute("shop",shopsById);
        }
        List<category> allCategory = categoryService.findAllCategory();
        map.addAttribute("allCategory",allCategory);

        return "product-add";
    }

    @RequestMapping(value = "/category_add")
    public String category_add(ModelMap map){
        return "product-category-add";
    }

    //新增商品
    @PostMapping(value = "/goods")
    public  String addGood(@RequestParam String first,String second,@ModelAttribute goods goods) {
        //需要做数据验证
        //...................
        Integer categoryId;
        Date date = new Date();
        if (second.equals("---请选择---")){
            categoryId = categoryService.findIdByName(first);
        }else{
            categoryId = categoryService.findIdByName(second);
        }
        goods.setCategoryId(categoryId);

        goods.setCreateTime(date);
        goods.setUpdateTime(date);
     goodsService.createGoods(goods);
        return "redirect:/user/Products_List";
    }
    @PostMapping(value = "/changeStatus")
    public String changeStatus(@RequestParam Integer status,Integer id){
        goodsService.changeStatusById(id,status);
        return "redirect:/user/Products_List";
    }

    @PostMapping(value = "/deleteGoods")
    public String findGoodsByUpdateTime(@RequestParam List<Integer> checkedList) throws ParseException {
        for (Integer checked:checkedList) {
            goodsService.deleteGoods(checked);
        }
        return "redirect:/user/Products_List";
    }

    @GetMapping(value = "/goods/{id}")
    public String goUpdateView(@PathVariable("id") Integer id,ModelMap map,HttpSession session){
        int shopId =0;
        //查询当前登录用户的商店，以此来选择商店分类
        String userName = session.getAttribute("loginUser").toString();
        List<user> allUser = userService.findAllUser();
        for (user user: allUser) {
            if(user.getName().equals(userName)){
                shopId = user.getShopId();
            }
        }
        if (shopId !=0) {
            shop shopsById = shopService.findShopsById(shopId);
            map.addAttribute("shop",shopsById);
        }
        List<category> allCategory = categoryService.findAllCategory();
        map.addAttribute("allCategory",allCategory);
        goods goods = goodsService.findGoodsByGoodsId(id);
        map.addAttribute("goods",goods);
        return "product-add";
    }

    //商品修改
    @PutMapping("/goods")
    public String updateGoods(goods goods){
        Date date = new Date();
        goods.setUpdateTime(date);
       goodsService.updateaGoods(goods);
        return "redirect:/user/Products_List";
    }

    //商品删除
    @DeleteMapping("/goods/{id}")
    public String deleteGoods(@PathVariable("id") Integer id){
        goodsService.deleteGoods(id);
        return "redirect:/user/Products_List";
    }
}
