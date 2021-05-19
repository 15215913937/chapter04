package com.itheima.controller;

import com.itheima.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Controller
public class LoginController {
    /**
     * 获取并封装当前年份跳转到登录页login.html
     * @param model
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String Login(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login";
    }

    @PostMapping("/login")
    public String toLogin(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getUsername())&&"123".equals(user.getPassword())){
            session.setAttribute("loginUser",user);
            return "redirect:index";
        }else{
            model.addAttribute("msg","账号密码错误");
            return "login";
        }

    }

    @GetMapping("/index")
    public String toIndex(HttpSession session,Model model){
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser!=null){
            return "index";
        }else{
            model.addAttribute("msg","请登录");
            return "login";
        }
    }
}
