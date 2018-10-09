package com.applets.controller;


import com.applets.enetity.category;
import com.applets.enetity.goods;
import com.applets.enetity.shop;
import com.applets.enetity.user;
import com.applets.service.CategoryService;
import com.applets.service.GoodsService;
import com.applets.service.ShopService;
import com.applets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsCon {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;


    @GetMapping(value = "/productAdd")
    public String productAdd(ModelMap map, HttpSession session){
        //查询当前登录用户的商店，以此来选择商店分类
        String loginUserName = session.getAttribute("loginUser").toString();
        user loginUser = userService.findUserByName(loginUserName);
        shop shopsById = shopService.findShopsById(loginUser.getShopId());
        List<category> allCategory = categoryService.findAllCategory();
        map.addAttribute("shop",shopsById);
        map.addAttribute("allCategory",allCategory);

        return "product-add";
    }

    //新增商品
    @PostMapping(value = "/goods")
    public  String addGood(@RequestParam String first, String second, @ModelAttribute @Validated goods goods, BindingResult result,RedirectAttributes redirectAttributes) {
        List<String> errors = new ArrayList<String>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println("=============================="+field + ":" + message);
                errors.add(message);
            });
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/goods/productAdd";
        }
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
    public String deleteGoods(@RequestParam List<Integer> checkedList) throws ParseException {
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
    public String updateGoods(@Validated goods goods, BindingResult result,RedirectAttributes redirectAttributes){
        List<String> errors = new ArrayList<String>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println("=============================="+field + ":" + message);
                errors.add(message);
            });
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/goods/goods/"+goods.getId();
        }
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
