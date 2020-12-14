package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.*;
import edu.fudan.onlinehotelbooking.mapper.CommentMapper;
import edu.fudan.onlinehotelbooking.mapper.OrderMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private CommentMapper commentMapper;
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
    public List<OrderAndUserAndInfor> getOrdersOfHotel(int hotelId) {
        List<OrderAndInformation> list = orderMapper.selectAllByHotelId(hotelId);
        List<OrderAndUserAndInfor> newList = new ArrayList<>();
        for (OrderAndInformation info: list){
            Room room = roomMapper.selectByPrimaryKey(info.getRoomId());
            OrderAndUserAndInfor orderAndUserAndInfor = new OrderAndUserAndInfor();
            orderAndUserAndInfor.setOrderId(info.getOrderId());
            orderAndUserAndInfor.setRoomNumber(room.getRoomNumber());
            orderAndUserAndInfor.setStatus(info.getStatus());
            orderAndUserAndInfor.setOrderTime(info.getTime());
            orderAndUserAndInfor.setUsername(info.getUsername());
            orderAndUserAndInfor.setGender(info.getGender());
            orderAndUserAndInfor.setUserPhone(info.getPhone());
            orderAndUserAndInfor.setPayment(info.getPayment());
            Comment comment = commentMapper.selectByOrderId(info.getOrderId());
            if (comment==null){
                orderAndUserAndInfor.setCommentTime(null);
                orderAndUserAndInfor.setContent(null);
                orderAndUserAndInfor.setRating(0.0);
            }else {
                orderAndUserAndInfor.setCommentTime(comment.getTime());
                orderAndUserAndInfor.setContent(comment.getContent());
                orderAndUserAndInfor.setRating(comment.getRating());
            }
            newList.add(orderAndUserAndInfor);
        }
        return newList;

//        List<Order> orders = orderMapper.selectByHotelId(hotelId);
//        return orders;
    }

    @Override
    public List<OrderAndUserAndInfor> findInfoByHotelIdAndUserId(int hotelId, int userId) {
        List<OrderAndInformation> list = orderMapper.selectByHotelIdAndUserId(hotelId,userId);
        List<OrderAndUserAndInfor> newList = new ArrayList<>();
        for (OrderAndInformation info: list){
            Room room = roomMapper.selectByPrimaryKey(info.getRoomId());
            OrderAndUserAndInfor orderAndUserAndInfor = new OrderAndUserAndInfor();
            orderAndUserAndInfor.setOrderId(info.getOrderId());
            orderAndUserAndInfor.setRoomNumber(room.getRoomNumber());
            orderAndUserAndInfor.setStatus(info.getStatus());
            orderAndUserAndInfor.setOrderTime(info.getTime());
            orderAndUserAndInfor.setUsername(info.getUsername());
            orderAndUserAndInfor.setGender(info.getGender());
            orderAndUserAndInfor.setUserPhone(info.getPhone());
            orderAndUserAndInfor.setPayment(info.getPayment());
            Comment comment = commentMapper.selectByOrderId(info.getOrderId());
            if (comment==null){
                orderAndUserAndInfor.setCommentTime(null);
                orderAndUserAndInfor.setContent(null);
                orderAndUserAndInfor.setRating(0.0);
            }else {
                orderAndUserAndInfor.setCommentTime(comment.getTime());
                orderAndUserAndInfor.setContent(comment.getContent());
                orderAndUserAndInfor.setRating(comment.getRating());
            }
            newList.add(orderAndUserAndInfor);
        }
        return newList;
    }

    @Override
    public List<OrderAndUserAndInfor> getOrdersOfStatus(int hotelId) {
        List<OrderAndInformation> list = orderMapper.selectAllByHotelIdAndStatus(hotelId);
        List<OrderAndUserAndInfor> newList = new ArrayList<>();
        for (OrderAndInformation info: list){
            Room room = roomMapper.selectByPrimaryKey(info.getRoomId());
            OrderAndUserAndInfor orderAndUserAndInfor = new OrderAndUserAndInfor();
            orderAndUserAndInfor.setOrderId(info.getOrderId());
            orderAndUserAndInfor.setRoomNumber(room.getRoomNumber());
            orderAndUserAndInfor.setStatus(info.getStatus());
            orderAndUserAndInfor.setOrderTime(info.getTime());
            orderAndUserAndInfor.setUsername(info.getUsername());
            orderAndUserAndInfor.setGender(info.getGender());
            orderAndUserAndInfor.setUserPhone(info.getPhone());
            orderAndUserAndInfor.setPayment(info.getPayment());
            Comment comment = commentMapper.selectByOrderId(info.getOrderId());
            if (comment==null){
                orderAndUserAndInfor.setCommentTime(null);
                orderAndUserAndInfor.setContent(null);
                orderAndUserAndInfor.setRating(0.0);
            }else {
                orderAndUserAndInfor.setCommentTime(comment.getTime());
                orderAndUserAndInfor.setContent(comment.getContent());
                orderAndUserAndInfor.setRating(comment.getRating());
            }
            newList.add(orderAndUserAndInfor);
        }
        return newList;
    }

    @Override
    public int cancelOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setStatus(2);
        int roomId = order.getRoom_id();
        Room room = roomMapper.selectByPrimaryKey(roomId);
        room.setStatus(0);
        roomMapper.updateByPrimaryKey(room);
        int type_id = room.getTypeId();
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(type_id);
        int freeNumber = roomType.getFreeNumber();
        roomType.setFreeNumber(freeNumber+1);
        roomTypeMapper.updateByPrimaryKey(roomType);
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public int finishOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setStatus(1);
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

    //根据用户id，获取该用户订单详细信息
    @Override
    public List<OrderDetails> getOrderDetailsOfUser(int userID) {
        List<OrderDetails> list = orderMapper.selectOrderDetailsList(userID);
        return list;
    }

    //删除订单，需要先删除评论再删除订单
    @Override
    public void deleteOrderById(int orderID) {
        commentMapper.deleteByOrderId(orderID);
        orderMapper.deleteByPrimaryKey(orderID);
    }
}
