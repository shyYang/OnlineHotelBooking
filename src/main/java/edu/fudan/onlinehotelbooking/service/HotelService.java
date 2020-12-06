package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.HotelType;


/**
 * Created by CodeGenerator on 2020/12/04.
 */
public interface HotelService extends Service<Hotel> {

    //商家注册
    //返回商家id
    int sellerSignUp(HotelType hotel);
}
