package com.qf.controller;

import com.qf.pojo.Holiday;
import com.qf.pojo.Users;
import com.qf.service.QingjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class QingjiaController {

    @Autowired
    private QingjiaService qingjiaService;

    @RequestMapping("addQingPage")
    public String addQingPage(){
        return "addQingPage";
    }

    @RequestMapping("saveQing")
    public String saveQing(Holiday holiday, HttpSession session){

        Users user1 = (Users) session.getAttribute("users");
        holiday.setUsers(user1);
        System.out.println("user1:"+user1);
        System.out.println("holiday:"+holiday);

        qingjiaService.addQingjia(holiday);
        return "redirect:showPage";
    }
}
