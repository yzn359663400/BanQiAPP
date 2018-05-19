package com.example.b0.banqiapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
//字符串解析，返回对象集合
public class GsonHandle {
    public static List<DataTest> requestHandleWithGson(String jsonData){
        Gson gson=new Gson();
        List<DataTest> dataTests=gson.fromJson(jsonData,new TypeToken<List<DataTest>>(){}.getType());
        return dataTests;

    }
}
