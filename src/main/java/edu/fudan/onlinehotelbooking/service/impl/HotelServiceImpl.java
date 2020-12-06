package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.mapper.HotelMapper;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.mapper.UserMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Created by CodeGenerator on 2020/12/04.
 */
@Service
@Transactional
public class HotelServiceImpl extends AbstractService<Hotel> implements HotelService {
    @Resource
    private HotelMapper hotelMapper;
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
}
