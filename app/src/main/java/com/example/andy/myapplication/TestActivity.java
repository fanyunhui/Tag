package com.example.andy.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    @Bind(R.id.lv)
    ListView lv;
    private List<String> data=new ArrayList<>();
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initView() {
        adapter=new TestAdapter(this);
        lv.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            data.add("测试数据"+i);
        }
        adapter.setData(data);

    }
}
