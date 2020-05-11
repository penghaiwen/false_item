package com.example.demo.flowable.service.impl;

import com.example.demo.flowable.service.ActTaskService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class IActTaskServiceImpl implements ActTaskService {

    @Resource
    private RuntimeService runtimeService;


    @Override
    public String startProcess(String procDefKey, String businessId, Map<String, Object> vars, Long assignee) {




        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(procDefKey, vars);
        return null;
    }
}
