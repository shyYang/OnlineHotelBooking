package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.Room;

import java.util.List;

public interface RoomMapper extends Mapper<Room> {

    //选择某一房型的空房
    List<Room> selectByTypeIdAndStatus(int typeId, int status);

    List<Room> selectByHotelId(int hotelId);

    void deleteByRoomTypeId(Integer typeId);
}
