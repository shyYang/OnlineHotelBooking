package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/06.
*/
@RestController
@RequestMapping("/room/type")
public class RoomTypeController {
    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private HotelService hotelService;

    @PostMapping("/add")
    public Result add(@RequestBody RoomType roomType) {
        //roomTypeService.save(roomType);
        
//        System.out.println(roomType.getName());
//        System.out.println(roomType.getPrice());
//        System.out.println(roomType.getHotelId());
//        System.out.println();

//        Hotel hotel=hotelService.findById(roomType.getHotelId());
//        roomType.setHotelId(hotel.getHotelId());
        int resultId = roomTypeService.addRoomType(roomType);
        if (resultId <= 0 ){
            return ResultGenerator.genFailResult("增加新房型失败");
        }else return ResultGenerator.genSuccessResult("增加新房型成功");
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam int typeId) {
        //roomTypeService.deleteById(id);
        RoomType roomType = roomTypeService.findById(typeId);
        //System.out.println(roomType.getPrice());
        if (roomType==null){
            return ResultGenerator.genFailResult("删除失败，无此类型房型");
        }
        //System.out.println(roomTypeService.deleteRoomType(typeId));
        int resultId = roomTypeService.deleteRoomType(typeId);
        if (resultId <= 0){
            return ResultGenerator.genFailResult("删除房型失败");
        }else return ResultGenerator.genSuccessResult("删除房型成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody RoomType roomType) {
        //roomTypeService.update(roomType);
        System.out.println(roomType.getNumber());
        int resultId = roomTypeService.updateRoomType(roomType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam int typeId) {
        //RoomType roomType = roomTypeService.findById(id);
        RoomType roomType = roomTypeService.findById(typeId);
        if (roomType==null){
            return ResultGenerator.genFailResult("null");
        }else return ResultGenerator.genSuccessResult(roomTypeService.roomTypeFindById(typeId));
        //return ResultGenerator.genSuccessResult(roomType);
    }

    @PostMapping("/list")
    public Result list() {
        List<RoomType> list = roomTypeService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
