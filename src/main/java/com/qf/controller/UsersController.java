package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Users;
import com.qf.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private SecurityManager securityManager;


    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("loginP")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(Users users , HttpSession session){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(users.getUsername(),users.getPassword());
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){

                Users userByUsername = usersService.getUserByUsername(users.getUsername());


                session.setAttribute("users",userByUsername);
                return "redirect:showPage";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginP";
    }
    @RequestMapping("showPage")
    public String showPage(Model model, @RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(defaultValue = "2") Integer pageNum){

        PageInfo<Users> users = usersService.getUsers(pageNo,pageNum);
        //System.out.println("users:"+users);
        model.addAttribute("users",users);
        return "showPage";
    }

    @RequestMapping("addPage")
    public String addPage(){
        return "addUser";
    }

    @RequestMapping("savUser")
    @ResponseBody
    public String addUserPage(Users users){
       // System.out.println("users:"+users);
        int i = usersService.addUser(users);
        if(i>0){
            return "redirect:showPage";
        }
        return "file";

    }

    @RequestMapping("unauth")
    public String unauth(){
        return "unauth";
    }

    @RequestMapping("ech")
    public String ech(){
        return "echarts";
    }
}
