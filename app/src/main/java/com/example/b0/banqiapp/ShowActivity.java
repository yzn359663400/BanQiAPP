package com.example.b0.banqiapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

//服务器端和客户端数据库都提供了相关的接口操作CRUD
public class ShowActivity extends AppCompatActivity {
    private static final String LOGINTAG = "ShowActivity";
    private ListView listView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
             List<DataTest> dataTests=null;
            dataTests = (List<DataTest>) msg.obj;
            InfoAdapeter infoAdapeter=new InfoAdapeter(ShowActivity.this,R.layout.list_itemlayout,dataTests);
          listView.setAdapter(infoAdapeter);
            }
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
         listView = findViewById(R.id.listview);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取用户输入的账号和密码并通过服务器验证
                String response = "";
                List<DataTest> dataTests=null;
                String url = "http://10.0.2.2:8080/BanQiAPP/Newinfo.json";
                Log.d(LOGINTAG, "即将发送网络请求");
                try {
                    response = HttpUtil.sendOkHttpRequestLogin(url);
                     dataTests = GsonHandle.requestHandleWithGson(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 信息回传回主线程
                Message message=new Message();
//                Bundle bundle = new Bundle();
//                bundle.put("data", (Serializable) dataTests);
//                    message.setData(bundle);
                message.obj=dataTests;
                    handler.sendMessage(message);
                    Log.d(LOGINTAG, "消息回传到主线程");

            }
        }).start();

    }
}