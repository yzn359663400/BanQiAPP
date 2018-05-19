package com.example.b0.banqiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.net.URLEncoder;


//在创建活动的时候从SQLite数据库中加载用户名和密码，点击登录后连接服务器登录验证，然后检查是否保存用户名和密码。
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final int CK_UI = 1;
    protected static final int ERROR = 0;
    protected static  final  String LOGINTAG="LoginActivity";
    protected static boolean LOAD=false;
    private   Button btn_log;
    private EditText et_name;
    private EditText et_pass;
    private CheckBox cb;
    private String name;
    private String password;

    //        主线程消息处理器
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CK_UI) {  // 成功后弹Toast提示框
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);//开启CRUD活动
                // 检测选框是否被勾选
                if (cb.isChecked()) {
                    if (name != null && password != null)
                        saveAccount(name, password);//用户名密码存到SQLite数据库

                }
            } else if (msg.what == ERROR) {
               Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                //saveAccount(name, password);//用户名密码存到SQLite数据库中
              //  Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

            }

            Log.d(LOGINTAG,"处理登录完毕");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        Log.d(LOGINTAG,"开始了");
        init();
        loadAccount();
        }

    private void init() {
        btn_log = findViewById(R.id.button);
        btn_log.setOnClickListener(this);
        et_name = findViewById(R.id.editText);
        et_pass = findViewById(R.id.editText2);
        cb = findViewById(R.id.checkBox);

    }

    public void onClick(View v) {
        // 开启子线程通过网络服务器验证
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取用户输入的账号和密码并通过服务器验证
                name = et_name.getText().toString().trim();
                password = et_pass.getText().toString().trim();
                String response="";
                String url="http://10.0.2.2:8080/BanQiAPP/LoginCheckServlet?uName="
                        + URLEncoder.encode(name)+"&uPassword="+URLEncoder.encode(password);
                Log.d(LOGINTAG,url);
                try {
                    response=HttpUtil.sendOkHttpRequestLogin(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 信息回传回主线程
                Message message=new Message();
//                字符串变量是引用类型
                if(response.equals("OK") ){
                    message.what =CK_UI;
                    handler.sendMessage(message);
                }
                else {
                    message.what =ERROR;
                    handler.sendMessage(message);
                }
                Log.d(LOGINTAG,"消息回传到主线程");
            }
        }).start();

    }


    //保存用户名密码到数据库
    public void saveAccount(String name, String pass) {
        User user = new User();
        //Litepal数据库自动生成自增的ID
        user.setPwd(pass);
        user.setUsername(name);
        user.save();

        LOAD=true;
        Log.d(LOGINTAG,"保存用户名密码到数据库");
    }

    //从数据库加载用户名和密码
    public void loadAccount() {
        Log.d(LOGINTAG,"加载库的用户名和密码");
            User user;
            try{
             user= DataSupport.findFirst(User.class);
            if (user!=null)
            {  et_name.setText(user.getUsername());
            et_pass.setText(user.getPwd());}
            else{ et_name.setText("mike1");
            et_pass.setText("123456");}


    }catch(Exception e)
            {
                e.printStackTrace();
            }}


}
