package edu.fudan.onlinehotelbooking.Service;

import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.service.ExampleService;
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
public class RoomTypeServiceImplTest {
    @Resource
    private RoomTypeServiceImpl roomTypeService;
    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Test
    public void testAdd(){
        System.out.println("开始测试");
        //int typeId = 17;
        int hotelId = 7;
        double price = 400;
        String photo = "wu";
        int number = 1;
        int freeNumber = 1;
        String introduction="ceshi";
        String name = "ceshi";
        RoomType roomType = new RoomType();
        roomType.setFreeNumber(freeNumber);
        roomType.setNumber(number);
        roomType.setName(name);
        roomType.setPrice(price);
        roomType.setPhoto(photo);
        roomType.setHotelId(hotelId);
        roomType.setIntroduction(introduction);
        assertEquals(1,roomTypeService.addRoomType(roomType));

        //System.out.println(exampleService.findAll());
    }
    @Test
    public void testDelete(){
        int typeId = 16;
        assertEquals(1,roomTypeService.deleteRoomType(typeId));
    }
    @Test
    public void testUpdate(){
        int typeId = 16;
        int hotelId = 7;
        double price = 500;
        String photo = "wu";
        int number = 1;
        int freeNumber = 1;
        String introduction="ceshi";
        String name = "ceshi";
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(typeId);
        roomType.setFreeNumber(freeNumber);
        roomType.setNumber(number);
        roomType.setName(name);
        roomType.setPrice(price);
        roomType.setPhoto(photo);
        roomType.setHotelId(hotelId);
        roomType.setIntroduction(introduction);
        assertEquals(1,roomTypeService.updateRoomType(roomType));
    }
    @Test
    public void testFindById(){
        int typeId = 16;
        RoomType roomType = roomTypeService.roomTypeFindById(typeId);
        assertNotNull(roomType);
    }
}
