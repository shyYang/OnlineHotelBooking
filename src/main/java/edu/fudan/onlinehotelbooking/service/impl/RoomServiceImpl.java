package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.service.RoomService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/12/07.
 */
@Service
@Transactional
public class RoomServiceImpl extends AbstractService<Room> implements RoomService {
    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Room> findByTypeId(int typeId) {
        return null;
    }
}
