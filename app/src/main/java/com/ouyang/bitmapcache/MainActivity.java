package com.ouyang.bitmapcache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add(i + ">>>>>>>>>>>>>>>");
        }

        MyAdapter adapter = new MyAdapter(this,data);
        lv.setAdapter(adapter);
    }
}
