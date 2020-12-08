package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.Hotel;

import java.util.List;

public interface HotelMapper extends Mapper<Hotel> {
    List<Hotel> selectOrderByRating();

    List<Hotel> selectByHotelName(String hotelName);
}
