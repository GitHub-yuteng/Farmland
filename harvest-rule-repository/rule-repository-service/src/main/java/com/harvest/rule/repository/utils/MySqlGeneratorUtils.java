package com.harvest.rule.repository.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alodi
 */
public class MySqlGeneratorUtils {

    private static final String PARENT_DIR = System.getProperty("user.dir");
    private static final String ENTITY_PATH = PARENT_DIR + "/harvest-rule-repository/rule-repository-entity/src/main/java/com/harvest/rule/repository/entity";
    private static final String MAPPER_PATH = PARENT_DIR + "/harvest-rule-repository/rule-repository-service/src/main/java/com/harvest/rule/repository/mapper";
    private static final String XML_PATH = PARENT_DIR + "/harvest-rule-repository/rule-repository-service/src/main/resources/mapper";

    private static final String MYSQL_IP = "jdbc:mysql://localhost:3306/farmland_rule";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("farmland_rule_logistics_match");
        list.add("farmland_rule_logistics_match_section");
        list.add("farmland_rule_logistics_match_smart");
        list.add("farmland_rule_order_merge");
        list.add("farmland_rule_order_split");
        list.add("farmland_rule_task_auto");
        list.add("farmland_rule_task_retry");
        list.forEach(MySqlGeneratorUtils::execute);
    }

    /**
     * 获取路径信息map
     *
     * @return {@link Map< OutputFile, String> }
     * @author MK
     * @date 2022/4/21 21:21
     */
    private static Map<OutputFile, String> pathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        return pathInfo;
    }

    private static void execute(String table) {
        FastAutoGenerator.create(MYSQL_IP, USERNAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("Alodi")
                        .disableOpenDir()
                        .enableSwagger()
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                        .dateType(DateType.TIME_PACK))
                .packageConfig(builder -> builder
                        .pathInfo(pathInfo())
                        .parent("")
                        .controller("")
                        .serviceImpl("")
                        .service("")
                        .entity("com.harvest.rule.repository.entity")
                        .mapper("com.harvest.rule.repository.mapper")
                        .xml("com.harvest.rule.repository.mapper"))

                .templateConfig(template -> {
                    //template.entity("/src/main/com/scaffolding/pojo/entity/");
                })
                .strategyConfig(builder -> {
                    builder.addInclude(table);
                    builder.entityBuilder()
                            .enableLombok()
                            .enableFileOverride()
                            .logicDeleteColumnName("is_deleted")
                            .disableSerialVersionUID()
                            .enableTableFieldAnnotation()
                            .addTableFills(new Column("rc_time", FieldFill.INSERT)) // 表字段填充
                            .addTableFills(new Column("rm_time", FieldFill.INSERT_UPDATE)) // 表字段填充
                            .formatFileName("%sEntity");

                    // 取消生成 Controller 文件
                    builder.controllerBuilder()
                            .enableFileOverride()
                            .formatFileName("");

                    // 取消生成 Service ServiceImpl 文件
                    builder.serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("")
                            .formatServiceImplFileName("");

                    // mapper 设置
                    builder.mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .enableFileOverride()
                            .enableBaseColumnList() // 启用 columnList (通用查询结果列)
                            .enableBaseResultMap() // 启动resultMap
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}