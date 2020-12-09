package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.*;
import edu.fudan.onlinehotelbooking.mapper.*;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private OrderMapper orderMapper;


    @Override
    public int saveCustomer(UserOfCustomer customer) {
        User user = new User();
        user.setPassword(customer.getPassword());
        user.setRole(2);
        userMapper.insertUser(user);
        Customer customer1 = new Customer();
        customer1.setUserId(user.getUserId());
        customer1.setUsername(customer.getUsername());
        customer1.setGender(customer.getGender());
        customer1.setPhone(customer.getPhone());
        customer1.setAccount(0);
        customerMapper.insert(customer1);
        return user.getUserId();
    }

    @Override
    public Room orderRoom(int typeId, RoomType roomType, int userId) {
        Condition condition = new Condition(Room.class);
        condition.createCriteria().andEqualTo("typeId", typeId).andEqualTo("status", 0);
        //选择一个，然后把此房间标记为 status = 1
        List<Room> list = roomMapper.selectByCondition(condition);
        Room room = list.get(0);
        room.setStatus(1);
        roomMapper.updateByPrimaryKey(room);
        //然后把房型的剩余量减一
        int freeNumber = roomType.getFreeNumber();
        roomType.setFreeNumber(freeNumber - 1);
        roomTypeMapper.updateByPrimaryKey(roomType);
        //扣款
        Customer customer = customerMapper.selectByPrimaryKey(userId);
        double account = customer.getAccount();
        double price = roomType.getPrice();
        if (account < price) {
            return null;
        } else {
            //扣款
            customer.setAccount(account - price);
            customerMapper.updateByPrimaryKey(customer);
            //生成订单
            Order order = new Order();
            order.setUser_id(userId);
            order.setPayment(price);
            order.setStatus(1);
            order.setRoom_id(room.getRoomId());
            order.setTime(new Date());
            orderMapper.insert(order);
            return room;
        }
    }

    @Override
    public double recharge(double money, int userId) {
        Customer customer = customerMapper.selectByPrimaryKey(userId);
        double account = money + customer.getAccount();
        customer.setAccount(account);
        return account;
    }


}
