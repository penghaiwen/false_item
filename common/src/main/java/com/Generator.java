package com;


import com.generator.CodeGenerator;

/**
 * 自动生成代码工具
 *
 * @author hdj
 * @since 2019/1/25 17:28
 **/
public class Generator {
    public static void main(String[] args) {
        CodeGenerator.generator("/pro-auth","sys","1","sys_role");
    }
}
