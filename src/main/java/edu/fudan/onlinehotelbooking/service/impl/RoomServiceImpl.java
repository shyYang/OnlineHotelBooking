package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.service.RoomService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by whw on 2020/12/07.
 */
@Service
@Transactional
public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<Room> findByTypeId(int typeId) {
        return null;
    }
    @Override
    public int addRoom(Room room) {
        Room rm = new Room();
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(room.getTypeId());
        rm.setRoomNumber(room.getRoomNumber());
        rm.setTypeId(room.getTypeId());
        rm.setStatus(0);
        int freeNumber = roomType.getFreeNumber();
        int number = roomType.getNumber();
        roomType.setNumber(number+1);
        roomType.setFreeNumber(freeNumber+1);
        roomTypeMapper.updateByPrimaryKey(roomType);
        return roomMapper.insert(rm);
    }

    @Override
    public int deleteRoom(int id) {
        return roomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRoom(Room room) {
        return roomMapper.updateByPrimaryKey(room);
    }

    @Override
    public Room roomFindById(int roomId) {
        Room room = roomMapper.selectByPrimaryKey(roomId);
        return room;
    }

    @Override
    public List<Room> findAllRoom(int hotelId) {
        return roomMapper.selectByHotelId(hotelId);
    }
}
