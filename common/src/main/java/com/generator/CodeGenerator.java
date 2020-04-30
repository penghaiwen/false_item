package com.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成类
 *
 * @author hdj
 * @since 2019/1/25 16:40
 **/
public class CodeGenerator {

    private static final String URL = JdbcConst.URL;
    private static final String USERNAME = JdbcConst.USERNAME;
    private static final String PASSWORD = JdbcConst.PASSWORD;



    private static StrategyConfig getStrategyConfig(String model, String tableName) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        if (model.equals("1")) {
            strategy.setInclude(tableName);
        }
        if (model.equals("2")) {
            List<String> preTableName = PreparedStatement.getPreTableName(tableName);
            String[] strings = new String[preTableName.size()];
            strategy.setInclude(preTableName.toArray(strings));
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_", "bbt_","", "mg_");
        List<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("create_time", FieldFill.INSERT));
        fillList.add(new TableFill("created_dt", FieldFill.INSERT));
        fillList.add(new TableFill("create_by", FieldFill.INSERT));
        fillList.add(new TableFill("update_by", FieldFill.INSERT_UPDATE));
        fillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        strategy.setVersionFieldName("version");
        strategy.setTableFillList(fillList);// 字段自定义填充
        strategy.setLogicDeleteFieldName("delete_flag");// 设置逻辑删除字段属性名
        return strategy;
    }

    public static void generator(String packagePath, String moduleName, String model, String tableName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + packagePath;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("老默");
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setActiveRecord(true);
        gc.setBaseResultMap(false);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.example.demo");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 策略配置
        mpg.setStrategy(getStrategyConfig(model, tableName));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
