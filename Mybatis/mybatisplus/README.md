# MyBatis-Plus笔记摘要


| 功能       | 自定义接口                             | MyBatisPlus接口                              |
| ---------- | -------------------------------------- | -------------------------------------------- |
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
