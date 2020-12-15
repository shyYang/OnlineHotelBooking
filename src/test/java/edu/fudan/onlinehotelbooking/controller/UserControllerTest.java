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
class UserControllerTest extends Tester {

    private MockMvc mockMvc;
    private MockHttpSession session;
    @Resource
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        System.out.println("start testing");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();
        session.setAttribute("USER_ID_SESSION","1");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test ends");
    }


    @Test
    void login() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .session(session)
                .content("{\"userId\":\"1\",\"role\":\"l\",\"password\":\"lwy666\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void logout() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/logout")
                .session(session)
                //.content("{\"userId\":\"1\",\"username\":\"ll\",\"password\":\"lwy666\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void changePassword() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/user/change_password")
                .session(session)
                .param("password","hhh666")
                //.content("{\"userId\":\"1\",\"username\":\"ll\",\"password\":\"lwy666\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
}