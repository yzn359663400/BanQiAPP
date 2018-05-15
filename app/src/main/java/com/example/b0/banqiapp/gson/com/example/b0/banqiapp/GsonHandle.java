package com.example.b0.banqiapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonHandle {
    public static void requestHandleWithGson(String jsonData){
        Gson gson=new Gson();
        List<DataTest> dataTests=gson.fromJson(jsonData,new TypeToken<List<DataTest>>(){}.getType());
        for(DataTest dataTest:dataTests){

        }

    }
}
