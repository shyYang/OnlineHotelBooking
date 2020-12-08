package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.mapper.HotelMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.mapper.UserMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
@Service
public class HotelServiceImpl extends AbstractService<Hotel> implements HotelService {
    @Resource
    private HotelMapper hotelMapper;
    @Resource
    private RoomTypeMapper roomtypeMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public int sellerSignUp(HotelType hotel) {
        User user = new User();
        user.setPassword(hotel.getPassword());
        user.setRole(1);
        System.out.println(user);
        userMapper.insertUser(user);
        int id = user.getUserId();
        Hotel insertHotel = new Hotel();
        insertHotel.setUserId(id);
        insertHotel.setHotelName(hotel.getHotelName());
        insertHotel.setPhone(hotel.getPhone());
        insertHotel.setAddress(hotel.getAddress());
        insertHotel.setGuestNumber(0);
        insertHotel.setRating(new BigDecimal("0.0"));
        insertHotel.setIntroduction(hotel.getIntroduction());
        insertHotel.setPhoto(hotel.getPhoto());
        hotelMapper.insert(insertHotel);
        return id;
    }

    @Override
    public List<Hotel> findOrderByRating() {
        return hotelMapper.selectOrderByRating();
    }

    @Override
    public List<Hotel> findByHotelName(String hotelName) {
        return hotelMapper.selectByHotelName(hotelName);
    }

    public int delHotel(int hotelID)
    {
        Hotel hotel=new Hotel();
        hotel.setHotelId(hotelID);
        hotelMapper.delete(hotel);
        RoomType roomType=new RoomType();
        roomType.setHotelId(hotelID);
        roomtypeMapper.delete(roomType);
        return hotelID;
    }

    public int delHotelOfUser(int userID)
    {
        Hotel hotel=new Hotel();
        hotel.setUserId(userID);
        for (Hotel h:hotelMapper.select(hotel)) {
            delHotel(h.getHotelId());
        }
        return userID;
    }

    public List<Hotel> listHotelOfUsers(List<User> users)
    {
        List<Hotel> hotels=new LinkedList<>();
        for(User user:users) {
            Hotel hotel=new Hotel();
            hotel.setUserId(user.getUserId());
            //if multiple
//        for (Hotel h:hotelMapper.select(hotel)) {
//            hotels.add(h);
//        }
//        return hotels;
            hotels.add(hotelMapper.selectOne(hotel));
        }
        return hotels;
    }

}
