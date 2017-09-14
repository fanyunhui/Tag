package com.example.andy.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

public class TestService extends Service {

    //绑定服务执行的方法
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"绑定服务",Toast.LENGTH_SHORT).show();
        return new MyBind();
    }

    public class MyBind extends Binder{
        public void callTest(String str){



            test(str);
        }
    }

    //服务中的方法
    public void test(String str) {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"开启服务",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
