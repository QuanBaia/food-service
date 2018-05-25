package com.baiquan.food.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/25 14:13
 */

public class HttpUtils {
    public static String isImagesTrue(String posturl) {
        String code = "400";
        try {
            URL url = new URL(posturl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("GET");
            urlcon.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println(HttpURLConnection.HTTP_OK + posturl
                        + ":posted ok! ----------------图片获取成功");
                code = "200";
            } else {
                System.out.println(urlcon.getResponseCode() + posturl
                        + ":Bad post...----------------图片获取失败");
                code= "400";
            }
        } catch (Exception ex){
            System.out.println("error-------"+posturl
                    + ":Bad post...----------------图片获取失败");
            code="400";

        } finally {

            return  code;
        }
    }
}
