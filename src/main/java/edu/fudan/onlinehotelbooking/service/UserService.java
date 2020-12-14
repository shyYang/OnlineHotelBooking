package edu.fudan.onlinehotelbooking.service;

import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.entity.User;

import java.util.List;

public interface UserService extends Service<User>{
    List<User> listSellers ();
    int delCustomer(int customerID);
    int delSeller(int sellerID);
    List<User> getUsersOfCustomers(List<Customer> customerList);

}
