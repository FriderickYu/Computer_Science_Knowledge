# AOP基本概念

AOP，面向切面编程, 是一种编程范式; 核心思想为, 在不改动原程序的情况下增强其功能

例子如下:

有一个叫`BookDaoImpl`的实现类，有四个方法, `save()`, `update()`, `delete()`, `select()`

```java
@Repository
public class BookDaoImpl implements BookDao {
    public void save() {
        //记录程序当前执行执行（开始时间）
        Long startTime = System.currentTimeMillis();
        //业务执行万次
        for (int i = 0;i<10000;i++) {
            System.out.println("book dao save ...");
        }
        //记录程序当前执行时间（结束时间）
        Long endTime = System.currentTimeMillis();
        //计算时间差
        Long totalTime = endTime-startTime;
        //输出信息
        System.out.println("执行万次消耗时间：" + totalTime + "ms");
    }
    public void update(){
        System.out.println("book dao update ...");
    }
    public void delete(){
        System.out.println("book dao delete ...");
    }
    public void select(){
        System.out.println("book dao select ...");
    }
}
```

<img alt="1630143927489.png" height="135" src="assets/result.png" width="600"/>

可以看到, `save()`执行力n次, `delete()`和`update()`也执行了n次;
但`delete()`和`update()`并没有计算时间的方法; 这就是AOP, 即**即无入侵**

AOP实现原理如下图所示

<img alt="1630144353462.png" height="261" src="assets/AOP_base.png" width="600"/>

1. AOP可以在不改变原方法的情况下增强其功能，其实这个*方法*就是连接点，像上面的的`save()`, `update()`, `delete()`, `select()`都是连接点 <font color="red">JoinPoint</font>
2. 在上面的四个连接点中，只有`update()`和`delete()`以前没有附加功能，后来运行时有了附加功能，这个就是切入点 <font color="red">Pointcut</font>
3. `update()`和`delete()`在运行时被添加了相同的附加功能，这个相同的附加功能就叫作通知，因为方法在Java中无法单独存在，需要被封装到类当中，所以一般是需要构建通知类的 <font color="red">Advice</font>
4. 在N个通知和N个切入点中间，会有个叫切面的中间地区; 去决定哪些通知被哪些切入点所使用 <font color="red">Aspect</font>
