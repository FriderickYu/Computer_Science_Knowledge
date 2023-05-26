# 容器

**本节内容**

> * 容器替换
> * 获取Bean的不同方式

之前接触过的容器，基本上就是`ApplicationContext`

先制作实现类和接口类，将类放到bean中，然后通过使用IoC容器`ApplicationContext`，加载配置，并调用其方法`getBean`调用bean对象

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
```

也可以采用`FileSystemXmlApplicationContext`来加载配置，通过绝对路径来加载

```java
ApplicationContext ctx = new FileSystemXmlApplicationContext("C:\Users\Fredrick\Documents\GitHub\Unicom_Foundation\Spring\Chapter1\Spring_01_quickstart\src\main\resources\applicationContext.xml");
```

## Bean的三种获取方式

之前最常用的是通过拿去bean对象，并转类型的方式

```java
BookDao bookDao = (BookDao) ctx.getBean("bookDao");
```

还可以通过反射的反射避免强转类型

```java
BookDao bookDao = ctx.getBean("bookDao", BookDao.class);
```

或者直接传入反射类型

```java
BookDao bookDao = ctx.getBean(BookDao.class);
```

这三种获取方式其实都是一样的，没有本质上的区别

## BenFactory

可以使用BeanFactory来创建IoC容器的具体实现方式

```java
public class AppForBeanFactory{
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory bf = new XmlBeanFactory(resource);
        BookDao bookDao = bf.getBean(BookDao.class);
        bookDao.save();
    }
}
```

```java
public class BookDaoImpl implements BookDao{
    public BookDaoImpl(){
        System.out.println("constructor");
    }
    public void save(){
        System.out.println("book dao save ...");
    }
}
```

如果注释掉主程序里调用save的流程直接运行，会发现`BookDaoImpl`的构造函数并没有被执行，这是因为BeanFactory是延迟加载的，只有在获取bean对象的时候才会去创建

而之前常用的ApplicationContext是立即加载，容器加载的时候就会马上创建bean对象

新建一个`JDBCConfig`类，在里面放需要引入的第三方类和字段

`@Bean`的作用是将该方法的返回值作为Spring管理的bean

```java
public class JDBCConfig {
    // 1. 定义一个方法获得要管理的对象
    @Value("com.mysql.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;
    @Value("root")
    private String userName;
    @Value("root")
    private String password;
    // 2. 添加@Bean，表示当前方法的返回值是一个bean对象
    // @Bean修饰的方法，形参根据类型自动装配
    @Bean("dataSource")
    public DataSource dataSource(BookDao bookDao){
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }
}
```

然后我们需要将JDBC的配置类引入到我们的Spring配置类中

`@Import`手动引入需要加载的配置类

```java
@Configuration
@ComponentScan("org.ytq")

@Import({JDBCConfig.class})
public class SpringConfig {

}
```
