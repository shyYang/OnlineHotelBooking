package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.entity.User;
import edu.fudan.onlinehotelbooking.service.CommentService;
import edu.fudan.onlinehotelbooking.service.HotelService;
import edu.fudan.onlinehotelbooking.service.OrderService;
import edu.fudan.onlinehotelbooking.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class AdminControllerTest extends Tester {
    private MockMvc mockMvc;
   @Resource
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void before(){
        System.out.println("start testing");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @AfterEach
    void after(){
        System.out.println("test ends");
    }
    String userID ="1";
    String oderId = "1";
    @Test
    void listUsers()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/users")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
    @Test
    void s() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/room/list")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }


    @Test
    void listOrdersOfUser() throws Exception{

        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/orders_all")
                .param("userID","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
        //assertTrue(s.contains());
    }

    @Test
    void listCommentsOfUser() throws Exception {

        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/comments_all")
                .param("userID",userID)
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }

    @Test
    void deleteOrderByIdTest() throws Exception {
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete_order")
                .param("oderID",oderId)
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    String commentID = "1";
    @Test
    void deleteCommentTest() throws Exception{
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete_comment")
                .param("commentID",commentID)
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
    String customerID = "1";
    @Test
    void deleteConsumerTest () throws Exception{
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete_consumer")
                .param("customerID",customerID)
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    String hotelID ="1";
    @Test
    void deleteHotelTest() throws Exception{
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete_seller")
                .param("hotelID",hotelID)
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void deleteUnexistedHotelTest() throws Exception{
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete_seller")
                .param("hotelID","10086")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void listSellers()throws Exception {
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin/sellers")

                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void testForURLMapping() throws Exception{
        String s =mockMvc.perform(MockMvcRequestBuilders.get("/admin")//未定义的mapping

                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse()

                .getContentAsString(); //将相应的数据转换为字符
        assertFalse(s.isEmpty());
    }
}