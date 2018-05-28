package com.baiquan.food.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/25 14:13
 */

@Service
public class HttpUtils {


    @Autowired
    private RestTemplate restTemplate;


    public String isImagesTrue(String posturl) throws Exception {
        System.out.println("获取图片");
        String code = "404";
        try {
            ResponseEntity result = restTemplate.getForEntity(posturl,String.class);
            code = result.getStatusCode().toString();
        }catch (Exception e){
            return "404";
        }
        System.out.println("--------------------------"+code);




        return code;
    }
}
