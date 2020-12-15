package edu.fudan.onlinehotelbooking.Service.impl;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.service.CommentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest extends Tester {

    @Resource
    CommentService cs;
    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test ends");
    }

    int userID = 1;
    @Test
    void getCommentOfUser() {
        assertNotNull(cs.getCommentOfUser(userID));
    }

    @Test
    void getAllComments() {
        assertNotNull(cs.getAllComments());
    }

    @Test
    void delCommentOfUser() {
        assertNotNull(cs.delCommentOfUser(userID));
    }

    @Test
    void getCommentsOfUser() {
        assertNotNull(cs.getCommentsOfUser(userID));
    }
}