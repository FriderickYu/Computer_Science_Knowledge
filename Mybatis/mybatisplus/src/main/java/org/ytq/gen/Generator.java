package org.ytq.gen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class Generator {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        // 连那个数据库
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_db?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("194466");
        autoGenerator.setDataSource(dataSource);
        autoGenerator.execute();
    }
}
