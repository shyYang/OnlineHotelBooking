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

**AdminController**

| 函数名          | 函数功能        | 请求方式 | 对应地址        | 所需参数                    | 返回值（data）   |
| --------------- | --------------- | -------- | --------------- | --------------------------- | ---------------- |
| listUsers()   | 列出所有用户     | GET     | /admin/users    | null | 所有user组成的list             |
| listOrderOfID(int orderID) | 列出指定订单记录     | GET     | /admin/orders | 订单的orderID         | 该orderID对应的order            |
| listOrdersOfUser(int userID)          | 列出指定id用户的所有订单记录 | GET     | /admin/orders_all   | userID                        | list<该用户的Order> |
| listComments()    | 列出所有用户的所有评价     | GET     | /admin/comments_all    | userID | list<该用户的Comment>             |
| listCommentsOfUser(int userID) | 列出指定id用户的所有评价     | GET     | /admin/comments | null       | list<所有comment>           |
| deleteCustomer(int customerID)         | 删除指定id的消费者 | GET     | /admin/delete_consumer   | customerID                        | 成功：customerID 失败：FailResult("User not a customer")或者FailResult("User don't exist")  |
| deleteSeller(int sellerID)    | 删除指定id的商家     | GET     | /admin/delete_seller    | sellerID | 成功：sellerID 失败：FailResult("User not a seller")或FailResult("User don't exist")       |
| deleteHotel(int hotelID) | 删除指定id的酒店     | GET     | /admin/delete | hotelID         | hotelID              |
| listSellers()          | 列出所有商家 | GET     | /admin/sellers   | null                        | Map中有两部分，seller字段内容是list<User>为所有商家，hotels字段内容是list<Hotel>，为对应前面商家顺序的hotel list （这里假设每个商家只对应一个hotel）|

**UserController**

| 函数名   | 函数功能       | 请求方式 | 对应地址     | 所需参数                                          | 返回值（data）                                 |
| -------- | -------------- | -------- | ------------ | ------------------------------------------------- | ---------------------------------------------- |
| login()  | 所有人员的登陆 | POST     | /user/login  | userId/username、password(管理员和商家必须userId) | 成功：返回token和userId,role; 失败返回错误信息 |
| logout() | 所以人员的登出 | GET      | /user/logout | null                                              |                                                |

**HotelController**

| 函数名          | 函数功能             | 请求方式 | 对应地址              | 所需参数       | 返回值（data）                           |
| --------------- | -------------------- | -------- | --------------------- | -------------- | ---------------------------------------- |
| signUp()        | 商家注册             | POST     | /hotel/sign_up        | *hotel(见下方) | 成功：返回商家管理员userId; 失败返回信息 |
| listAllHotels() | 列出所有商家         | GET      | /hotel/list_all_hotel | null           | 返回hotel的list                          |
| listTopHotels() | 列出top5的商家       | GET      | /hotel/list_top_hotel | null           | 返回<=5的hotel list                      |
| findHotelById   | 返回特定id的商家     | GET      | /hotel/find_hotel     | hotelId        | 成功返回酒店信息，失败返回信息           |
| findRoomType    | 返回指定id的房间类型 | GET      | /hotel/find_room_type | hotelId        | 成功返回roomType list，失败返回信息      |
|                 |                      |          |                       |                |                                          |

***hotelName,password,address,phone,photo,introduction**

**CustomerController**

| 函数名   | 函数功能 | 请求方式 | 对应地址          | 所需参数                       | 返回值（data）                     |
| -------- | -------- | -------- | ----------------- | ------------------------------ | ---------------------------------- |
| signUp() | 用户注册 | POST     | /customer/sign_up | username,gender,phone,password | 成功：返回用户userId; 失败返回信息 |
|          |          |          |                   |                                |                                    |

**RoomTypeController**


| 函数名          | 函数功能        | 请求方式 | 对应地址        | 所需参数                    | 返回值（data）   |
| --------------- | --------------- | -------- | --------------- | --------------------------- | ---------------- |
| add(RoomType roomType)| 添加新房型     | POST  |/room/type/add|"hotelId""price""photo""number""introduction""name"| 成功："增加新房型成功" 失败："增加新房型失败"            |
| deleteRoomType(int typeId) | 删除房型    | POST     | /room/type/delete | 被删除的房型的typeId         |  成功："删除房型成功" 失败：“删除房型失败” "删除失败，无此类型房型"            |
| list()          | 查询所有example | POST     | /example/list   | null                        | id, name, school |
