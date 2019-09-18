package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("page",userList);
        return "user/list";
    }
    //用户点击 add 按钮，跳转到新增用户页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }
    //保存新增用户数据
    @PostMapping("/add")
    public String add(User user){
        userService.add(user);
        //跳转到列表页
        return "redirect:/";
    }
    //跳转到编辑页面
    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable("id") Long id){
        //读取对应 id 用户信息
        User user = userService.findOne(id);
        model.addAttribute("user",user);
        //跳转到编辑页面
        return "user/userEdit";
    }
    //保存修改用户数据
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user.getId(),user);
        //跳转到列表页
        return "redirect:/";
    }
    //删除用户数据
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        //跳转到列表页
        return "redirect:/";
    }
}
