package com.example.b0.banqiapp;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

//在创建活动的时候从SQLite数据库中加载用户名和密码，点击登录后连接服务器登录验证，然后检查是否保存用户名和密码。
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final int CK_UI = 1;
    protected static final int ERROR = 0;
    Button btn_log;
    EditText et_name;
    EditText et_pass;
    CheckBox cb;
    String name;
    String password;
    //        主线程消息处理器
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CK_UI) {  // 成功后弹Toast提示框
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                // 检测选框是否被勾选
                if (cb.isChecked()) {
                    if (name != null && password != null)
                        saveAccount(name, password);//存到SQLite数据库中
                }
            } else if (msg.what == ERROR) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        init();
        //loadAccount();


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
                // 获取用户输入的账号和密码
                name = et_name.getText().toString().trim();
                password = et_pass.getText().toString().trim();
                Message message=new Message();
                message.what =CK_UI;
                handler.sendMessage(message);

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
    }

    //从数据库加载用户名和密码
    public void loadAccount() {
        User user = DataSupport.findFirst(User.class);
        if (user != null) {
            et_name.setText(user.getUsername());
            et_pass.setText(user.getPwd());
        }
    }



}
