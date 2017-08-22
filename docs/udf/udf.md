# 编译udf jar

在项目根目录下，执行，如下命令

```
mvn clean package -pl examples -am
```

在{udp_demo_home}/examples/target目录下，会有examples-{version}.jar文件，该jar内包含了UDF class.

# 创建UDF

1. 首先，将examples-{version}.jar文件上传UDP平台。
在“数据开发 -> 资源管理”里上传资源。

2. 创建自定义函数
在“数据开发 -> 函数管理”里，创建自定义函数，选取刚才上传的资源文件

# 使用UDF

创建UDF后，便可以在即席查询和工作流SQL节点内使用
