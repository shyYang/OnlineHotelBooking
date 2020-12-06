package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.entity.RoomType;
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

    @Override
    public void addRoomType(RoomType roomType) {
        RoomType rt = new RoomType();
        rt.setHotelId(roomType.getHotelId());
        rt.setPrice(roomType.getPrice());
        rt.setPhoto(roomType.getPhoto());
        rt.setNumber(roomType.getNumber());
        rt.setIntroduction(roomType.getIntroduction());
        rt.setName(roomType.getName());
        roomTypeMapper.insert(rt);
    }
}
