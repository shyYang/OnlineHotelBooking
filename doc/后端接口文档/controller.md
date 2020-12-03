# 前后端交互接口文档之后端篇：

**要求：后端controller接口每个函数的功能，输入值以及返回值（Result的data，不包括code和message）**

例如：

**ExampleController**

| 函数名          | 函数功能        | 请求方式 | 对应地址        | 所需参数                    | 返回值（data）   |
| --------------- | --------------- | -------- | --------------- | --------------------------- | ---------------- |
| add(Example)    | 保存example     | POST     | /example/add    | Example实例即id,name,school | null             |
| delete(Integer) | 删除Example     | POST     | /example/delete | 被删除的example的id         | null             |
| list()          | 查询所有example | POST     | /example/list   | null                        | id, name, school |

**注意请求方式规定：表单请求为POST，其余为GET，也可以理解为添加为POST，其余都是GET**



