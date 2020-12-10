package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.entity.Order;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/04.
*/
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private OrderService orderService;

    @PostMapping("/sign_up")
    public Result signUp(HotelType hotel) {
        int id = hotelService.sellerSignUp(hotel);
        return ResultGenerator.genSuccessResult(id);
    }


    @GetMapping("/list_all_hotel")
    public Result listAllHotels(){
        List<Hotel> list = hotelService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    @GetMapping("/list_top_hotel")
    public Result listTopHotels(){
        final int NUMBER = 2;
        List<Hotel> list = hotelService.findOrderByRating();
        List<Hotel> result = new ArrayList<>();
        if (list.size() <= NUMBER)
            result = list;
        else {
            for (int i = 0; i < NUMBER; i++) {
                result.add(list.get(i));
            }
        }
        return ResultGenerator.genSuccessResult(result);
    }
    @GetMapping("/find_hotel")
    public Result findHotelById(@RequestParam int hotelId){
        Hotel hotel = hotelService.findById(hotelId);
        return ResultGenerator.genSuccessResult(hotel);
    }

    @GetMapping("/find_room_type")
    public Result findRoomType(@RequestParam int hotelId){
        List<RoomType> list = roomTypeService.findByHotelId(hotelId);
        return ResultGenerator.genSuccessResult(list);
    }

    @GetMapping("search_hotel")
    public Result searchHotels(@RequestParam String hotelName) {
        List<Hotel> list = hotelService.findByHotelName(hotelName);
        return ResultGenerator.genSuccessResult(list);
    }

    @GetMapping("/find_all_order")
    public Result findAllOrder(@RequestParam int hotelId){
        //todo order
        List<Order> lists = orderService.getOrdersOfHotel(hotelId);
        return ResultGenerator.genSuccessResult(lists);
    }
}
