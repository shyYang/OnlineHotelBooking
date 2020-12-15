package edu.fudan.onlinehotelbooking.controller;

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

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class RoomControllerTest extends Tester {

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
    void add() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/room/add")
                .content("{\"hotelId\":\"1\",\"price\":\"666\",\"price\":\"100\",\"number\":\"12\",\"photo\":\"00\",\"introduction\":\"good\",\"name\":\"good\",\"freeNumber\":\"10\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/room/delete")
                //.content("{\"hotelId\":\"1\",\"price\":\"100\",\"number\":\"12\",\"photo\":\"00\",\"introduction\":\"good\",\"name\":\"good\",\"freeNumber\":\"10\"}")
                .param("roomId","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }



    @Test
    void list() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/room/list")
                .param("hotelId","1")
                // .content("{\"hotelId\":\"1\",\"price\":\"666\",\"price\":\"100\",\"number\":\"12\",\"photo\":\"00\",\"introduction\":\"good\",\"name\":\"good\",\"freeNumber\":\"10\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
}