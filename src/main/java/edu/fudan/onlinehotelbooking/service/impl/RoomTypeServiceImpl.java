package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/12/06.
 */
@Service
@Transactional
public class RoomTypeServiceImpl extends AbstractService<RoomType> implements RoomTypeService {
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private HotelService hotelService;

    @Override
    public int addRoomType(RoomType roomType) {
        Hotel hotel=hotelService.findById(roomType.getHotelId());
        if (hotel == null){
            return 0;
        }
        RoomType rt = new RoomType();
        rt.setHotelId(roomType.getHotelId());
        rt.setPrice(roomType.getPrice());
        rt.setPhoto(roomType.getPhoto());
        rt.setNumber(roomType.getNumber());
        rt.setIntroduction(roomType.getIntroduction());
        rt.setName(roomType.getName());
        return roomTypeMapper.insert(rt);
    }

    @Override
    public int deleteRoomType(int typeId) {
//        RoomType roomType = new RoomType();
//        roomType.setTypeId(typeId);
//        roomTypeMapper.delete(roomType);

        return roomTypeMapper.deleteByPrimaryKey(typeId);
    }
}
