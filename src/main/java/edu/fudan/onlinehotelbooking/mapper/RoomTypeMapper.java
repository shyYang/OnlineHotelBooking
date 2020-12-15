package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.RoomType;

import java.util.List;

public interface RoomTypeMapper extends Mapper<RoomType> {

    List<RoomType> selectByHotelId(int hotelId);

    void deleteByHotelId(int hotelID);
}
