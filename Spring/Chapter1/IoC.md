# IoC基础知识

**本节重点**

> * bean的属性
> * 实例化bean的方法
> * bean的生命周期

## bean的基础配置

本小节代码详情请见[Spring_01_quickstart](Spring_01_quickstart)

### bean的name属性

在`applicationContext.xml`文件中为bean对象设置别名，这样Spring提供的方法`getBean`就可以使用别名作为参数了

```xml
<bean id = "bookDao" name = "dao" class = "org.ytq.dao.impl.BookDaoImpl" />
```

### bean的scope配置

正常情况下，IoC容器创造出来的bean对象是单例的，例如业务层和数据层，生成一个bookDao对象即可，想要调用不同的方法那就改变指向就好，不需要生成多个对象

但如果要生成多个对象，也是可以的

```xml
<bean id = "bookDao" name = "dao" class = "org.ytq.dao.impl.BookDaoImpl" scope="prototype"/>
```

## bean的实例化与生命周期

本小节代码详情请见[Spring_02_bean_instance](Spring_02_bean_instance)

对象是交给IoC容器去创建的，那么到底是如何创建的呢？

通常来讲，实例化bean对象有三种方式，`构造方法`, `静态工厂`和`实例工厂`

### 构造方法实例化bean对象

bean本质上就是对象，按照以往的管理通过new来声明对象，本质上就是使用构造方法，那创建bean也可以使用构造方法

1. 在实现类`BookDaoImpl`中创建非默认的构造方法

```java
public BookDaoImpl() {
    System.out.println("book dao constructor is running ....");
}
```

2. 正常的将类装配到IoC容器中

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl"/>
```

注意，虽然可以用构造方法，但如果提供的是<font color="red">带参数的构造方法</font>，是不可以的，因为Spring底层使用的是类的无参数构造方法

### 构造方法实例化bean对象

1. 创建一个工厂类OrderDaoFactory，并提供一个静态方法

```java
public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        return new OrderDaoImpl();
    }
}
```

2. 因为是静态方法，所以直接可以通过类名进行调用来生成对象

```java
public class AppForInstanceOrder {
    public static void main(String[] args) {
        OrderDao orderDao = OrderDaoFactory.getOrderDao();
        orderDao.save();
    }
}
```

这个方法可以，但是没有领用Spring的特性，应该考虑如何让Spring去管理

### 静态工厂实例化bean对象

1. 在配置文件中，指定工厂类的路径，和要调用的工厂类方法，并将bean对象放到IoC容器中

```xml
<bean id = "orderDao" class = "org.ytq.factory.OrderDaoFactory" factory-method="getOrderDao"/>
```

class: 工厂类的类全名与路径

factory-method: 具体工厂类中创建对象的方法名

2. 在主类中调用`getBean`方法

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
OrderDao orderDao = (OrderDao) ctx.getBean("orderDao");
orderDao.save();
```

这里最大的问题就是，静态方法本质上还是new一个对象，没有本质上的改进，确实可以在静态类中加入一些其他操作

### 实例化工厂实例化bean对象

1. 构造一个工厂类，照样构造一个生成对象的方法，但这个方法不是静态方法

```java
public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
```

2. 在配置文件中加载工厂类

```xml
<bean id = "userFactory" class = "org.ytq.factory.UserDaoFactory"/>
<bean id = "userDao" factory-bean="userFactory" factory-method="getUserDao"/>
```

第一行配置，是将工厂类`UserFactory`放入了IoC容器当中

第二行配置，调用工厂类的方法来构建bean，其实`factory-bean`工厂的bean对象，`factory-method`工厂bean对象具体的创建对象的方法名

3. 按照老方法，在主类中引入配置、调用bean方法

#### 实例化工厂的改进

1. 创建一个`UserDaoFactoryBean`的类，实现`FactoryBean`接口，并重写方法

```java
public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}

```

这里用到了反射，详情请见[反射专题](..%2F..%2FOthers%2FReflection.md)

2. 配置bean

```xml
<bean id = "userDao" class = "org.ytq.factory.UserDaoFactoryBean"/>
```

### bean的生命周期

本小节代码详情请见[Spring_03_bean_lifecycle](Spring_03_bean_lifecycle)

* bean创建之后，想要添加内容，比如实例化需要用到的资源
* bean销毁之前，想要添加内容，比如用来释放用掉的资源

1. 添加初始化和销毁方法

```java
public void init(){
    System.out.println("init ...");
}
public void destroy(){
    System.out.println("destroy ...");
}
```

2. 配置生命周期

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl" init-method="init" destroy-method="destroy"/>
```

3. 初始化的方法会被正常执行，但销毁并不会被执行
   1. 原因在于Spring的IoC容器还是在JVM中运行的
   2. 运行完main方法后，JVM启动，加载配置并生成IoC容器，从容器中拿到bean对象，调用相应的方法执行
   3. main方法执行完后，JVM退出，这个时候bean没来得及销毁整个程序就结束了
   4. 所以destroy方法根本就没来得及调用

如果想要在JVM关闭之前调用destroy方法，应该按照如下步骤去做

a. 因为`ApplicationContext`没有close方法，所以换成`ClassPathXmlApplicationContext`

```java
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
```

b. 直接调用close方法

```java
ctx.close()
```

这种close方法比较暴力，而且是只有调用的时候才会关闭，可以使用如下的方法进行优化

设置`registerShutdownHook()`回调函数，在JVM退出之前回调此函数来关闭容器

```java
ctx.registerShutdownHook();
```
