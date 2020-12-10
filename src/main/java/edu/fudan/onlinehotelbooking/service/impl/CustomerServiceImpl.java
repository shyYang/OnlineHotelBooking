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
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private HotelMapper hotelMapper;


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
        //选择一个，然后把此房间标记为 status = 1
        List<Room> list = roomMapper.selectByTypeIdAndStatus(typeId, 0);
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
            order.setStatus(0);
            order.setHotel_id(roomType.getHotelId());
            order.setRoom_id(room.getRoomId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setTime(new Date());
            System.out.println(order);
            System.out.println(new Date());
            orderMapper.insertSelective(order);
            return room;
        }
    }

    @Override
    public double recharge(double money, int userId) {
        Customer customer = customerMapper.selectByPrimaryKey(userId);
        System.out.println(customer);
        double account = money + customer.getAccount();
        customer.setAccount(account);
        customerMapper.updateByPrimaryKey(customer);
        return account;
    }

    @Override
    public List<OrderAndComment> findOrdersByUserId(int userId) {
        List<Order> orderList = orderMapper.selectByUserId(userId);
        List<OrderAndComment> resultList = new ArrayList<>();
        for (Order order : orderList) {
            System.out.println(order);
//            Condition condition1 = new Condition(Comment.class);
//            condition.createCriteria().andCondition("order_id", order.getOrder_id());
//            System.out.println(order.getOrder_id());
            Comment comment = commentMapper.selectByOrderId(order.getOrder_id());
            System.out.println(comment);
            OrderAndComment o = new OrderAndComment();
            Hotel hotel = hotelMapper.selectByPrimaryKey(order.getHotel_id());
            o.setOrderId(order.getOrder_id());
            o.setHotelName(hotel.getHotelName());
            o.setPhone(hotel.getPhone());
            Customer customer = customerMapper.selectByPrimaryKey(userId);
            o.setUsername(customer.getUsername());
            o.setOrderTime(order.getTime());
            o.setPayment(order.getPayment());
            Room room = roomMapper.selectByPrimaryKey(order.getRoom_id());
            o.setRoomNumber(room.getRoomNumber());
            if (comment != null ) {
                o.setContent(comment.getContent());
                o.setRating(comment.getRating());
                o.setCommentTime(comment.getTime());
            }
            resultList.add(o);
        }
        return resultList;
    }


}
