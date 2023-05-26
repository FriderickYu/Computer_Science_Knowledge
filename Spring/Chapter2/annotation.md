# 注解

**本节重点**

> * 常用的注解
> * 注解开发bean的作用范围和生命周期管理
> * 注解开发与依赖注入
> * 管理第三方bean

本小节代码详情请见[Spring_06_annotation](Spring_06_annotation)

之前利用IoC和DI属性去装配bean对象，每次都需要写配置文件，如果利用注解会极大的加大效率

原来

```xml
<bean id = "dao" class = "org.ytq.dao.impl.BookDaoImpl"/>
<bean id = "service" class = "org.ytq.service.impl.BookServiceImpl"/>
```

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
BookDao bookDao = (BookDao) ctx.getBean("dao");
BookService bookService = (BookService) ctx.getBean("service");
```

通过注解Component，简化流程

标注这个是bean对象dao

```java
@Component("dao")
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save ...");
    }
}
```

在指定文件夹下进行扫描，看有没有bean需要被加载到容器中

```xml
<context:component-scan base-package="org.ytq.dao.impl"/>
```

但这种方式还是得依靠配置文件，Spring还是能够支持纯注解开发模式的，构建类来代替原来的配置文件

```java
@Configuration
@ComponentScan("org.ytq")
public class SpringConfig {

}
```

就相当于

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="org.ytq"/>
</beans>
```

`@Component/@Controller/@Service/@Repository`: 设置该类是Spring管理的bean

`@Configuration`: 设置该类为Spring配置类

`@ComponentScan`: 设置Spring配置类的扫描路径，用于加载使用注解格式定义的bean

## 注解开发bean作用范围与生命周期管理

具体的bean作用范围和生命周期这里概不赘述，请看[IoC章节](../Chapter1/IoC.md)

设置bean的作用范围，可以使用`@Scope`
而生命周期，可以使用`@PostConstruct`和`@PreDestroy`，相当于`init`和`destroy`

## 注解开发与依赖注入

之前的依赖注入，主要是在配置文件中使用`<bean>`

现在可以在配置类中实现依赖注入

```java
@Autowired
private BookDao bookDao;

public void setBookDao(BookDao bookDao) {
    this.bookDao = bookDao;
}

@Override
public void save() {
    System.out.println("book service save ...");
    bookDao.sav
```

注意，这里的`@Autowired`是按照类型注入，如果存在多个实现类的话，需要按照名称进行注入

```java
@Autowired
@Qualifier("dao")
```

刚才示范的是引用类型数据的注入，但如果想注入的是基本数据类型，也可以用类似的方式

```java
@Value("hello world")
private String name;
@Override
public void save() {
    System.out.println("book dao save ..." + name);
}
```

当然，如果是想从外部注入的话，比如读取一个properties文件的数据进行注入

```properties
name = hello world!!!!!
```

然后将`@Value`内部参数进行修改`@Value("${name}")`

当然，也别忘记修改配置类，加入`@PropertySource`标签

```java
@Configuration
@ComponentScan("org.ytq")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {

}
```

## 通过注解管理第三方bean

本小节代码详情请见[Spring_07_dataSource](Spring_07_dataSource)

如果是自己开发的情况下，经常会在实现类上边打`@Component`标签来注明这是一个bean；但如果引入的是第三方类，都在jar包里面，没有办法加注解

比如说，这里引入`druid`数据源

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.16</version>
</dependency>
```
