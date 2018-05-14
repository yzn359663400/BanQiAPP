package com.example.b0.banqiapp;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_log = (Button) findViewById(R.id.button);
    EditText et_name = (EditText) findViewById(R.id.editText);
    EditText et_pass = (EditText) findViewById(R.id.editText2);
    // 获取选框组件
    CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
    // 获取用户输入的账号和密码
    String name = et_name.getText().toString();
    String pass = et_pass.getText().toString();


    public void onClick(View v) {
   // 开启子线程通过网络服务器验证
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        // 检测选框是否被勾选
        if (cb.isChecked()) {
            saveAccount(name, pass);//存到SQLite数据库中
        }

        // 成功后弹Toast提示框
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAccount();
    }



    public void saveAccount(String name, String pass) {
        File file = new File("data/data/com.example.b0/info.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write((name + "##" + pass).getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAccount() {
        File file = new File("data/data/com.example.bo/info.txt");
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                // 把字节流转换为字节流
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        fis));
                String text = br.readLine();
                String[] s = text.split("##");
                // 获取用户输入的账号和密码
                EditText et_name = (EditText) findViewById(R.id.editText);
                EditText et_pass = (EditText) findViewById(R.id.editText2);
                et_name.setText(s[0]);
                et_pass.setText(s[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
