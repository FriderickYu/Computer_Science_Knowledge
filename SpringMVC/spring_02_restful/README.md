# RESTful 标准

## REST风格
* 传统风格
  * http://localhost/user/getById?id=1
  * http://localhost/user/saveUser
* RESTful风格
  * http://localhost/user/1
  * http://localhost/user

* 优势
  * 书写简化
  * 隐藏了资源的访问行为，无法通过地址得知对资源进行的是何种操作

按照RESTful风格访问资源时使用*行为动作*区分对资源进行了何种操作
* http://localhost/users 查询全部用户的信息  GET（查询）
* http://localhost/users/1 查询指定用户的信息  GET（查询）
* http://localhost/users 添加用户信息  POST（新增/保存）
* http://localhost/users 修改用户信息  PUT（修改/更新）
* http://localhost/users/1 删除用户信息  DELETE（删除）

根据REST风格对资源访问，就叫做RESTful

## RESTful