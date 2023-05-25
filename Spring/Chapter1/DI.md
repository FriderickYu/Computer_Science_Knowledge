# DI

**本节重点**

> * 构造方法注入
> * setter方法注入
> * 自动装配
> * 集合注入

本节代码详情请见[Spring_04_DI_setter](Spring_04_DI_setter)

## setter注入

这类注入之前的project多次用到过，例如

```java
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    // 提供对应的set方法

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```

之后在配置中直接注入引用类对象

```xml
<bean id = "bookDao" name = "dao" class = "org.ytq.dao.impl.BookDaoImpl" scope="prototype"/>
<bean id = "bookService" name = "service" class = "org.ytq.service.impl.BookServiceImpl">
    <property name="bookDao" ref = "bookDao"/>
</bean>
```

### 注入引用类型

最简单的形式，使用setter把引用的对象放进去，配置的时候直接把bean对象间联系起来就好

```java
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private UserDao userDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
        userDao.save();
    }
}

```

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl"/>
<bean id = "userDao" class = "org.ytq.dao.impl.UserDaoImpl"/>
<bean id = "bookService" class = "org.ytq.service.impl.BookServiceImpl">
<property name="bookDao" ref="bookDao"/>
<property name="userDao" ref="userDao"/>
</bean>
```

### 注入简单数据类型

```java
public class BookDaoImpl implements BookDao {
    private String databaseName;
    private int connectionName;

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setConnectionName(int connectionName) {
        this.connectionName = connectionName;
    }

    @Override
    public void save() {
        System.out.println("book dao save ..." + databaseName + "," + connectionName);
    }
}

```

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl">
    <property name="connectionName" value = "100"/>
    <property name="databaseName" value="mysql"/>
</bean>
```

## 构造器注入

本节代码详情请见[Spring_05_DI_constructor](Spring_05_DI_constructor)

实现类使用构造方法传参

```java
public class BookDaoImpl implements BookDao {
    private String databaseName;
    private int connectionName;

    public BookDaoImpl(String databaseName, int connectionName) {
        this.databaseName = databaseName;
        this.connectionName = connectionName;
    }

    @Override
    public void save() {
        System.out.println("book dao save ..." + databaseName + "," + connectionName);
    }
}

```

标签`constructor-arg`, name属性对应为构造方法中方法形参参数名, ref属性指向IoC容器中的奇对象

```xml
<bean id = "bookDao" class = "org.ytq.dao.impl.BookDaoImpl">
    <!--根据构造方法参数名称注入-->
    <constructor-arg name = "connectionName" value = "100"/>
    <constructor-arg name="databaseName" value = "mysql"/>
</bean>

<bean id = "userDao" class = "org.ytq.dao.impl.UserDaoImpl"/>
<bean id = "bookService" class = "org.ytq.service.impl.BookServiceImpl">
    <constructor-arg name = "userDao" ref = "userDao"/>
    <constructor-arg name = "bookDao" ref = "bookDao"/>
</bean>

```

配置中因为name跟的是形参名，为了防止形参名发生改变，可以使用type进行代替

```xml
<constructor-arg type="int" value="10"/>
<constructor-arg type="java.lang.String" value="mysql"/>
```

## 自动配置

IoC容器根据bean依赖的资源自动查找并注入到bean对象，这就叫自动配置

这个是老版的，原Service实现类中调用了Dao类

```xml
<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>
<bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
    <property name="bookDao" ref="bookDao"/>
</bean>
```

使用自动装配

```xml
<bean class="com.itheima.dao.impl.BookDaoImpl"/>
<!--autowire属性：开启自动装配，通常使用按类型装配-->
<bean id="bookService" class="com.itheima.service.impl.BookServiceImpl" autowire="byType"/>

```

这里的自动装配是按照类型查找的，如果要是想找多个对象，还是需要使用`byName`

## 集合注入

注入数组
```xml
<property name="array">
    <array>
        <value>100</value>
        <value>200</value>
        <value>300</value>
    </array>
</property>
```

注入List

```xml
<property name="list">
    <list>
        <value>itcast</value>
        <value>itheima</value>
        <value>boxuegu</value>
        <value>chuanzhihui</value>
    </list>
</property>
```

注入Set
```xml
<property name="set">
    <set>
        <value>itcast</value>
        <value>itheima</value>
        <value>boxuegu</value>
        <value>boxuegu</value>
    </set>
</property>
```

注入Map类型数据

```xml
<property name="map">
    <map>
        <entry key="country" value="china"/>
        <entry key="province" value="henan"/>
        <entry key="city" value="kaifeng"/>
    </map>
</property>
```

注入Properties类型数据

```xml
<property name="properties">
    <props>
        <prop key="country">china</prop>
        <prop key="province">henan</prop>
        <prop key="city">kaifeng</prop>
    </props>
</property>
```
