package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.Order;
import edu.fudan.onlinehotelbooking.entity.OrderAndInformation;
import edu.fudan.onlinehotelbooking.entity.OrderAndUserAndInfor;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    List<Order> selectByUserId(int userId);
    List<Order> selectByHotelId(int hotelId);
    List<OrderAndInformation> selectByHotelIdAndUserId(int hotelId,int userId);
}
