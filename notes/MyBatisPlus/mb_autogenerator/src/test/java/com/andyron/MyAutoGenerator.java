package com.andyron;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Andy Ron
 */
// 代码自动生成器
public class MyAutoGenerator {
    public static void main(String[] args) {
        // 构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();
        // 配置策略

        // 1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // 项目根目录
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("AndyRon");
        gc.setOpen(false);  // 代码生成完是否打开目录
        gc.setFileOverride(false); //是否覆盖
        gc.setServiceName("%sService"); // 去Service接口I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 2. 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setUsername("root");
        dsc.setPassword("iop654321");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 3. 包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.andyron");
        pc.setEntity("entity"); // or pojo
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 4. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("college_professional"); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动Lombok

        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");

        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
        mpg.setStrategy(strategy);


        mpg.execute(); // 执行
    }

    @Test
    void test() {
        Properties properties = System.getProperties();
        for (String key: properties.stringPropertyNames()) {
            System.out.println(key + " = " + System.getProperty(key));
        }
    }
}
