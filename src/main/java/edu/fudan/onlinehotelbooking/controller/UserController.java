package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/04.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private CustomerService customerService;

    @PostMapping("/login")
    public Result login(@RequestBody UserOfCustomer userOfCustomer) {
        System.out.println(userOfCustomer.getUsername());
        System.out.println(userOfCustomer.getRole());
        int role = userOfCustomer.getRole();
        String message = "";
        String password = userOfCustomer.getPassword();
        if (role == 0 || role == 1) { //管理员
            int id = userOfCustomer.getUserId();
            User user = userService.findById(id);

            if (user == null) { //用户id不存在
                message = "用户不存在或密码错误";
                return ResultGenerator.genFailResult(message);
            } else {
                if (password.equals(user.getPassword())  && role == user.getRole()) { //登陆成功
                    message = "登陆成功";
                    //todo: Token
                    user.setPassword("*********");
                    return ResultGenerator.genSuccessResult("token", user);
                } else {  //密码错误
                    message = "用户不存在或密码错误";
                    return ResultGenerator.genFailResult(message);
                }
            }
        } else{
            int id = userOfCustomer.getUserId()==null ? 0 : userOfCustomer.getUserId();
            String username = userOfCustomer.getUsername();
            if (id != 0) {
                User user = userService.findById(id);
                if (user == null) {
                    message = "用户不存在或密码错误";
                    return ResultGenerator.genFailResult(message);
                } else {
                    if (password.equals(user.getPassword()) && role == user.getRole()) {
                        message = "登陆成功";
                        //todo: Token
                        user.setPassword("*********");
                        return ResultGenerator.genSuccessResult("token", user);
                    } else {  //密码错误
                        message = "用户不存在或密码错误";
                        return ResultGenerator.genFailResult(message);
                    }
                }
            } else {
                Customer customer = customerService.findBy("username", username);
                if (customer == null ) {
                    return ResultGenerator.genFailResult("用户不存在或密码错误");
                } else {
                    User user = userService.findById(customer.getUserId());
                    if (password.equals(user.getPassword()) && role == user.getRole()) {   //登陆成功
                        message = "登陆成功";
                        //todo: Token
                        user.setPassword("*********");
                        return ResultGenerator.genSuccessResult("token", user);
                    } else {  //密码错误
                        message = "用户不存在或密码错误";
                        return ResultGenerator.genFailResult(message);
                    }
                }
            }
        }
    }


}
