package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import static edu.fudan.onlinehotelbooking.core.ProjectConstant.*;

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
    public Result login(@RequestBody UserOfCustomer userOfCustomer, HttpServletRequest request) {
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
                    handleSession(request, user.getUserId());
                    return ResultGenerator.genSuccessResult();
                } else {  //密码错误
                    message = "用户不存在或密码错误";
                    return ResultGenerator.genFailResult(message);
                }
            }
        } else{
            String username = userOfCustomer.getUsername();
            System.out.println(username);
            Condition condition = new Condition(Customer.class);
            condition.createCriteria().andEqualTo("username", username);
            List<Customer> customers = customerService.findByCondition(condition);
            if (customers == null || customers.size() == 0) {
                return ResultGenerator.genFailResult("用户不存在或密码错误");
            } else {
                Customer customer = customers.get(0);
                User user = userService.findById(customer.getUserId());
                if (password.equals(user.getPassword()) && role == user.getRole()) {   //登陆成功
                    handleSession(request, user.getUserId());
                    return ResultGenerator.genSuccessResult();
                } else {  //密码错误
                    message = "用户不存在或密码错误";
                    return ResultGenerator.genFailResult(message);
                }
            }
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(USER_ID_SESSION);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("change_password")
    public Result changePassword(HttpServletRequest httpServletRequest,@RequestBody HashMap<String, String> map) {
        //TODO: userId
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        System.out.println(oldPassword);
        System.out.println(newPassword);
        HttpSession session = httpServletRequest.getSession();
        int userId = (int)session.getAttribute(USER_ID_SESSION);
        User user = userService.findById(userId);
        System.out.println(user);
        if (!user.getPassword().equals(oldPassword))
            return ResultGenerator.genFailResult("旧密码不正确");
        user.setPassword(newPassword);
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    private void handleSession(HttpServletRequest request, int id) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID_SESSION, id);
        //        设置失效时间30分钟
        session.setMaxInactiveInterval(1800);
    }


}
