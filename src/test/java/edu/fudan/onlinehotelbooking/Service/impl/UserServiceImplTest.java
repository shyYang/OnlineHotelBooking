package edu.fudan.onlinehotelbooking.Service.impl;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends Tester {

    @Resource
    UserService us;
    @Test
    void listSellers() {
        assertNotNull(us.listSellers());
    }



    @Test
    void getUsersOfCustomers() {
        Customer customer = new Customer();
        customer.setUserId(1);
        ArrayList a = new ArrayList<Customer>();
        a.add(customer);
        us.getUsersOfCustomers(a);
    }
}