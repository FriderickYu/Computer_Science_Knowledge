# 生命周期与插件

正常情况下的生命周期: compile -> test -> package -> install

在测试过程中，同样包含了编译的过程，所以: compile -> test-compile -> test -> package -> install

`Maven`对项目构建的生命周期划分为3套:

1. `clean`: 清理工作，把之前编译、测试，日志等所有文件全部删除干净
   1. `pre-clean`: 执行一些需要在`clean`之前完成的工作
   2. `clean`: 移除所有上一次构建生成的文件
   3. `post-clean`: 执行一些需要在`clean`之后立刻完成的工作
2. `default`: 核心工作，例如编译、测试、打包，部署等
   1. 此处略去很多
3. `site`: 产生报告、发布站点
   1. 此处略去很多
