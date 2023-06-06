package org.ytq.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class CodeGenerator {
    public static void main(String[] args) {
        // 获取代码生成器的对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 连那个数据库
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_db?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("194466");
        autoGenerator.setDataSource(dataSource);


        // 设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置目录路径
        globalConfig.setOutputDir("C:\\Users\\Fredrick\\Documents\\GitHub\\Unicom_Foundation\\Mybatis\\mybatisplus\\src\\main\\java");
        // 设置代码生成完毕后是否直接打开生成代码所在的目录
        globalConfig.setOpen(false);
        // 生成作者
        globalConfig.setAuthor("于天奇");
        // 设置是否覆盖源文件
        globalConfig.setFileOverride(true);
        // 设置数据层接口名, %s为占位符, 指代模块名称
        globalConfig.setMapperName("%SDao");
        // 设置id生成策略
        globalConfig.setIdType(IdType.AUTO);
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.execute();

        // 设置包名相关的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("org.ytq");
        // 设置实体类包名
        packageConfig.setEntity("domain");
        // 设置数据层包名
        packageConfig.setMapper("dao");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置...

    }

}

