package com.example.b0.banqiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
   private TextView editView1;
   private TextView editView2;
   private TextView editView3;
   private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }
    void init(){
        editView1=findViewById(R.id.editText);
        editView1=findViewById(R.id.editText2);
        editView3=findViewById(R.id.editText3);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataTest dataTest=new DataTest();
                 dataTest.setName(editView1.getText().toString().trim());
                dataTest.setData1(Integer.parseInt(editView2.getText().toString().trim()));
                dataTest.setData2(Integer.parseInt(editView3.getText().toString().trim()));
                dataTest.save();
            }
        });
    }
}
