package edu.fudan.onlinehotelbooking.controller;

import com.mysql.cj.Session;
import edu.fudan.onlinehotelbooking.Tester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static edu.fudan.onlinehotelbooking.core.ProjectConstant.HOTEL_ID_SESSION;
import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class HotelControllerTest extends Tester {

    private MockMvc mockMvc;
    private MockHttpSession session;
    @Resource
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    void before(){
        System.out.println("start testing");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();
        session.setAttribute("HOTEL_ID_SESSION","1");

    }
    @AfterEach
    void after(){
        System.out.println("test ends");
    }

    @Test
    void signUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/hotel/sign_up")
                .content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符


    }

    @Test
    void listAllHotels() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/list_all_hotel")
                //.content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }

    @Test
    void listTopHotels() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/list_top_hotel")
               // .content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }

//    @Test
//    void findHotelById() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/find_hotel")
//               // .content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
//               //.content("{\"hotelId\":\"1\"}")
//                .session(session)
//                .contentType(MediaType.APPLICATION_JSON) //数据的格式
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
//                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
//                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
//    }

//    @Test
//    void findRoomType() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/find_room_type")
//                //.content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
//                .contentType(MediaType.APPLICATION_JSON) //数据的格式
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
//                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
//                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
//    }

    @Test
    void findHotelById1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/find_hotel_by_id")
                //.content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .param("hotelId","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }

    @Test
    void findRoomType1() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/find_room_type_by_id")
                //.content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .param("hotelId","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }

    @Test
    void searchHotels() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/search_hotel")
               // .content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .param("hotelName","复旦卿云宾馆")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符
    }
    @Test
    void finish()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel/finish_order")
                // .content("{\"hotelName\":\"lwy\",\"password\":\"hhh666\",\"address\":\"somewhere\",\"phone\":\"12312341234\",\"photo\":\"00\",\"introduction\":\"good\"}")
                .param("orderId","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
}