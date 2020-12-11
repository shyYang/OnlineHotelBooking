package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.*;
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

    @GetMapping("list_comments_of_hotel")
    public Result findCommentsByHotelId(@RequestParam int hotelId) {
        List<CommentResponse> list = hotelService.findCommentsByHotelId(hotelId);
        return ResultGenerator.genSuccessResult(list);
    }

    //finished by whw
    @GetMapping("/find_all_order")
    public Result findAllOrder(@RequestParam int hotelId){
        Hotel hotel = hotelService.findById(hotelId);
        //System.out.println(hotelId);
        if (hotelId==0){
            return ResultGenerator.genFailResult("hotel_id为null");
        }else if (hotel==null){
            //System.out.println("null");
            return ResultGenerator.genFailResult("不存在此hotel");
        }else {
//            System.out.println(hotelId);
//            return null;
            List<Order> lists = orderService.getOrdersOfHotel(hotelId);
            if (lists.isEmpty()){
                return ResultGenerator.genSuccessResult("此hotel不存在订单",lists);
            }else {
                return ResultGenerator.genSuccessResult(lists);
            }
        }
    }

    @GetMapping("/find_order_information")
    public Result findOrderAndUserInformation(@RequestParam int hotelId,int userId){
        boolean valid = (hotelId==0|userId==0);
        //System.out.println(hotelId);
        if (valid==true){
            return ResultGenerator.genFailResult("有值为null");
        }else {
            List<OrderAndInformation> list = orderService.findInfoByHotelIdAndUserId(hotelId,userId);
            if (list.isEmpty()){
                return ResultGenerator.genFailResult("无订单信息");
            }else return ResultGenerator.genSuccessResult(list);
        }

//        System.out.println(hotelId);
//        System.out.println(userId);

    }

    @GetMapping("/cancel_order")
    public Result cancelOrder(@RequestParam int orderId){
        Order order = orderService.findById(orderId);
        if (order==null){
            return ResultGenerator.genFailResult("没有该order");
        }else {
            if (orderService.cancelOrder(orderId)==1){
                return ResultGenerator.genSuccessResult("取消订单成功");
            }else {
                return ResultGenerator.genFailResult("取消order失败");
            }
        }
    }

    @GetMapping("/finish_order")
    public Result finishOrder(@RequestParam int orderId){
        Order order = orderService.findById(orderId);
        if (order==null){
            return ResultGenerator.genFailResult("没有该order");
        }else {
            if (orderService.finishOrder(orderId)==1){
                Order order1 = orderService.findById(orderId);
                return ResultGenerator.genSuccessResult(order1);
            }else {
                return ResultGenerator.genFailResult("结束order失败");
            }
        }
    }
}
