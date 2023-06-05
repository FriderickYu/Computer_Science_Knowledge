# MyBatis-Plus笔记摘要

[官方文档参考](https://baomidou.com/)

| 功能       | 自定义接口                             | MyBatisPlus接口                              |
| ------------ | ---------------------------------------- | ---------------------------------------------- |
| 新增       | boolean save(T t)                      | int insert(T t)                              |
| 删除       | boolean delete(int id)                 | int deleteById(Serializable id)              |
| 修改       | boolean update(T t)                    | int updateById(T t)                          |
| 根据id查询 | T getById(int id)                      | T selectById(Serializable id)                |
| 查询全部   | List<T> getAll()                       | List<T> selectList()                         |
| 分页查询   | PageInfo<T> getAll(int page, int size) | IPage<T> selectPage(IPage<T> page)           |
| 按条件查询 | List<T> getAll(Condition condition)    | IPage<T> selectPage(Wrapper<T> queryWrapper) |

具体实现:

```java
// 新增
@Test
void testSave(){
    User user = new User();
    user.setName("程序员");
    user.setPassword("123456");
    user.setAge(25);
    user.setTel("15545567555");
    userDao.insert(user);
}
```

```java
// 删除 id为1665627495222026242的用户
@Test
void testDelete(){
    userDao.deleteById(1665627495222026242L);
}
```

```java
// 修改
@Test
void testUpdate(){
    User user = new User();
    user.setId(1L);
    user.setName("666");
    user.setPassword("666");
    userDao.updateById(user);
}
```

```java
// 根据id查询
@Test
void testGetById(){
    User user = userDao.selectById(2L);
    System.out.println(user);
}
```

`lombok`提供了简化实体类中`setter` `getter`等方法的注解，例如
`@Getter`, `@Setter`, `ToString`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@RequiredArgsConstructor`

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

`@Data`注解相当于`@Getter` + `@Setter` + `ToString`, 但不包含构造方法, 下面是具体的例子

```java
// lombok
@Setter
@Getter
@ToString

// @Data 包含所有，但不包含构造方法
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
}
```

## 分页功能

分页其实指的就是`select * from user limit 1,2;`
使用`IPage selectPage(IPage page)`实现

```java
@Test
// 分页查询
void testGetByPage(){
    // select * from user limit 1,2;
    // 分页拦截器
    // 第一个参数指的是当前要设定的页码总数
    // 第二个参数指的是每页最多能显示的信息个数
    IPage page = new Page(2, 3);
    userDao.selectPage(page, null);
    System.out.println("当前页码值: " + page.getCurrent());
    System.out.println("每页显示数: " + page.getSize());
    System.out.println("一共多少页: " + page.getPages());
    System.out.println("一共多少条数据: " + page.getTotal());
    System.out.println("数据: " + page.getRecords());
}
```

单单实现`selectPage`接口没有用，还要进行配置，去拦截分页

```java
@Configuration
public class MpConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 定义拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 添加具体的拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
```

## 条件查询

条件查询需要用到`QueryWrapper`类型的对象，因为像`selectList`这种接口只接收这种类型的参数

第一种，直接使用接口进行条件查询

```java
QueryWrapper queryWrapper = new QueryWrapper();
// 查询18岁以下的人
queryWrapper.lt("age", 18);
List<User> userList = userDao.selectList(queryWrapper);
System.out.println(userList);
```

第二种，使用`lambda`表达式

```java
QueryWrapper<User> queryWrapper = new QueryWrapper();
queryWrapper.lambda().lt(User::getAge, 18);
List<User> userList = userDao.selectList(queryWrapper);
System.out.println(userList);
```

第三种，还是使用`lambda`表达式, 但使用另一种接口

`LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();`

遇到多重条件查询, 可以使用链式法则进行嵌套

```java
lambdaQueryWrapper.lt(User::getAge, 25).gt(User::getAge, 10);
// OR关系,例如小于10岁或者大于30岁
lambdaQueryWrapper.lt(User::getAge, 10).or().gt(User::getAge, 30);
```

### 条件为null

有的时候，如果要对某一个字段的范围进行检索，例如想查询价格在0~100之间的商品，这个的下限是0，怎么进行处理

如果不舍`wrapper.gt`, 只设`wrapper.lt`, 则下限就会是`null`

首先，新设计一个类，并继承原来的`User`类，新类具有字段`upper_age`

```java
@Data
public class UserQuery extends User {
    private Integer upper_age;
}
```

使用`getAge`取出来的值，可以直接使用`if`进行判断，如果不为空才继续处理

```java
if(userQuery.getAge() != null){
    lambdaQueryWrapper.lt(User::getAge, userQuery.getUpper_age());
}
```

其实，`lt`方法中间的参数是提供了逻辑判断的, `public Children lt(boolean condition, R column, Object val)`，所以可以这样

```java
void testQueryByCondition2(){
    UserQuery userQuery = new UserQuery();
    userQuery.setUpper_age(30);
    userQuery.setAge(10);
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.lt(userQuery.getUpper_age() != null, User::getAge, userQuery.getUpper_age());
    lambdaQueryWrapper.gt(userQuery.getAge() != null, User::getAge, userQuery.getAge());
    List<User> userList = userDao.selectList(lambdaQueryWrapper);
    System.out.println(userList);
}
```

### 条件查询 投影

如何实现`select id, name, age from user`

使用`select`方法，在lambda表达式中将想要投影的属性列出即可

```java
lambdaQueryWrapper.select(User::getId, User::getName, User::getAge);
List<User> userList = userDao.selectList(lambdaQueryWrapper);
```

还可以实现`count` `groupBy`等方法

```java
QueryWrapper<User> queryWrapper = new QueryWrapper();
queryWrapper.select("count(*) as nums, age");
queryWrapper.groupBy("age");
List<Map<String, Object>> maps = userDao.selectMaps(queryWrapper);
```

### 条件查询 其他

#### 相等

`select * from user where username="ytq" and password="qty";`等同于

```java
LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
lambdaQueryWrapper.eq(User::getName, "ytq1").eq(User::getPassword, "qty");
User loginUser = userDao.selectOne(lambdaQueryWrapper);
System.out.println(loginUser);
```

由于抽出来的只是一条数据，所以可以使用`selectOne()`方法

#### 范围查询


| 方法      | 关系         |
| ----------- | -------------- |
| `lt`      | 小于         |
| `le`      | 小于等于     |
| `gt`      | 大于         |
| `ge`      | 大于等于     |
| `eq`      | 等于         |
| `between` | 在某个区间内 |

#### 模糊查询

模糊查询最常见的就是`like`, 这里同样也有相同的方法
* `like`: 匹配%字符%
* `likeLeft`: 匹配%字符
* `likeRight`: 匹配字符%

## 