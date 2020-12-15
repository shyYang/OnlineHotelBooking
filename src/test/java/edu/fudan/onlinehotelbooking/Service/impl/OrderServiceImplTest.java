package edu.fudan.onlinehotelbooking.Service.impl;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest extends Tester {

    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
    }

    @Resource
    OrderService os;
    int userId =1;
    @Test
    void getOrderOfUser() {
        assertNotNull(os.getOrderOfUser(1));
    }

    @Test
    void getOrdersOfHotel() {
        assertNotNull(os.getOrdersOfHotel(1));
    }


    @Test
    void getOrdersOfStatus() {
        assertNotNull(os.getOrdersOfStatus(1));
    }

    @Test
    void finishOrder() {
        assertNotNull(os.finishOrder(2));
    }

    @Test
    void getDetail() {
        assertNotNull(os.getOrderDetailsOfUser(1));
    }
}