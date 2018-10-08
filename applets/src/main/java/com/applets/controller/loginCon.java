package com.applets.controller;

import com.applets.enetity.category;
import com.applets.enetity.user;
import com.applets.service.CategoryService;
import com.applets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class loginCon {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/")
    public String login(ModelMap map,HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }

    @GetMapping(value = "/index")
    public String mian(ModelMap map){
        map.addAttribute("action", "home");
        return "index";
    }
//登录逻辑
    @PostMapping(value = "/login")
   // @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String log(ModelMap map,@RequestParam String username,String userpwd,HttpSession session){
        Boolean userExist = userService.findAllUserByNamePwd(username, userpwd);
        List<category> allCategory = categoryService.findAllCategory();
        if (userExist == true){
            session.setAttribute("loginUser",username);
            //登陆成功，防止表单重复提交，可以重定向到主页@GetMapping(value = "/index")方法
            return "redirect:/index";
        }else {
            map.addAttribute("msg","账号或密码错误");
            return "login";
        }
    }
    //登录成功后在返回
    //@RequestMapping(value = "/login",method = RequestMethod.GET)
    @GetMapping(value = "/login")
    public String logg(ModelMap map,HttpSession session){
        map.addAttribute("msg","");
        session.removeAttribute("loginUser");
            return "login";

    }
}
