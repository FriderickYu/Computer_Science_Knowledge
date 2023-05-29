# README

## 工作流程分析

启动服务器初始化过程

* 服务器启动，执行`ServletContainerInitConfig`类，初始化web容器
* 执行`createServletApplicationContext`方法，创建`WebApplicationContext`对象
* 加载`SpringmvcConfig`
* 执行`@ComponentScan`加载对应的bean
* 加载`UserController`，每一个`@RequestMapping`的名称对应一个具体的方法
* 执行`getServletMapping`方法，定义所有请求都经过SpringMVC
