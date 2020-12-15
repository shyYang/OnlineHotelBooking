package edu.fudan.onlinehotelbooking.Service.impl;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.service.FileUploadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadServiceImplTest extends Tester {

    @Resource
    FileUploadService fs;
    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadImage() {
        MockMultipartFile firstFile = new MockMultipartFile("upload_file", "filename.txt", "text/plain", "some xml".getBytes());

        assertNull(fs.uploadImage(firstFile));
    }
}