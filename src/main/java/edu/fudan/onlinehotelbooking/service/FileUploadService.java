package edu.fudan.onlinehotelbooking.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * created by 姜向阳
 * on 2020/12/14
 */
public interface FileUploadService {
    // 上传图片
    String uploadImage(MultipartFile file) ;
}
