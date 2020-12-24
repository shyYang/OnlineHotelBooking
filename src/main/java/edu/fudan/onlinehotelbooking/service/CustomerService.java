package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.*;
import edu.fudan.onlinehotelbooking.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/12/04.
 */
public interface CustomerService extends Service<Customer> {

    //用户注册
    int saveCustomer(UserOfCustomer customer);

    /**
     * 预订房间
     * @param typeId
     * @param roomType
     * @return 成功返回room，失败（账户余额不足）返回null
     */
    Room orderRoom(int typeId, RoomType roomType, int userId);


    //充值
    double recharge(double money, int userId);

    //查询用户的订单记录
    List<OrderAndComment> findOrdersByUserId(int userId);

    void comment(Comment comment);
}
