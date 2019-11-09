package com.qf.mapper;

import com.qf.pojo.Holiday;

import java.util.List;

public interface QingjiaMapper {

    //增加请假---学生发起请假
    public int addQingjia(Holiday holiday);

    //修改请假的状态--老师审批后
    public int updQingjiaStatus(int hid);

    /**
     * 查询待审批的假条列表
     * 参数list表示待审批的假条id集合
     * @param list
     * @return
     */
    public List<Holiday> getApproveHolidayList(List<String> list);
}
