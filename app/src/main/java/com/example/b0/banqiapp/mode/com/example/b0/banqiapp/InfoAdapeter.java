package com.example.b0.banqiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InfoAdapeter extends ArrayAdapter {
    private  int itemid=0;
    public InfoAdapeter(@NonNull Context context, int resource, @NonNull List<DataTest> objects) {
        super(context, resource, objects);
        itemid=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DataTest dataTest = (DataTest) getItem(position);
        @SuppressLint("ViewHolder")
        View view= LayoutInflater.from(getContext()).inflate(itemid,parent,false);
        TextView textView1=view.findViewById(R.id.textView);
        TextView textView2=view.findViewById(R.id.textView2);
        TextView textView3=view.findViewById(R.id.textView3);
        assert dataTest != null;
        textView1.setText(dataTest.getName());
        textView2.setText(dataTest.getData1());
        textView3.setText(dataTest.getData2());
        return view;
    }
}
