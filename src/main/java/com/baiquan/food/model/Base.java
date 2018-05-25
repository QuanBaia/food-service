package com.baiquan.food.model;

import lombok.Data;

import java.util.Date;

/**
 * BaseModel
 *
 * @author : QuanBai
 * @Date : Created in 2018/5/23 15:31
 */
@Data
public class Base {

    private String id;

    private long times;
    public Base(){
        this.times = new Date().getTime();
    }


}
