package edu.fudan.onlinehotelbooking.Service.impl;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.RoomType;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.service.CommentService;
import edu.fudan.onlinehotelbooking.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest extends Tester {

    @Resource
    CustomerService cs;
    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test ends");
    }

    @Test
    void saveCustomer() {
        UserOfCustomer customer = new UserOfCustomer();
        customer.setAccount(0);
        customer.setGender("男");
        customer.setPhone("12312311231");
        customer.setUsername("lwyppp");
        customer.setPassword("lll123");
        assertNotNull(cs.saveCustomer(customer));
    }

    @Test
    void orderRoom() {
        int typeId = 1;
        int hotelId = 7;
        double price = 500;
        String photo = "wu";
        int number = 1;
        int freeNumber = 1;
        String introduction="ceshi";
        String name = "ceshi";
        RoomType roomType = new RoomType();
        roomType.setFreeNumber(freeNumber);
        roomType.setNumber(number);
        roomType.setName(name);
        roomType.setPrice(price);
        roomType.setPhoto(photo);
        roomType.setHotelId(hotelId);
        roomType.setIntroduction(introduction);
        int userId = 1;

        assertNotNull(cs.orderRoom(typeId,roomType,userId));
    }

    @Test
    void recharge() {
        assertNotNull(cs.recharge(8.8,1));
    }

    @Test
    void findOrdersByUserId() {
        assertNotNull(cs.findOrdersByUserId(1));
    }

}