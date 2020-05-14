package com.example.demo.flowable.service;

import java.util.Map;

public interface ActTaskService {
    /**
     *功能描述
     * @author 老默
     * @date 2020/5/11
     * @time 18:01
     * @param procDefKey 流程实例
     * @param businessId 业务编号
     * @param vars 参数
     * @param assignee 下一审核人
     * @return java.lang.String
     */
    String startProcess(String procDefKey, String businessId, Map<String, Object> vars, Long assignee);


}
