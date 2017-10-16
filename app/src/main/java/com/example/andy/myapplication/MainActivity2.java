
package com.example.andy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.andy.myapplication.view.Seekbar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.seek)
    Seekbar seekbar;

    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        intView();
        Log.i(TAG,"onCreate");
    }

    private void intView() {
        seekbar.setText(tvTitle);
        //第五步 设置回调监听的实现
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
