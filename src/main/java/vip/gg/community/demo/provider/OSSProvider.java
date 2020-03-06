package vip.gg.community.demo.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.gg.community.demo.exception.CustomizeErrorCode;
import vip.gg.community.demo.exception.CustomizeException;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * Creat by GG
 * Date on 2020/3/6  2:49 下午
 */
@Service
public class OSSProvider {

    @Value("${oss.coldcode.endpoint}")
    private String endpoint;

    @Value("${oss.coldcode.accessKeyId}")
    private String id;

    @Value("${oss.coldcode.accessKeySecret}")
    private String secret;



    public String upload(InputStream inputStream,String fileName){

        OSS ossClient = new OSSClientBuilder().build(endpoint, id, secret);
        String generateFileName = "";
        String[] fileSpliter = fileName.split("\\.",0);
        if(fileSpliter.length>1){
            if (fileSpliter[fileSpliter.length - 1].equals("png") || fileSpliter[fileSpliter.length - 1].equals( "jpeg")
                    || fileSpliter[fileSpliter.length - 1].equals("jpg")){
                generateFileName = "images/"+fileSpliter[0]+UUID.randomUUID().toString()+"."+fileSpliter[fileSpliter.length - 1];
            }else{
                generateFileName = "others/"+fileSpliter[0]+UUID.randomUUID().toString()+"."+fileSpliter[fileSpliter.length - 1];
            }
        }else {
            return null;
        }
        PutObjectResult coldcode = ossClient.putObject("coldcode", generateFileName, inputStream);
        if(coldcode != null){
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24);
            URL url = ossClient.generatePresignedUrl("coldcode" ,generateFileName , expiration);
            return url.toString();
        }else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }

}
