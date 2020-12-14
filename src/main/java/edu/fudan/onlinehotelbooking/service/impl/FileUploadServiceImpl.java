package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.service.FileUploadService;
import edu.fudan.onlinehotelbooking.util.FileNameUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    //支持的图片类型
    private static final List<String> IMAGE_CONTENT_TYPE = Arrays.asList("image/gif", "image/jpeg","image/png");
    //支持的文件类型

    // 文件的真实路径
    @Value("${file.uploadFolder}")
    private String realBasePath;
    @Value("${file.accessPath}")
    private String accessPath;

    @Override
    public String uploadImage(MultipartFile file) {
        return handleFile(file);
    }

    private String handleFile(MultipartFile file){
        String newFileName = FileNameUtil.getFileName(file.getOriginalFilename());
        String contentType = file.getContentType();
        File dir = new File(realBasePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        if (!IMAGE_CONTENT_TYPE.contains(contentType)) {
            return null;
        }
        String path = realBasePath + newFileName;
        try {
            File dest = new File(path,"");
            // 判断文件父目录是否存在
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessPath+newFileName;
    }
}
