package com.ouyang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            list.add(">>>>>>>>>>>>" + i);
        }

        MyAdapter adapter = new MyAdapter(this,list);
        lv.setAdapter(adapter);

        TextView tv;
        ImageView iv;
        Button btn;

    }




}
