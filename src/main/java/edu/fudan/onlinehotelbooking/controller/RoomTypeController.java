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
        roomTypeService.addRoomType(roomType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        roomTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RoomType roomType) {
        roomTypeService.update(roomType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        RoomType roomType = roomTypeService.findById(id);
        return ResultGenerator.genSuccessResult(roomType);
    }

    @PostMapping("/list")
    public Result list() {
        List<RoomType> list = roomTypeService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
