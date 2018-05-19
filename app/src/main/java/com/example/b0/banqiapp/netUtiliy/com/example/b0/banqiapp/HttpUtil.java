package com.example.b0.banqiapp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//通过一个地址字符串返回响应
public class HttpUtil {
    public static String  sendOkHttpRequestLogin(final String  url) throws IOException {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();//Buider静态内部类，连缀（适用于构造函数参数过多）
                    Response response=client.newCall(request).execute();
                    return  response.body().string();
    }

}
