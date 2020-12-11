package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Order;
import edu.fudan.onlinehotelbooking.entity.OrderAndInformation;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.OrderMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
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
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;

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

    //finished by whw
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

    @Override
    public int cancelOrder(int orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int finishOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setStatus(3);
        int roomId = order.getRoom_id();
        Room room = roomMapper.selectByPrimaryKey(roomId);
        room.setStatus(0);
        roomMapper.updateByPrimaryKey(room);
        int typeId = room.getTypeId();
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(typeId);
        int freeNumber = roomType.getFreeNumber();
        roomType.setFreeNumber(freeNumber+1);
        roomTypeMapper.updateByPrimaryKey(roomType);
        return orderMapper.updateByPrimaryKey(order);
    }
}
