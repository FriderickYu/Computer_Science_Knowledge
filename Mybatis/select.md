# 查询

步骤:
1. 编写Mapper接口方法
2. 确定是否有参数
3. 确定返回什么样的结果，结果是什么类型
4. 确定SQL语句是什么样的
5. 执行

## 多条件查询
1. 判断出有哪些参数
2. 连接多参数

## 动态条件查询
SQL语句会随着用户的输入或外部条件的变化而变化，称之为[动态SQL](https://mybatis.org/mybatis-3/zh/dynamic-sql.html)
1. if
2. choose(when, otherwise)
3. trim(where, set)
4. foreach