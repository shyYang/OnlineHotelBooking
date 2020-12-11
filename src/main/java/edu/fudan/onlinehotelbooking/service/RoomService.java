package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.RoomType;

import java.util.List;

import java.util.List;


/**
 * Created by whw on 2020/12/07.
 */
public interface RoomService extends Service<Room> {
    int addRoom(Room room);

    int deleteRoom(int id);

    int updateRoom(Room room);

    Room roomFindById(int id);

    List<Room> findByTypeId(int typeId);

    List<Room> findAllRoom(int hotelId);
}
