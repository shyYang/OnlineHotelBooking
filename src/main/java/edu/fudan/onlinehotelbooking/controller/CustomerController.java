package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/04.
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @PostMapping("/sign_up")
    public Result signUp(@RequestBody UserOfCustomer customer) {
        System.out.println(customer.getUsername());
        int id = customerService.saveCustomer(customer);
        return ResultGenerator.genSuccessResult(id);
    }

    @GetMapping("/user_information")
    public Result showUserInformation(){
        int userId = 0;
        Customer customer = customerService.findById(userId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("change_user_information")
    public Result changeUserInformation(@RequestBody UserOfCustomer userOfCustomer) {

        //TODO: userId
        int userId = 0;
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setUsername(userOfCustomer.getUsername());
        customer.setGender(userOfCustomer.getGender());
        customer.setPhone(userOfCustomer.getPhone());
        customerService.update(customer);
        return ResultGenerator.genSuccessResult();
    }




}
