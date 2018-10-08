package com.applets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/link")
@Controller
public class LinkController {

    private static final String INDEX_PATH_NAME = "index";

    @RequestMapping(value = "/product")
    public String product(ModelMap map){
        map.addAttribute("action", "Products_List");
        return INDEX_PATH_NAME;
    }

    @RequestMapping(value = "/category")
    public String category(ModelMap map){
        map.addAttribute("action", "Category_Manage");
        return INDEX_PATH_NAME;
    }

    @RequestMapping(value = "/shops")
    public String shop(ModelMap map){
        map.addAttribute("action", "Shops");
        return INDEX_PATH_NAME;
    }

    @RequestMapping(value = "/administrator")
    public String administrator(ModelMap map){
        map.addAttribute("action", "administrator");
        return INDEX_PATH_NAME;
    }

    @RequestMapping(value = "/admin_info")
    public String admin_info(ModelMap map){
        map.addAttribute("action", "admin_info");
        return INDEX_PATH_NAME;
    }

}
