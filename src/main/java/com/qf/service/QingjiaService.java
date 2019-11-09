package com.qf.service;

import com.qf.pojo.Holiday;

import java.util.List;

public interface QingjiaService {

    //增加请假---学生发起请假
    public int addQingjia(Holiday holiday);

    //待审批系列
    public List<Holiday> getApproveHolidayList(String uname);

    //修改请假的状态--老师审批后
    public int updQingjiaStatus(int hid,String uname);
}
