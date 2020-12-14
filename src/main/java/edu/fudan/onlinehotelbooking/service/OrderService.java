package edu.fudan.onlinehotelbooking.service;

import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.*;

import java.util.List;

public interface OrderService extends Service<Order> {
    List<Order> getOrderOfUser(int userID);
    Order getOrder(int orderID);
    List<OrderAndUserAndInfor> getOrdersOfHotel(int hotelId);
    List<OrderAndUserAndInfor> findInfoByHotelIdAndUserId(int hotelId, int userId);
    List<OrderAndUserAndInfor> getOrdersOfStatus(int hotelId);
    int cancelOrder(int orderId);
    int finishOrder(int orderId);

    List<OrderDetails> getOrderDetailsOfUser(int userID);

    void deleteOrderById(int orderID);
}
