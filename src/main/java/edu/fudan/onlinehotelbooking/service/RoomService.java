package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/12/07.
 */
public interface RoomService extends Service<Room> {

    List<Room> findByTypeId(int typeId);
}
