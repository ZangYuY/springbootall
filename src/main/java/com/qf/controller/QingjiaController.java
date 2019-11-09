package com.qf.controller;

import com.qf.pojo.Holiday;
import com.qf.pojo.Users;
import com.qf.service.QingjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
      //  System.out.println("user1:"+user1);
       // System.out.println("holiday:"+holiday);

        qingjiaService.addQingjia(holiday);
        return "redirect:showPage";
    }

    /**
     * 查看老师审批的假条列表
     *
     * @return
     */
    @RequestMapping("aboQing")
    public String aboQing(HttpSession session, HttpServletRequest request){
        Users user1 = (Users) session.getAttribute("users");
        List<Holiday> holidayList = qingjiaService.getApproveHolidayList(user1.getUsername());
       /* for (Holiday holiday : holidayList){
            System.out.println(holiday);
        }*/
         System.out.println("holidayList:"+holidayList);

        request.setAttribute("holidayList",holidayList);
        return "teaQing";
    }

    @RequestMapping("updateHoliday")
    public String updateHoliday(int hid,HttpSession session){
        Users user1 = (Users) session.getAttribute("users");
        qingjiaService.updQingjiaStatus(hid,user1.getUsername());
        return "redirect:aboQing";
    }
}
