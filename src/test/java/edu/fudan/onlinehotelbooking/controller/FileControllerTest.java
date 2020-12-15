package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.Tester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class FileControllerTest extends Tester {
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
    @Test
    void fileUpload() throws Exception{
        MockMultipartFile firstFile = new MockMultipartFile("upload_file", "filename.txt", "text/plain", "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file_upload")
                .file(firstFile))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


    }
}