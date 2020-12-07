package edu.fudan.onlinehotelbooking.service;
import edu.fudan.onlinehotelbooking.entity.Customer;
import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.UserOfCustomer;


/**
 * Created by CodeGenerator on 2020/12/04.
 */
public interface CustomerService extends Service<Customer> {

    //用户注册
    int saveCustomer(UserOfCustomer customer);

}
