package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Comment;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.mapper.CommentMapper;
import edu.fudan.onlinehotelbooking.mapper.CustomerMapper;
import edu.fudan.onlinehotelbooking.mapper.OrderMapper;
import edu.fudan.onlinehotelbooking.mapper.UserMapper;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

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
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private OrderMapper orderMapper;

    public List<User> listSellers()
    {
        User user=new User();
        user.setRole(1);
        return userMapper.select(user);
    }

    /**
     * @param userID 用户id
     * @return -1，不存在; -2, 不是顾客
     */
    public int delCustomer(int userID)
    {
        User user = userMapper.selectByPrimaryKey(userID);
        if (user == null) {
            return -1;
        }
        if (user.getRole() != 2)
            return -2;

        customerMapper.deleteByPrimaryKey(userID);
        commentMapper.deleteByUserId(userID);
        orderMapper.deleteByUserId(userID);
        userMapper.deleteByPrimaryKey(userID);

        return 0;
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

