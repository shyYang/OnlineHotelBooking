package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.service.CommentService;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class AdminControllerTest extends Tester {
    private MockMvc mockMvc;
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    @Resource
    private OrderService orderService;
    @Resource
    private HotelService hotelService;

    @Test
    void listUsers() {
        List<User> list = userService.findAll();
        assertNotNull(list);
    }

    @Test
    void listOrderOfID() {
        int orderID =2;
        assertEquals(orderService.getOrder(orderID).getRoom_id(),5);
    }

    @Test
    void listOrdersOfUser() {
    }

    @Test
    void listCommentsOfUser() {
    }

    @Test
    void listComments() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void deleteSeller() {
    }

    @Test
    void deleteHotel() {
    }

    @Test
    void listSellers() {
    }
}