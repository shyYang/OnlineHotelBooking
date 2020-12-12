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

| 函数名         | 函数功能         | 请求方式 | 对应地址              | 所需参数                                          | 返回值（data）                                 |
| -------------- | ---------------- | -------- | --------------------- | ------------------------------------------------- | ---------------------------------------------- |
| login()        | 所有人员的登陆   | POST     | /user/login           | userId/username、password(管理员和商家必须userId) | 成功：返回token和userId,role; 失败返回错误信息 |
| logout()       | 所以人员的登出   | GET      | /user/logout          | null                                              |                                                |
| changePassword | 商家用户修改密码 | POST     | /user/change_password | password                                          | 返回200，success                               |

**HotelController**

| 函数名                | 函数功能             | 请求方式 | 对应地址                      | 所需参数       | 返回值（data）                           |
| --------------------- | -------------------- | -------- | ----------------------------- | -------------- | ---------------------------------------- |
| signUp()              | 商家注册             | POST     | /hotel/sign_up                | *hotel(见下方) | 成功：返回商家管理员userId; 失败返回信息 |
| listAllHotels()       | 列出所有商家         | GET      | /hotel/list_all_hotel         | null           | 返回hotel的list                          |
| listTopHotels()       | 列出top5的商家       | GET      | /hotel/list_top_hotel         | null           | 返回<=5的hotel list                      |
| findHotelById         | 返回特定id的商家     | GET      | /hotel/find_hotel             | hotelId        | 成功返回酒店信息，失败返回信息           |
| findRoomType          | 返回指定id的房间类型 | GET      | /hotel/find_room_type         | hotelId        | 成功返回roomType list，失败返回信息      |
| searchHotels          | 搜索商家             | GET      | /hotel/searche_hotel          | hotelName      | 成功返回hotel list，失败500              |
| findCommentsByHotelId | 列出商家评论         | GET      | /hotel/list_comments_of_hotel | hotelId        | 成功返回** CommentResponse list          |
|findAllOrder()         | 列出商家所有订单     | GET      | /hotel/find_all_order         |  hotelId       |  成功返回list*(order)  失败返回信息   |
|findOrderAndUserInformation| 列出未入住可公开用户信息|  GET   |/hotel/find_order_information | hotelId,userId  |  成功list*(orderAndInformation)失败返回信息|
|cancelOrder()          | 取消订单             |GET      | /hotel/cancel_order           | orderId        |  成功返回成功信息  失败返回失败信息|
|finishOrder()          | 结束订单            |GET       | /hotel/finish_order           |  oderId        |成功返回成功返回list*(order) 失败返回信息|
***hotelName,password,address,phone,photo,introduction**

****CommentResponse: userId, username, rating, content, time, roomId, roomTypeName(比如双人间)**

******

**CustomerController**

| 函数名                | 函数功能     | 请求方式 | 对应地址                          | 所需参数                       | 返回值（data）                              |
| --------------------- | ------------ | -------- | --------------------------------- | ------------------------------ | ------------------------------------------- |
| signUp()              | 用户注册     | POST     | /customer/sign_up                 | username,gender,phone,password | 成功：返回用户userId; 失败返回信息          |
| showUserInformation   | 用户基本信息 | GET      | /customer/user_information        | null                           | 成功返回用户信息                            |
| changeUserInformation | 修改用户信息 | POST     | /customer/change_user_information | username,gender,phone,         | 成功返回新的用户信息                        |
| recharge              | 充值         | GET      | /customer/recharge                | money                          | 成功返回当前账户余额                        |
| orderRoom             | 预订房间     | GET      | /customer/order_room              | typeId                         | 成功返回房间信息，失败返回原因（钱不够...） |
| listAllOrders         | 订单记录     | GET      | /customer/list_all_order          | null                           | 成功返回list*                               |
| comment               | 进行评价     | POST     | /customer/comment                 | content, rating, order_id      | 成功返回200                                 |

***orderId, username, hotelName, phone(hotel),orderTime,roomNumber,commentTime,content,payment,rating**

**RoomTypeController**

| 函数名          | 函数功能        | 请求方式 | 对应地址        | 所需参数                    | 返回值（data）   |
| --------------- | --------------- | -------- | --------------- | --------------------------- | ---------------- |
| add(RoomType roomType)| 添加新房型     | POST  |/room/type/add|"hotelId""price""photo""number""introduction""name",freeNumber| 成功："增加新房型成功" 失败："增加新房型失败"            |
| delete(int typeId) | 删除房型    | POST     | /room/type/delete | typeId         |  成功："删除房型成功" 失败：“删除房型失败” "删除失败，无此类型房型"            |
| update()  | 更新房型信息 | POST | /room/type/update | typeId,hotelId,price,photo,number,introduction,name,freeNumber| 成功：更新新房型成功 失败：错误信息 |
|detail()|查看相关房型信息|POST|/room/type/detail|typeId|成功：list（roomtype）失败：null|
|roomDetail()     | 查看某一类型所有room信息|GET |/room/type/roomDetail | typeId   |成功：list*(room) 失败：null|      
| list()       |  查看本酒店所有房型信息  |  GET  | /room/type/list     | hotelId              |成功list*（roomtype）失败：null message：相关失败信息       |     



**RoomController**

| 函数名          | 函数功能        | 请求方式 | 对应地址        | 所需参数                    | 返回值（data）   |
| --------------- | --------------- | -------- | --------------- | --------------------------- | ---------------- |
| add()| 添加新房间     | POST  |/room/add|typeId,roomNumber| 成功："增加新房间成功" 失败："增加新房间失败"            |
| delete() | 删除房间    | POST     | /room/delete | roomId         |  成功："删除房间成功" 失败：“删除房间失败” "删除失败，无此类型房型"            |
| update()  | 更新房间信息 | POST | /room/update | typeId,roomId,status,roomNumber| 成功：更新房间成功 失败：错误信息 |
|detail()|查看相关房间信息|POST|/room/detail|roomId|成功：list（room）失败：null| 
| list()       |  查看本酒店所有房型信息  |  POST  | /room/list   | hotelId              |成功list*（room）失败：null message：相关失败信息       |         
