# 入门案例

具体可以参考官网的[入门教程](https://mybatis.org/mybatis-3/zh/getting-started.html)按照步骤进行即可

**大概过程**

> * 创建user表，添加数据
> * 创建模块，导入坐标
> * 编写MyBatis核心配置文件 --> 替换连接信息，解决硬编码问题
> * 编写SQL映射文件 --> 统一管理SQL语句，解决硬编码问题
> * 编码
>   * 定义POJO类
>   * 加载核心配置文件，获取SqlSessionFactory对象
>   * 获取SqlSession对象，执行SQL语句
>   * 释放资源

第一步，添加`mybatis`, `mysql-connector-java`的环境依赖

第二步，在resources中构建`mybatis-config.xml`文件，其中包含了数据库池，还有映射文件的管理

```xml
<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--数据库连接信息-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mybatis?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="194466"/>
            </dataSource>
        </environment>
</environments>
<mappers>
    <!--加载SQL映射文件-->
    <mapper resource="UserMapper.xml"/>
</mappers>
```

第三步，在主类中加载Mybatis核心配置文件，获取`SqlSessionFactory`

```java
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```

第四步，创建`UserMapper.xml`文件，在里面写上想要的SQL语句

```xml
<mapper namespace="test">
    <select id="selectAll" resultType="org.ytq.pojo.User">
        select * from tb_user;
    </select>
</mapper>
```

第五步，获取`sqlsession`对象，并用它来执行sql语句

```java
SqlSession sqlSession = sqlSessionFactory.openSession();
// 3. 执行sql
List<User> users = sqlSession.selectList("test.selectAll");
System.out.println(users);
```
