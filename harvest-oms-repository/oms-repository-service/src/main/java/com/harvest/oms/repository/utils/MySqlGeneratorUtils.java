package com.harvest.oms.repository.utils;

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
    private static final String ENTITY_PATH = PARENT_DIR + "/harvest-oms-repository/oms-repository-entity/src/main/java/com/harvest/oms/repository/entity";
    private static final String MAPPER_PATH = PARENT_DIR + "/harvest-oms-repository/oms-repository-service/src/main/java/com/harvest/oms/repository/mapper";
    private static final String XML_PATH = PARENT_DIR + "/harvest-oms-repository/oms-repository-service/src/main/resources/mapper";

    private final static String MYSQL_IP = "jdbc:mysql://localhost:3306/farmland_oms";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("farmland_oms_after_sale_bill");
        list.add("farmland_oms_after_sale_bill_item");
        list.add("farmland_oms_after_sale_bill_log");
        list.add("farmland_oms_inbound_bill");
        list.add("farmland_oms_inbound_bill_item");
        list.add("farmland_oms_order");
        list.add("farmland_oms_order_address");
        list.add("farmland_oms_order_item");
        list.add("farmland_oms_order_remark");
        list.add("farmland_oms_order_sensitive_data");
        list.add("farmland_oms_order_tag");
        list.add("farmland_oms_order_template_export");
        list.add("farmland_oms_order_template_import");
        list.add("farmland_oms_order_value_map");
        list.add("farmland_oms_outbound_bill");
        list.add("farmland_oms_outbound_bill_item");
        list.add("farmland_oms_snapshot");
        list.forEach(MySqlGeneratorUtils::execute);
    }

    /**
     * ??????????????????map
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
                        .entity("com.harvest.oms.repository.entity")
                        .mapper("com.harvest.oms.repository.mapper")
                        .xml("com.harvest.oms.repository.mapper"))

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
                            .addTableFills(new Column("rc_time", FieldFill.INSERT)) // ???????????????
                            .addTableFills(new Column("rm_time", FieldFill.INSERT_UPDATE)) // ???????????????
                            .formatFileName("%sEntity");

                    // ???????????? Controller ??????
                    builder.controllerBuilder()
                            .enableFileOverride()
                            .formatFileName("");

                    // ???????????? Service ServiceImpl ??????
                    builder.serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("")
                            .formatServiceImplFileName("");

                    // mapper ??????
                    builder.mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            .enableFileOverride()
                            .enableBaseColumnList() // ?????? columnList (?????????????????????)
                            .enableBaseResultMap() // ??????resultMap
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                })
                // ??????Freemarker???????????????????????????Velocity????????????
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
