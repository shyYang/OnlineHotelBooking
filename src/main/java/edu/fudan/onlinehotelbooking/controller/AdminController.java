package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.*;

import edu.fudan.onlinehotelbooking.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    @Resource
    private OrderService orderService;
    @Resource
    private HotelService hotelService;
    @Resource
    private CustomerService customerService;

    //列出所有普通用户
    @GetMapping("/users")
    public Result listUsers() {
        Map<String,Object> result=new HashMap<>();
        List<Customer> customerList = customerService.findAll();
        List<User> userList=userService.getUsersOfCustomers(customerList);
        result.put("customers",customerList);
        result.put("users",userList);
        return ResultGenerator.genSuccessResult(result);
    }



//    //列出指定用户的指定订单记录
//    @GetMapping("/orders")
//    public Result listOrderOfID(int orderID)
//    {
//        return ResultGenerator.genSuccessResult(orderService.getOrder(orderID));
//    }

    //列出指定用户的所有订单记录
    @GetMapping("/orders_all")
    public Result listOrdersOfUser(@RequestParam int userID)
    {
        List<OrderDetails> list = orderService.getOrderDetailsOfUser(userID);
        return ResultGenerator.genSuccessResult(list);
    }
//    //列出指定用户的所有评价
//    @GetMapping("/comments")
//    public Result listCommentsOfUser(int userID){
//        return ResultGenerator.genSuccessResult(commentService.getCommentOfUser(userID));
//    }

    //列出指定用户的所有评价
    @GetMapping("/comments_all")
    public Result listCommentsOfUser(@RequestParam int userID)
    {
        List<Comment> list = commentService.getCommentsOfUser(userID);
        return ResultGenerator.genSuccessResult(list);
    }

    //删除指定id的订单
    @GetMapping("/delete_order")
    public Result deleteOrderById(@RequestParam int orderID) {
        orderService.deleteOrderById(orderID);
        return ResultGenerator.genSuccessResult();
    }

    //删除指定id的评价
    @GetMapping("delete_comment")
    public Result deleteCommentById(@RequestParam int commentID){
        commentService.deleteById(commentID);
        return ResultGenerator.genSuccessResult();
    }

    //这里删除指定消费者和商家这里的实现分开了，需要前端根据显示出的用户类型写参数名。或者前端就指定id，后端检查角色也是可以的。
    //删除指定id的消费者
    @GetMapping("/delete_consumer")
    public Result deleteCustomer(int customerID)
    {
        int result = userService.delCustomer(customerID);
        if(result==-1)
        {
            return ResultGenerator.genFailResult("该用户不存在");
        }
        else if(result==-2)
        {
            return ResultGenerator.genFailResult("该用户不是顾客，无法删除");
        }
        return ResultGenerator.genSuccessResult();
    }

//    //删除指定id的商家
//    @GetMapping("/delete_seller")
//    public Result deleteSeller(int sellerID)
//    {
//
//        int result=userService.delSeller(sellerID);
//        if(result==-1)
//        {
//            return ResultGenerator.genFailResult("User not a seller");
//        }
//        else if(result==-2)
//        {
//            return ResultGenerator.genFailResult("User don't exist");
//        }
//        hotelService.delHotelOfUser(sellerID);
//        return ResultGenerator.genSuccessResult(sellerID);
//    }

    @GetMapping("/delete_seller")
    public Result deleteHotel(@RequestParam int hotelID)
    {
        hotelService.delHotel(hotelID);
        return ResultGenerator.genSuccessResult();
    }

    //列出所有商家
    @GetMapping("/sellers")
    public Result listSellers()
    {
        List<Hotel> list = hotelService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }



}
