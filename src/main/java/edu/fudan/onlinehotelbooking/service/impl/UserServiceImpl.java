package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Comment;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.mapper.CustomerMapper;
import edu.fudan.onlinehotelbooking.mapper.UserMapper;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomerMapper customerMapper;

    public List<User> listSellers()
    {
        User user=new User();
        user.setRole(1);
        return userMapper.select(user);
    }

    public int delCustomer(int userID)
    {
        User user=new User();
        user.setUserId(userID);
        //检查该ID是否为普通用户&是否存在
        if(userMapper.selectOne(user)==null)
        {
            return -2;
        }
        if(userMapper.selectOne(user).getRole()!=2)
        {
            return -1;
        }
        userMapper.delete(user);
        Customer customer=new Customer();
        customer.setUserId(userID);
        customerMapper.delete(customer);
        return userID;
    }

    public int delSeller(int userID)
    {
        User user =new User();
        user.setUserId(userID);
        if(userMapper.selectOne(user)==null)
        {
            return -2;
        }
        if(userMapper.selectOne(user).getRole()!=1)
        {
            return -1;
        }
        userMapper.delete(user);
        return userID;
    }

    public List<User> getUsersOfCustomers(List<Customer> customerList)
    {
        List<User> users=new LinkedList<>();
        for (Customer customer:customerList)
        {
            User user=new User();
            user.setUserId(customer.getUserId());
            users.add(userMapper.selectOne(user));
        }
        return users;
    }

}
