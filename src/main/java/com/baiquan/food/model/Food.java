package com.baiquan.food.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 菜谱
 *
 * @author : QuanBai
 * @Date : Created in 2018/5/23 15:30
 */
@Document(collection = "t_food")
@Data
public class Food extends Base{

    public Food(){
        super();
    }

    private String name;

    private String info;

    private String material;

    private String img;

    private String[] interrelates;

    private String introduction;

    private Integer index;

    private String readNum;


}
