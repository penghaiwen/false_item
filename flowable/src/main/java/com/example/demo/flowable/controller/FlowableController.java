package com.example.demo.flowable.controller;

import com.example.demo.flowable.service.ActTaskService;
import com.example.demo.flowable.service.ImageService;
import com.exception.RestBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/task")
public class FlowableController {
    @Resource
    private ActTaskService taskService;

    @Resource
    private ImageService imageService;

    @PostMapping(value = "begin")
    public RestBean begin(@ApiParam(value = "启动流程实例的key")String modelKey) {
        String processId = taskService.startProcess(modelKey, "1", null, null);
        return RestBean.ok(processId);
    }





    @GetMapping(value = "processDiagram")
    @ApiOperation(value = "获取流程图")
    public void getProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        byte[] bytes = imageService.getProcessDiagramByProcessId(processId);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            outputStream.write(bytes);
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}
