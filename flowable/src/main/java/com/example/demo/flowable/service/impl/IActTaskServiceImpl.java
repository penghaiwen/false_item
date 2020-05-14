package com.example.demo.flowable.service.impl;

import com.example.demo.flowable.service.ActTaskService;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class IActTaskServiceImpl implements ActTaskService {

    @Resource
    private RuntimeService runtimeService;
    
    @Resource
    private TaskService taskService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private HistoryService historyService;




    @Override
    public String startProcess(String procDefKey, String businessId, Map<String, Object> vars, Long assignee) {
        if(null==vars){
            vars=new HashMap<>();
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(procDefKey,businessId, vars);
        return processInstance.getId();
    }


}
