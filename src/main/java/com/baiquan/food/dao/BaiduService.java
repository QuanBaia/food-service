package com.baiquan.food.dao;

import com.alibaba.fastjson.JSONObject;
import com.baiquan.food.config.Parm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/25 16:00
 */
@Service
public class BaiduService {

    @Autowired
    private RestTemplate restTemplate;

    public JSONObject getBaiduToken(){ //获取百度token

        JSONObject baiduSession;

            baiduSession = new JSONObject();
            JSONObject result = restTemplate.getForObject(Parm.GET_BAIDU_TOKEN,JSONObject.class);
            baiduSession.put("accessToken",result.getString("access_token"));
            baiduSession.put("sessionKey",result.getString("session_key"));
            baiduSession.put("refreshToken",result.getString("refresh_token"));
            baiduSession.put("sessionSecret",result.getString("session_secret"));

        return baiduSession;
    }


    public String getMusicUrl(String id,String txt){ //获取播放地址
        JSONObject jsonObject = getBaiduToken();
        String url =  Parm.GET_MP3
                .replace("USERID",id)
                .replace("TXT",txt)
                .replace("TOKEN",jsonObject.getString("accessToken"))
                .replace("PER","4");
        JSONObject result = restTemplate.getForObject(url,JSONObject.class);
        if (result != null && !result.isEmpty()){
            Integer code = result.getInteger("err_no");
            if (code != null){
                if (code.equals(502)){
                    return "";
                }
            }
        }

        return url;
    }
}
