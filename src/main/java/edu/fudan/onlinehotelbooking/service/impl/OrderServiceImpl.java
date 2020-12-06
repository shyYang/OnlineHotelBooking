package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Order;
import edu.fudan.onlinehotelbooking.mapper.OrderMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    public List<Order> getOrderOfUser(int userID)
    {
        Order order=new Order();
        order.setUser_id(userID);
        return orderMapper.select(order);
    }

    public Order getOrder(int orderID)
    {
        Order order=new Order();
        order.setOrder_id(orderID);
        return orderMapper.selectOne(order);
    }

}
