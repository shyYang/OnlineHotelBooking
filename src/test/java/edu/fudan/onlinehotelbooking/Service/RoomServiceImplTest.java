package edu.fudan.onlinehotelbooking.Service;

import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.service.impl.RoomServiceImpl;
import edu.fudan.onlinehotelbooking.service.impl.RoomTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceImplTest {
    @Resource
    private RoomServiceImpl roomService;
    @Resource
    private RoomMapper roomMapper;

    @Test
    public void testAdd(){
        System.out.println("开始测试");
        Room rm = new Room();
        rm.setRoomNumber(610);
        rm.setTypeId(17);
        rm.setStatus(0);
        assertEquals(1,roomService.addRoom(rm));

        //System.out.println(exampleService.findAll());
    }
    @Test
    public void testDelete(){
        int roomId = 18;
        assertEquals(1,roomService.deleteRoom(roomId));
    }

    @Test
    public void testUpdate(){
        int typeId = 17;
        int roomId = 18;
        int roomNumber = 611;
        int status = 0;
        Room rm = roomMapper.selectByPrimaryKey(roomId);
        rm.setRoomNumber(roomNumber);
        rm.setTypeId(typeId);
        rm.setStatus(status);
        assertEquals(1,roomService.updateRoom(rm));
    }
    @Test
    public void testFindById(){
        Room room = roomService.roomFindById(17);
        //RoomType roomType = roomTypeService.roomTypeFindById(typeId);
        assertNotNull(room);
    }
}
