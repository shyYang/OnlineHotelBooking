package edu.fudan.onlinehotelbooking.service;

import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.entity.HotelType;


import java.util.List;

public interface HotelService extends Service<Hotel> {
    int delHotel(int hotelID);
    int delHotelOfUser(int userID);
    List<Hotel> listHotelOfUsers(List<User> users);
    int sellerSignUp(HotelType hotel);

    List<Hotel> findOrderByRating();

    List<Hotel> findByHotelName(String hotelName);
}
