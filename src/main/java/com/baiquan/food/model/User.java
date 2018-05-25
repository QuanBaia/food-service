package com.baiquan.food.model;

import lombok.Data;

/**
 * 用户
 *
 * @author : QuanBai
 * @Date : Created in 2018/5/23 15:36
 */
@Data
public class User extends Base {

    public User(){
        super();
    }

    private String openId;

    private String phone;

    private String nickName;

    private String face;

    private String ip;
}
