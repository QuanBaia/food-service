package com.baiquan.food.util;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/23 17:07
 */

public class RandomUtils{

    public static Integer[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        Integer[] result = new Integer[n];
        int count = 0;
        while(count < n) {
            Integer num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
