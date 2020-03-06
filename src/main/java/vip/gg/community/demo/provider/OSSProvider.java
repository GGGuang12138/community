package vip.gg.community.demo.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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
        ossClient.putObject("coldcode", generateFileName, inputStream);
        return generateFileName;
    }

}
