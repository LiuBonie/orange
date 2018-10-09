package com.applets.controller;


import com.applets.enetity.shop;
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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class shopCon {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;


    @GetMapping(value = "/shopAdd")
    public String productAdd(ModelMap map, HttpSession session) {

        return "shop-edit";
    }

    //新增商品
    @PostMapping(value = "/shop")
    public String addGood(@ModelAttribute @Validated shop shop, BindingResult result, RedirectAttributes redirectAttributes) {
        System.out.println(shop.getLatitude());
        List<String> errors = new ArrayList<String>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println("==============================" + field + ":" + message);
                errors.add(message);
            });
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/shop/shopAdd";
        }
        Date date = new Date();
        shop.setCreateTime(date);
        shopService.createShop(shop);
        return "redirect:/user/Shops";
    }


    //进入shop编辑界面
    @GetMapping("/shop/{id}")
    public String toShopEdit(@PathVariable("id") Integer id, ModelMap map, HttpSession session) {
        shop shop = shopService.findShopsById(id);
        map.addAttribute("shop", shop);
        return "shop-edit";
    }

    //update shop
    @PutMapping("/shop")
    public String updateShop(@Valid shop shop, BindingResult result, RedirectAttributes redirectAttributes) {
        List<String> errors = new ArrayList<String>();
        System.out.println(shop.getRecommend());
        if (result.hasErrors()) {
            result.getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                // 属性
                String field = fieldError.getField();
                // 错误信息
                String message = fieldError.getDefaultMessage();
                System.out.println("==============================" + field + ":" + message);
                errors.add(message);
            });
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/shop/shop/" + shop.getId();
        }
        shopService.updateaShop(shop);
        return "redirect:/user/Shops";
    }

    //删除shop
    @DeleteMapping("/shop/{id}")
    public String deleteShop(@PathVariable("id") Integer id) {
        Boolean aBoolean = shopService.deleteShop(id);
        System.out.println(aBoolean);
        return "redirect:/user/Shops";
    }
}
