package com.baiquan.food.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baiquan.food.model.Food;
import com.baiquan.food.util.EncodeUtils;
import com.baiquan.food.util.HttpUtils;
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
    @Autowired
    HttpUtils httpUtils;



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
        if (search.equals("茶")){
            query = new Query(
                    Criteria.where("name").regex("^.*[\\茶]+$")
            );
        }
        if (search.equals("甜品")){

            query = new Query(Criteria.where("name").regex(".*?\\蛋糕.*"));

        }if (search.equals("主食")){
            query = new Query(Criteria.where("name").regex(".*?\\饭.*"));

        }

        return mongoTemplate.find(query,Food.class);

    }


    public String getFood(String id) throws Exception{
        Query query = new Query(
                Criteria.where("id").is(id)
        );
        Food food = mongoTemplate.findOne(query,Food.class);

        String url = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord="+food.getName()+"&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&word="+food.getName()+"&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=1&fr=&pn=30&rn=30&gsm=1e&1527213105663=";

        JSONArray imgs = new JSONArray();
        JSONObject jsonObject = restTemplate.getForObject(url,JSONObject.class);
        if (jsonObject != null){
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0;i< jsonArray.size();i++){

                JSONObject info = jsonArray.getJSONObject(i);
                String formUrl = info.getString("objURL");
                if (StringUtils.isNotBlank(formUrl)){

                    String ss = httpUtils.isImagesTrue(EncodeUtils.baiDuImgUrl(formUrl));
                    if (ss.equals("200")){
                        imgs.add( EncodeUtils.baiDuImgUrl(formUrl));
                    }

                    if (imgs.size() == 6){
                        break;
                    }
                }
            }
        }

        JSONObject result = new JSONObject();

        result.put("name",food.getName());
        result.put("id",food.getId());
        if (food.getInfo().contains("；")){
            result.put("info",food.getInfo().split("；"));
        }else if (food.getInfo().contains("。")){
            result.put("info",food.getInfo().split("。"));
        }else {
            result.put("info",food.getInfo().split(" "));
        }


        if (food.getMaterial().contains("1") && food.getMaterial().contains("2")  && food.getMaterial().contains("、")){
            result.put("material",food.getMaterial().split("；"));
        }else if (food.getMaterial().contains("1") && food.getMaterial().contains("2")  && !food.getMaterial().contains("；")){
            result.put("material",food.getMaterial().split(" "));
        }else if (food.getMaterial().contains("1") && food.getMaterial().contains("2")  && food.getMaterial().contains("。")){
            result.put("material",food.getMaterial().split("。"));
        }
        else {
            result.put("material",food.getMaterial().split("，"));
        }

        result.put("interrelates",food.getInterrelates());
        result.put("introduction",food.getIntroduction().split("，"));

        result.put("number",food.getReadNum());
        result.put("index",food.getIndex());



        result.put("imgs",imgs);



        return result.toJSONString();
    }

}
