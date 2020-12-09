package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.mapper.RoomTypeMapper;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
* Created by whw on 2020/12/06.
*/
@RestController
@RequestMapping("/room/type")
public class RoomTypeController {
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private HotelService hotelService;

    @PostMapping("/add")
    public Result add(@RequestBody RoomType roomType) {
        //roomTypeService.save(roomType);
//        System.out.println(roomType.getName());
//        System.out.println(roomType.getName()=="");
        boolean valid = (roomType.getNumber()==null|roomType.getFreeNumber()==null|roomType.getPrice()==0.0|roomType.getPhoto()==""|roomType.getIntroduction()==""|roomType.getName()==""|roomType.getHotelId()==0|roomType.getTypeId()==null);//        DecimalFormat price = new DecimalFormat("#.00");

//        String str = price.format(roomType.getPrice());
        //System.out.println(valid);
//        double roomTypePrice = Double.parseDouble(str);
////        System.out.println(roomType.getName());
//        System.out.println(roomTypePrice);
//        roomType.setPrice(roomTypePrice);
        //return ResultGenerator.genSuccessResult();
//        System.out.println(roomType.getHotelId());
//        System.out.println();

        Hotel hotel=hotelService.findById(roomType.getHotelId());
        if (hotel==null){
            return ResultGenerator.genFailResult("hotel_id不存在，增加新房型失败");
        }else if (valid==true){
            return ResultGenerator.genFailResult("更改信息失败，有信息为null");
        }else if (roomType.getNumber()<roomType.getFreeNumber()){
            return ResultGenerator.genFailResult("更改信息失败，room-type的数量不能小于该种类空闲数量");
        }else {
            int resultId = roomTypeService.addRoomType(roomType);
            if (resultId <= 0 ){
                return ResultGenerator.genFailResult("增加新房型失败");
            }else return ResultGenerator.genSuccessResult("增加新房型成功");
        }
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
        RoomType rt = roomTypeMapper.selectByPrimaryKey(roomType.getTypeId());
        //System.out.println(roomType.getHotelId());
        boolean valid = (roomType.getNumber()==null|roomType.getFreeNumber()==null|roomType.getPrice()==0.0|roomType.getPhoto()==""|roomType.getIntroduction()==""|roomType.getName()==""|roomType.getHotelId()==0|roomType.getTypeId()==null);
        Hotel hotel=hotelService.findById(roomType.getHotelId());
        if (hotel==null){
            return ResultGenerator.genFailResult("hotel_id不存在，增加新房型失败");
        }else if (rt==null){
            return ResultGenerator.genFailResult("更改失败，无此类型房型");
        }else if (valid==true){
            return ResultGenerator.genFailResult("更改信息失败，有信息为null");
        }else{
            int roomTypeNumber = rt.getNumber();//数据库中房间数
            int roomTypeFreeNumber = rt.getFreeNumber();//数据库中空闲数
            int number = roomTypeNumber - roomTypeFreeNumber;//已入住房间数
            if (number>roomType.getNumber()){
                return ResultGenerator.genFailResult("更改信息失败，room-type的数量不能小于已入住该种类数量");
            }else if (roomType.getNumber()<roomType.getFreeNumber()){
                return ResultGenerator.genFailResult("更改信息失败，room-type的数量不能小于该种类空闲数量");
            } else {
                int resultId = roomTypeService.updateRoomType(roomType);
                if (resultId <= 0 ){
                    return ResultGenerator.genFailResult("更新新房型失败");
                }else return ResultGenerator.genSuccessResult("更新新房型成功");
//                return ResultGenerator.genSuccessResult(roomTypeService.updateRoomType(roomType));
            }
        }
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
        List<RoomType> list = roomTypeService.findAllType();
        return ResultGenerator.genSuccessResult(list);
    }
}
