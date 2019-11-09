package com.qf.mapper;

import com.qf.pojo.Holiday;

public interface QingjiaMapper {

    //增加请假---学生发起请假
    public int addQingjia(Holiday holiday);

    //修改请假的状态--老师审批后
    public int updQingjiaStatus(int hid);
}
