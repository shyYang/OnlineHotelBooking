package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.mapper.CustomerMapper;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.mapper.UserMapper;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/12/04.
 */
@Service
@Transactional
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void saveCustomer(UserOfCustomer customer) {
        User user = new User();
        user.setUserId(customer.getUserId());
        user.setPassword(customer.getPassword());
        user.setRole(customer.getRole());
        Customer customer1 = new Customer();
        customer1.setUserId(customer.getUserId());
        customer1.setUsername(customer.getUsername());
        customer1.setGender(customer.getGender());
        customer1.setPhone(customer.getPhone());
        customer1.setAccount(0);
        customerMapper.insert(customer1);
        userMapper.insert(user);
    }
}
