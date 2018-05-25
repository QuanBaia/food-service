package com.baiquan.food.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baiquan.food.model.Food;
import com.baiquan.food.util.EncodeUtils;
import com.baiquan.food.util.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : QuanBai
 * @Date : Created in 2018/5/23 16:54
 */
@Service
public class FoodService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    RestTemplate restTemplate;



    public List<Food> getIndexItem(Integer size){
        long count = mongoTemplate.count(new Query(),Food.class);
        Integer[] sizes = RandomUtils.randomCommon(1,(int)count,size);

        Query query = new Query(
                Criteria.where("index").in(sizes)
        );
        List<Food> list  = mongoTemplate.find(query,Food.class);
        return list;
    }




    public List<Food> getListFood(String search){

        Query query = new Query(
                Criteria.where("name").regex(".*?\\"+search+".*")
                        .and("info").regex(".*?\\"+search+".*")
        );

        return mongoTemplate.find(query,Food.class);

    }


    public String getFood(String id){
        Query query = new Query(
                Criteria.where("id").is(id)
        );
        Food food = mongoTemplate.findOne(query,Food.class);

        String url = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord="+food.getName()+"&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word="+food.getName()+"&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=1&fr=&pn=30&rn=30&gsm=1e&1527213105663=";

        JSONArray imgs = new JSONArray();
        JSONObject jsonObject = restTemplate.getForObject(url,JSONObject.class);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0;i< jsonArray.size();i++){

            JSONObject info = jsonArray.getJSONObject(i);
            String formUrl = info.getString("objURL");
            if (StringUtils.isNotBlank(formUrl)){
               imgs.add( EncodeUtils.baiDuImgUrl(formUrl));
               if (imgs.size() == 10){
                   break;
               }
            }
        }

        JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(food));
        result.put("imgs",imgs);



        return "";
    }

}
