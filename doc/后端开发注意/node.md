##  后端开发注意事项

1. 注意完善controller接口文档

2. 测试controller接口可以使用[postman][Postman]

3. src/test/java/.../CodeGenerator.java可以根据在main函数中输入的表名生成对应的service，controller等

4. 由于我整合了通用Mapper，所以单表的操作均不用再Mapper里实现

5. 本次项目后端数据库部分 使用了mybatis，设计的文件为java/../mapper目录以及resource/mapper目录，非常简单实用，请自行查阅资料和学习

6. 注释不强求

7. 数据部分自行创造

8. .sql无法导入可能是因为外键约束，如果是实用phpmyadmin导入的话取消勾选外键约束即可，其他我也不清楚 

   [Postman]: https://www.postman.com/downloads/

   

