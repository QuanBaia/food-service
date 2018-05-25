package com.baiquan.food.util;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/25 10:19
 */

public class EncodeUtils {


    //百度图片解析

    public static String baiDuImgUrl(String fromUrl) {

        fromUrl = fromUrl.replace("ippr", "http")
                .replace("_z2C$q", ":")
                .replace("AzdH3F", "/")
                .replace("_z&e3B", ".");
        fromUrl = fromUrl.toLowerCase();
        fromUrl = fromUrl.substring(4);

        char[] arr = fromUrl.toCharArray();
        fromUrl = "";

        for (char a : arr) {

            switch (a) {
                case 'w':
                    fromUrl += "a";
                    break;
                case 'k':
                    fromUrl += "b";
                    break;
                case 'v':
                    fromUrl += "c";
                    break;
                case '1':
                    fromUrl += "d";
                    break;
                case 'j':
                    fromUrl += "e";
                    break;
                case 'u':
                    fromUrl += "f";
                    break;
                case '2':
                    fromUrl += "g";
                    break;
                case 'i':
                    fromUrl += "h";
                    break;
                case 't':
                    fromUrl += "i";
                    break;
                case '3':
                    fromUrl += "j";
                    break;
                case 'h':
                    fromUrl += "k";
                    break;
                case 's':
                    fromUrl += "l";
                    break;
                case '4':
                    fromUrl += "m";
                    break;
                case 'g':
                    fromUrl += "n";
                    break;
                case '5':
                    fromUrl += "o";
                    break;
                case 'r':
                    fromUrl += "p";
                    break;
                case 'q':
                    fromUrl += "q";
                    break;
                case '6':
                    fromUrl += "r";
                    break;
                case 'f':
                    fromUrl += "s";
                    break;
                case 'p':
                    fromUrl += "t";
                    break;
                case '7':
                    fromUrl += "u";
                    break;
                case 'e':
                    fromUrl += "v";
                    break;
                case 'o':
                    fromUrl += "w";
                    break;
                case '8':
                    fromUrl += "1";
                    break;
                case 'd':
                    fromUrl += "2";
                    break;
                case 'n':
                    fromUrl += "3";
                    break;
                case '9':
                    fromUrl += "4";
                    break;
                case 'c':
                    fromUrl += "5";
                    break;
                case 'm':
                    fromUrl += "6";
                    break;
                case '0':
                    fromUrl += "7";
                    break;
                case 'b':
                    fromUrl += "8";
                    break;
                case 'l':
                    fromUrl += "9";
                    break;
                case 'a':
                    fromUrl += "0";
                    break;
                default:
                    fromUrl += a;
                    break;
            }

        }
        return "http"+fromUrl;

    }
}