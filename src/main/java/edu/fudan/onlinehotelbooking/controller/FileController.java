package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.service.FileUploadService;
import edu.fudan.onlinehotelbooking.service.impl.FileUploadServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * created by 姜向阳
 * on 2020/12/14
 */

@RestController
public class FileController {
    @Resource
    FileUploadService fileUploadService;

    @RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public Result fileUpload(@RequestParam(value = "upload_file") MultipartFile file) {
        String path = fileUploadService.uploadImage(file);
        if (path == null){
            return ResultGenerator.genFailResult("图片上传失败");
        }
        return ResultGenerator.genSuccessResult(path);
    }
}
