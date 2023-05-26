# AOP入门搭建

**本小节重点**

> * 一个简单的小demo
> * 几个重要的AOP注解
> * AOP工作流程

本小节代码详情请见[Spring_08_AOP](Spring_08_AOP)

## 关于AOP的小demo

首先，需要`aspectjweaver`的依赖

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version>
</dependency>
```

分析现在已有的实现类，里面的两个方法中，`update()`方法需要实现AOP, 规定好切入点

先把通知类搭建好

```java
public class MyAdvice {
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
```

注意, 这个通知类不仅需要是个bean对象, 还要告诉程序这是个切面类, 所以`@Component`和`@Aspect`两个注释都需要

定义切入点，告诉程序，具体哪个切入点要使用通知

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void org.ytq.dao.BookDao.update())")
    private void pt(){}
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}

```

定义切面, 告诉程序通知和切入点的关系, 即谁先谁后, 下面的例子是通知在切入点前面执行
```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void org.ytq.dao.BookDao.update())")
    private void pt(){}
    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
```
不要忘记告诉配置类，开启注解格式AOP功能, 只有加上此注解程序在知道要扫描AOP
```java
@Configuration
@ComponentScan("org.ytq")
@EnableAspectJAutoProxy
public class SpringConfig {
}
```

## AOP注解


| 名称 | @EnableAspectJAutoProxy |
| ---- | ----------------------- |
| 类型 | 配置类注解              |
| 位置 | 配置类定义上方          |
| 作用 | 开启注解格式AOP功能     |


| 名称 | @Aspect               |
| ---- | --------------------- |
| 类型 | 类注解                |
| 位置 | 切面类定义上方        |
| 作用 | 设置当前类为AOP切面类 |


| 名称 | @Pointcut                   |
| ---- | --------------------------- |
| 类型 | 方法注解                    |
| 位置 | 切入点方法定义上方          |
| 作用 | 设置切入点方法              |
| 属性 | value（默认）：切入点表达式 |


| 名称 | @Before                                                      |
| ---- | ------------------------------------------------------------ |
| 类型 | 方法注解                                                     |
| 位置 | 通知方法定义上方                                             |
| 作用 | 设置当前通知方法与切入点之间的绑定关系，当前通知方法在原始切入点方法前运行 |

## AOP工作流程
To be continue ....