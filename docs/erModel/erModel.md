1. 创建逻辑模型
在模型开发-逻辑模型中创建逻辑模型，如下：
`获奖记录`表模型
![新建逻辑实体](../../docs/imags/erModel/erLogical.png)
`温度`表模型
![新建逻辑实体](../../docs/imags/erModel/erLogical2.png)

1. 创建物理模型
在模型开发-物理模型中，导入物理模型
选择逻辑实体导入物理模型
![导入物理实体](../../docs/imags/erModel/erPhysical.png)
保存后关闭弹框，导入成功

3. 创建物理表
在表管理中，点击创建表，选择通过物理实体创建
![创建物理表](../../docs/imags/erModel/tableCreate.png)
下一步后可以看到该物理实体生成的ddl，也可以在此基础上修改ddl
![创建物理表](../../docs/imags/erModel/tableCreate2.png)
点击执行，创建成功

