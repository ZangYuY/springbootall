package com.qf.service.ServiceImpl;

import com.qf.mapper.QingjiaMapper;
import com.qf.pojo.Holiday;
import com.qf.service.QingjiaService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QingjiaServiceImpl implements QingjiaService {

    @Autowired
    private QingjiaMapper qingjiaMapper;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    /**
     * 学生发起请假
     * @param holiday
     * @return
     */
    @Override
    public int addQingjia(Holiday holiday) {
        qingjiaMapper.addQingjia(holiday);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("stuName",holiday.getUsers().getUsername());
        map.put("teaName","teacher1");

        //发起流程实例
        runtimeService.startProcessInstanceByKey("qingjia",holiday.getHid()+"",map);

        //完成任务
        Task task = taskService.createTaskQuery().taskAssignee(holiday.getUsers().getUsername()).singleResult();
        taskService.complete(task.getId());
        return holiday.getHid();
    }

    @Override
    public List<Holiday> getApproveHolidayList(String uname) {
        //待办任务集合
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        List<String> bussinessKeys = new ArrayList<String>();
        //循环遍历获取bussiness keys,即请假条的id
        for (Task task : list){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }

        System.out.println("bussinessKeys:"+bussinessKeys.size());

        if(bussinessKeys.size()>0){
            List<Holiday> approveHolidayList = qingjiaMapper.getApproveHolidayList(bussinessKeys);
            return approveHolidayList;
        }
        return null;
    }

    @Override
    public int updQingjiaStatus(int hid,String uname) {
        //完成任务
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid + "").taskAssignee(uname).singleResult();
        taskService.complete(task.getId());
        //审核通过
        return qingjiaMapper.updQingjiaStatus(hid);
    }
}
