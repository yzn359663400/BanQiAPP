package com.example.b0.banqiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  void init(){
        findViewById(R.id.show_data).setOnClickListener(this);
        findViewById(R.id.add_data).setOnClickListener(this);
        findViewById(R.id.delete_data).setOnClickListener(this);
        findViewById(R.id.update_data).setOnClickListener(this);
        findViewById(R.id.query_data).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_data:
                Intent intent1=new  Intent(this,ShowActivity.class);
                startActivity(intent1);
                break;
            case R.id.add_data:
                Intent intent2=new  Intent(this,AddActivity.class);
                startActivity(intent2);
                break;
            case R.id.delete_data:
                Intent intent3=new  Intent(this,DeleteActivity.class);
                startActivity(intent3);
                break;
            case R.id.update_data:
                Intent intent4=new  Intent(this,UpdataActivity.class);
                startActivity(intent4);
                break;
            case R.id.query_data:
                Intent intent5=new  Intent(this,DeleteActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
