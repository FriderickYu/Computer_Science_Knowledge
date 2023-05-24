# 依赖管理

依赖，当前项目运行所需的`jar`包，一个项目可以设置多个依赖

具体依赖的用法如下, 引入`mybatis`和`log4j`

```xml
<dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.13</version>
        </dependency>
</dependencies>
```

## 依赖传递

假设: project01和project02同时存在，project01需要用到project02，正确的思路是将project02打包成`jar`包并引入到project01中

project01 will be like:

```xml
<dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.13</version>
        </dependency>
        <!--引入project02-->
        <dependency>
            <groupId>com.ytq</groupId>
            <artifactId>project02</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--引入project02-->
</dependencies>
```

* 直接依赖：在当前项目中通过依赖配置建立的依赖关系
* 间接依赖：被引用的资源如果还依赖其他的资源，则当前项目间接依赖其他的资源

如果在引用资源过程中，想要排除掉某些不想引用的资源，可以这么做：

```xml
<exclusions>
    <exclusion>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <!--不需要指明特定的版本，排除的是所有的版本-->
    </exclusion>
</exclusions>
```

## 依赖范围

* 依赖的`jar`默认情况可以在任何地方使用，可以通过`scope`标签设定其作用范围
* 作用范围
  * 主程序范围有效(`main`文件夹范围内)
  * 测试程序范围有效(`test`文件夹范围内)
  * 是否参与打包(`package`指令范围内)


|    `scope`    | 主代码 | 测试代码 | 打包 |     范例      |
|:-------------:|:---:|:----:|:--:|:-----------:|
| `compile`(默认) |  Y  |  Y   | Y  |    `log4j`    |
|    `test`     |     |  Y   |    |    `junit`    |
|  `provided`   |  Y  |  Y   |    | `servlet-api` |
|   `runtime`    |     |      | Y  |    `JDBC`     |