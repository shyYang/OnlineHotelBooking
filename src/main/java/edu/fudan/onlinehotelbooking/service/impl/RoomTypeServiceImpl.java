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

}
