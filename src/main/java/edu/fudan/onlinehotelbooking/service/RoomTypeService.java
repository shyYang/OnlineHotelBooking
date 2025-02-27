package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.core.Service;

import java.util.List;


/**
 * Created by whw on 2020/12/06.
 */
public interface RoomTypeService extends Service<RoomType> {

    int addRoomType(RoomType roomType);

    int deleteRoomType(int typeId);

    List<RoomType> findByHotelId(int hotelId);

    int updateRoomType(RoomType roomType);

    RoomType roomTypeFindById(int typeId);

    List<Room> findByRoomTypeId(int typeId);

    List<RoomType> findAllType(int hotelId);
}
