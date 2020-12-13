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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static edu.fudan.onlinehotelbooking.core.ProjectConstant.USER_ID_SESSION;

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
    public Result signUp(HttpServletRequest request, @RequestBody UserOfCustomer customer) {
        String username = customer.getUsername();
        Customer customer1 = customerService.findBy("username", username);
        if (customer1 != null)
            return ResultGenerator.genFailResult("用户名已存在");
        int id = customerService.saveCustomer(customer);
        handleSession(request, id);
        return ResultGenerator.genSuccessResult(id);
    }

    @GetMapping("/user_information")
    public Result showUserInformation(HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
        Customer customer = customerService.findById(userId);
        return ResultGenerator.genSuccessResult(customer);
    }

    @PostMapping("change_user_information")
    public Result changeUserInformation(HttpServletRequest request, @RequestBody UserOfCustomer userOfCustomer) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
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
    public Result orderRoom(HttpServletRequest request, @RequestParam int typeId){
        RoomType roomType = roomTypeService.findById(typeId);
        int freeNumber = roomType.getFreeNumber();
        if (freeNumber == 0) {  //此房型剩余量为 0
            return ResultGenerator.genFailResult("此房型无余量");
        } else {
//            List<Room> list = roomService.findByTypeId(typeId);
            HttpSession session = request.getSession();
            int userId = (int)session.getAttribute(USER_ID_SESSION);
            Room room = customerService.orderRoom(typeId, roomType, userId);
            if (room == null) {
                return ResultGenerator.genFailResult("账户余额不足，请充值");
            } else {
                return ResultGenerator.genSuccessResult(room);
            }

        }
    }

    @GetMapping("recharge")
    public Result recharge(HttpServletRequest request, @RequestParam double money) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
        double account = customerService.recharge(money, userId);
        return ResultGenerator.genSuccessResult(account);
    }

    @GetMapping("list_all_order")
    public Result listAllOrders(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
        List<OrderAndComment> list = customerService.findOrdersByUserId(userId);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("comment")
    public Result comment(HttpServletRequest request, @RequestBody Comment comment) {
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
        comment.setUser_id(userId);
        comment.setTime(new Date());
        commentMapper.insert(comment);
        return ResultGenerator.genSuccessResult();
    }

    private void handleSession(HttpServletRequest request, int id) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID_SESSION, id);
        //        设置失效时间30分钟
        session.setMaxInactiveInterval(1800);
    }

}
