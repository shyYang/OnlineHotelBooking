package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.*;
import edu.fudan.onlinehotelbooking.mapper.CommentMapper;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import edu.fudan.onlinehotelbooking.service.RoomService;
import edu.fudan.onlinehotelbooking.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/04.
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private RoomTypeService roomTypeService;
    @Resource
    private RoomService roomService;
    @Resource
    private OrderService orderService;
    @Resource
    private CommentMapper commentMapper;

    @PostMapping("/sign_up")
    public Result signUp(@RequestBody UserOfCustomer customer) {
        System.out.println(customer.getUsername());
        int id = customerService.saveCustomer(customer);
        return ResultGenerator.genSuccessResult(id);
    }

    @GetMapping("/user_information")
    public Result showUserInformation(){
        int userId = 1006;
        Customer customer = customerService.findById(userId);
        return ResultGenerator.genSuccessResult(customer);
    }

    @PostMapping("change_user_information")
    public Result changeUserInformation(@RequestBody UserOfCustomer userOfCustomer) {

        //TODO: userId
        int userId = 1006;
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setUsername(userOfCustomer.getUsername());
        customer.setGender(userOfCustomer.getGender());
        customer.setPhone(userOfCustomer.getPhone());
        customerService.update(customer);
        Customer customer1 = customerService.findById(userId);
        return ResultGenerator.genSuccessResult(customer1);
    }

    @GetMapping("order_room")
    public Result orderRoom(@RequestParam int typeId){
        RoomType roomType = roomTypeService.findById(typeId);
        int freeNumber = roomType.getFreeNumber();
        if (freeNumber == 0) {  //此房型剩余量为 0
            return ResultGenerator.genFailResult("此房型无余量");
        } else {
//            List<Room> list = roomService.findByTypeId(typeId);
            int userId = 1006;
            Room room = customerService.orderRoom(typeId, roomType, userId);
            if (room == null) {
                return ResultGenerator.genFailResult("账户余额不足，请充值");
            } else {
                return ResultGenerator.genSuccessResult(room);
            }

        }
    }

    @GetMapping("recharge")
    public Result recharge(@RequestParam double money) {
        int userId = 1007;
        double account = customerService.recharge(money, userId);
        return ResultGenerator.genSuccessResult(account);
    }

    @GetMapping("list_all_order")
    public Result listAllOrders() {
        int userId = 1006;
        List<OrderAndComment> list = customerService.findOrdersByUserId(userId);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("comment")
    public Result comment(@RequestBody Comment comment) {
        int userId = 1006;
        comment.setUser_id(1006);
        comment.setTime(new Date());
        commentMapper.insert(comment);
        return ResultGenerator.genSuccessResult();
    }


}
