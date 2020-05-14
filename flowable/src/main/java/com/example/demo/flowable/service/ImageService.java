package com.example.demo.flowable.service;

public interface ImageService {

    /**
     *功能描述 获取流程图
     * @author 老默
     * @date 2020/5/12
     * @time 9:44
     * @param processId
     * @return void
     */
    byte[] getProcessDiagramByProcessId( String processId);
}
