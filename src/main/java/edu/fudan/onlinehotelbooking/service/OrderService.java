package edu.fudan.onlinehotelbooking.service;

import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.Comment;
import edu.fudan.onlinehotelbooking.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order> {
    List<Order> getOrderOfUser(int userID);
    Order getOrder(int orderID);
    List<Order> getOrdersOfHotel(int hotelId);

}
