package org.crawler;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

@Data
public class CodeGenerator {

    /**
     * 配置文件名
     */
    private final static String APP_PROPERTY = "application-dev.yml";
    private String projectPath = System.getProperty("user.dir");
    /**
     * 公共包路径
     */
    private String parentPackage = "org.crawler";
    /**
     * 模块名
     */
    private String module = "";

    /**
     * 作者
     */
    private String author = "liushuwei";

    /**
     * 将要生成的表名
     */
//    private String [] tables = {"crawler_company_shorter", "crawler_company_list"};
    private String [] tables = {"crawler_credit_china_company_list"};


    /**
     * 自定义模板位置
     */
    private String templatePath = "templates/mp/";
    private String controllerTemplate = templatePath + "controller.java";
    private String serviceTemplate = templatePath + "service.java";
    private String serviceImplTemplate = templatePath + "serviceImpl.java";
    private String mapperTemplate = templatePath + "mapper.java";


    /**
     * 启动主方法
     */
    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.execute();
        System.out.println("生成 Controller，Service，ServiceImpl，Mapper 完毕");
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != null && !ipt.trim().isEmpty()) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public void execute() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
            gc.setOutputDir(projectPath + "/src/main/java");
            gc.setAuthor(author);
            gc.setOpen(false);
            gc.setActiveRecord(true);
            gc.setIdType(IdType.AUTO);
            gc.setServiceName("%sService");
            gc.setBaseResultMap(true);
            gc.setBaseColumnList(true);

            // 是否覆盖原文件
            gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        mpg.setDataSource(dataSourceConfig());

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setModuleName(module);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setXml(null);
        templateConfig.setService(serviceTemplate);
        templateConfig.setServiceImpl(serviceImplTemplate);
        templateConfig.setMapper(mapperTemplate);
        templateConfig.setController(controllerTemplate);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
      //  strategy.setSuperEntityColumns("id");

        // 前缀
        strategy.setTablePrefix("crawler_");

        // 循环生成表
        strategy.setInclude(tables);

        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);


        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = null;

        String resourcePath = System.getProperty("user.dir") + "/src/main/resources/" + APP_PROPERTY;
        try {
            InputStream inStream = new FileInputStream(new File(resourcePath));
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.loadAs(inStream, Map.class);
            Map<String, Object> dataSourceMap = (Map<String, Object>)((Map<String, Object>) map.get("spring")).get("datasource");

            dsc = new DataSourceConfig();
                dsc.setUrl((String) dataSourceMap.get("url"));
                dsc.setDriverName((String) dataSourceMap.get("driver-class-name"));
                dsc.setUsername((String) dataSourceMap.get("username"));
                dsc.setPassword((String) dataSourceMap.get("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dsc;
    }



}