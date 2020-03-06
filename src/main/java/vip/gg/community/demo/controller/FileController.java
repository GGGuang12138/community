package vip.gg.community.demo.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vip.gg.community.demo.dto.FileDTO;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.exception.CustomizeException;
import vip.gg.community.demo.provider.OSSProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Creat by GG
 * Date on 2020/3/5  10:57 下午
 */
@Controller
public class FileController {

    @Autowired
    private OSSProvider ossProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        try {
            String fileUrl = ossProvider.upload(file.getInputStream(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileUrl);
            return fileDTO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }

    }
}
