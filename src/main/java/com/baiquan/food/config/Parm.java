package com.baiquan.food.config;

/**
 * asd
 *
 * @author : QuanBai
 * @Date : Created in 2018/5/25 16:01
 */

public class Parm {


    public static final String BAIDU_APP_KEY = "QVm9oxSGDQGyFiAh0ogNgpN7";

    public static final String BAIDU_APP_SECRET = "GrxISCPpEa6icq940AQtE0QuDYb5D4Y1";
    //获取百度token
    public static final String GET_BAIDU_TOKEN = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id="+BAIDU_APP_KEY+"&client_secret="+BAIDU_APP_SECRET;

    //发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    public static final String GET_MP3 = "http://tsn.baidu.com/text2audio?tex=TXT&lan=zh&cuid=USERID&ctp=1&tok=TOKEN&per=PER"; //语音合成

}
