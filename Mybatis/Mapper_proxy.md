# Mapper代理开发

**目的**

> * 解决硬编码的问题
> * 简化后期执行SQL

以前

```java
// 2. 获取sqlsession对象，用它执行sql
SqlSession sqlSession = sqlSessionFactory.openSession();
// 3. 执行sql
List<User> users = sqlSession.selectList("test.selectAll");
System.out.println(users);
```

改进后

```java
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
List<User> users = userMapper.selectAll();
```

## Mapper代理方式 规范

1. 定义与SQL映射文件同名的Mapper接口，并且将Mapper接口和SQL映射文件放置在同一目录下
2. 设置SQL映射文件的namespace属性为Mapper接口全限定名
3. 在Mapper接口中定义方法，方法名就是SQL映射文件的SQL语句的id，并保持参数类型和返回值类型一致
4. 编码
   1. 通过SqlSession的`getMapper`方法获取Mapper接口的代理对象
   2. 调用对应方法完成SQL的执行

第一个: 在主文件夹下创建`mapper`目录，并在其中创建`UserMapper`接口；可以直接把`UserMapper.xml`挪到该文件夹下，也可以在`resources`目录下创建相同的额目录`org/ytq/mapper`，并将`UserMapper.xml`放入其中，`mvn compile`编译

第二个：`<mapper namespace="org.ytq.mapper.UserMapper">`

第三个

```xml
<select id="selectAll" resultType="org.ytq.pojo.User">
  select * from tb_user;
</select>
```

```java
public interface UserMapper {
    List<User> selectAll();
}
```

第四个

```java
// 3.1 获取对应的userMapper接口的代理对象
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
List<User> users = userMapper.selectAll();
```

## MyBatis核心配置文件

https://mybatis.org/mybatis-3/zh/configuration.html
