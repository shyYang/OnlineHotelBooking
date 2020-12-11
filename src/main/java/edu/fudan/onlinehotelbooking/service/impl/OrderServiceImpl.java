package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Order;
import edu.fudan.onlinehotelbooking.entity.OrderAndInformation;
import edu.fudan.onlinehotelbooking.mapper.OrderMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
@Service
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

    @Override
    public List<Order> getOrdersOfHotel(int hotelId) {
        //orderMapper.s
//        Condition condition = new Condition(Order.class);
//        condition.createCriteria().andEqualTo("hotel_Id",hotelId);
//        List<Order> orders = orderMapper.selectByCondition(condition);
        List<Order> orders = orderMapper.selectByHotelId(hotelId);
        return orders;
    }

    @Override
    public List<OrderAndInformation> findInfoByHotelIdAndUserId(int hotelId, int userId) {
        return orderMapper.selectByHotelIdAndUserId(hotelId,userId);
    }
}
