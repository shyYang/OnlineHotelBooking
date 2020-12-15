package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.service.UserService;
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
class CustomerControllerTest extends Tester {
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
    void signUp() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/sign_up")
                .content("{\"username\":\"lwy\",\"gender\":\"男\",\"phone\":\"12312341234\",\"password\":\"lwy666\"}")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }


    @Test
    void showUserInformation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/user_information")
               .session(session)

                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void changeUserInformation()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/change_user_information")

                        .content("{\"username\":\"lwy\",\"gender\":\"男\",\"phone\":\"12312341234\",\"password\":\"lwy666\"}")

                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                        .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                        .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符



    }

    @Test
    void orderRoom() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/order_room")
                .session(session)
                .param("typeId","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void recharge() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/recharge")
                .session(session)
                .param("money","1")
                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void listAllOrders() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/list_all_order")
                .session(session)

                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }

    @Test
    void comment()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/comment")
                .session(session)
                .content("{\"content\":\"lwy\",\"rating\":\"2\",\"order_id\":\"1\"}")

                .contentType(MediaType.APPLICATION_JSON) //数据的格式
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); //将相应的数据转换为字符

    }
}