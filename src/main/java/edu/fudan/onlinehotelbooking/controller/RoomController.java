package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Room;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.RoomMapper;
import edu.fudan.onlinehotelbooking.service.RoomService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by whw on 2020/12/07.
*/
@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;
    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private RoomMapper roomMapper;

    @PostMapping("/add")
    public Result add(@RequestBody Room room) {
        boolean valid = (room.getTypeId()==null|room.getRoomNumber()==null);
        //System.out.println(room.getTypeId());
        //RoomType roomType = roomTypeService.roomTypeFindById(room.getTypeId());cause error
        RoomType roomType = roomTypeService.findById(room.getTypeId());
        System.out.println(room.getRoomNumber());
        if (valid==true) {
            return ResultGenerator.genFailResult("更改信息失败，有信息为null");
        }else if (roomType==null) {
            return ResultGenerator.genFailResult("无此类型房型，请输入正确type_id");
        }else {
            int resultId = roomService.addRoom(room);
            System.out.println(resultId);
            if (resultId <= 0 ){
                return ResultGenerator.genFailResult("增加新房间失败");
            }else return ResultGenerator.genSuccessResult("增加新房间成功");
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer roomId) {
        Room room = roomService.findById(roomId);
        if (room==null){
            return ResultGenerator.genFailResult("无此房型间，请输入正确roomId");
        } else {
            int resultId = roomService.deleteRoom(roomId);
            if (resultId <= 0 ){
                return ResultGenerator.genFailResult("删除新房间失败");
            }else return ResultGenerator.genSuccessResult("删除新房间成功");
        }
        //roomService.deleteById(id);
        //return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Room room) {
        Room room1 = roomMapper.selectByPrimaryKey(room.getRoomId());
        RoomType roomType = roomTypeService.findById(room.getTypeId());
        boolean valid = (room.getTypeId()==null|room.getRoomNumber()==null|room.getRoomId()==null|room.getStatus()==null|room.getStatus()>1|room.getStatus()<0);
        if (room1==null){
            return ResultGenerator.genFailResult("room_id不存在，更新房间失败");
        }else if (roomType==null){
            return ResultGenerator.genFailResult("type_id不存在，更新房间失败");
        }else if (valid==true){
            return ResultGenerator.genFailResult("更改信息失败，有信息为null或者不符合要求");
        }else {
            int resultId = roomService.updateRoom(room);
            if (resultId <= 0 ){
                return ResultGenerator.genFailResult("更新房间失败");
            }else return ResultGenerator.genSuccessResult("更新房间成功");
            //return ResultGenerator.genSuccessResult();
        }
//        roomService.update(room);

    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer roomId) {
        Room room = roomService.findById(roomId);
        Room room1 = roomService.roomFindById(roomId);
        if (room==null){
            return ResultGenerator.genFailResult("无此房型间，请输入正确roomId");
        }else {
            return ResultGenerator.genSuccessResult(room1);
        }
        //return ResultGenerator.genSuccessResult(room);
    }

    @GetMapping("/list")
    public Result list(@RequestParam int hotelId) {
        List<Room> list = roomService.findAllRoom(hotelId);
        return ResultGenerator.genSuccessResult(list);
    }
}
