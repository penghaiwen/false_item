package com.example.demo.flowable.controller;

import com.example.demo.flowable.service.ActTaskService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FlowableController {
    @Resource
    private ActTaskService taskService;

    @PostMapping(value = "begin")
    public String begin(@ApiParam(value = "启动流程实例的key")String modelKey) {
        return "提交成功.流程Id为：" ;
    }
}
