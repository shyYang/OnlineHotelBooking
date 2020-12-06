package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.core.Service;


/**
 * Created by CodeGenerator on 2020/12/06.
 */
public interface RoomTypeService extends Service<RoomType> {

    void addRoomType(RoomType roomType);
}
