package com.baiquan.food.controller;

import com.baiquan.food.dao.BaiduService;
import com.baiquan.food.dao.FoodService;
import com.baiquan.food.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/23 15:38
 */
@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;
    @Autowired
    BaiduService baiduService;


    @GetMapping("/getIndexItem")
    public List<Food> getIndexItem(Integer size){

        return foodService.getIndexItem(size);
    }

    @GetMapping("/getListFood")
    public List<Food> getListFood(String search){
        return foodService.getListFood(search);
    }

    @GetMapping("/getFood")
    public String getFood(String id) throws Exception{
        return foodService.getFood(id);
    }


    @GetMapping("/getMusicUrl")
    public String getMusicUrl(String id,String txt){
        return baiduService.getMusicUrl(id,txt);
    }

}
