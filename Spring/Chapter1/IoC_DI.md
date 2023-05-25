# IoC & DI简单演示

**本节目标**

> * IoC思想的具体实现
> * DI思想的具体实现

## IoC思路整理

1. Spring是用IoC容器来管理bean对象的，那么管什么？
   1. 主要是管理项目中使用的类对象，比如Service和Dao
2. 如何将要管理的类对象告知IoC容器？
   1. 使用配置文件
3. 如何获取IoC容器？
   1. Spring框架提供的接口
4. 拿到IoC容器后，如果从容器中拿到bean对象？
   1. 调用接口中的对应方法

### 具体实现

1. 在pom.xml文件中导入spring坐标`spring-context`

```xml
<dependency>
   <groupId>org.springframework</groupId>
   <artifactId>spring-context</artifactId>
   <version>5.2.10.RELEASE</version>
</dependency>
```

2. 配置bean对象，并将要管理的类对象放入到IoC容器中

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl" />
```

3. 获取IoC容器

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
```

4. 拿到容器中的bean对象，用Spring框架提供的接口拿

```java
BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");
```

## DI思路整理

1. Service中需要的Dao对象如何进入到Service中？
   1. 在Service提供注入方法，让Spring的IoC容器可以通过该方法传入bean对象
2. Service和Dao关系如何描述？
   1. 使用配置文件

### 具体实现

1. 在业务层中删掉new出来的对象 `private BookDao bookDao;`
2. 为业务层提供注入方法，比如setter方法

```java
public void setBookDao(BookDao bookDao) {
     this.bookDao = bookDao;
 }
```

3. 修改配置文件，描述bean对象之间的关系

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl" />
<bean id = "bookService" class = "org.ytq.service.impl.BookServiceImpl">
  <property name="bookDao" ref = "bookDao"/>
</bean>
```

* `name="bookDao"`中的`bookDao`是让Spring的IoC容器在获取名称后，将首字母大写，前面加上`set`找对应的`setBookDao()`方法进行对象注入
* `ref="bookDao"`中的`bookDao`是让Spring在IoC容器中找到`id=bookDao`的bean对象

<img alt="1629736314989.png" height="211" src="assets/DI_dependency.png" width="600"/>
