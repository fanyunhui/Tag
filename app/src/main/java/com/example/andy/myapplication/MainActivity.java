package com.example.andy.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final int ID =0X123 ;
    public static final String ACTION_INTENT_TEST = "com.broadcast.test";
    @Bind(R.id.btn)
    Button btn;
    NotificationManager manager;

    private static final String TAG = "MainActivity";
    public static WeakReference<MainActivity> mActivityRef;
    private MyReceiver myReceiver;

    private TestService.MyBind myBind;

    public static MainActivity getInstance(){
        return mActivityRef==null?null:mActivityRef.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivityRef=new WeakReference<MainActivity>(this);
        Log.i(TAG,"onCreate");

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //event.getAction()=0代表ACTION_DOWN，event.getAction()=1代表ACTION_UP，
                //event.getAction()=2代表ACTION_MOVE
                Log.i(TAG, "onTouch execute, action " + event.getAction());
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"click");
            }
        });

        //动态注册广播
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.broadcast.test");
        registerReceiver(myReceiver, intentFilter);
    }

    public void tv(View v) {
        startActivity(new Intent(this,MainActivity2.class));
    }


    @OnClick(R.id.btn_notification)
    void openNotification(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:1110"));
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
                0, intent, 0);
        Notification.Builder builder = new Notification.Builder(
                MainActivity.this);
        builder
                // Notification notification = new
                // Notification.Builder(MainActivity.this)

                // 设置打开通知，该通知取消
                .setAutoCancel(true)
                // 设置通知提示信息
                .setTicker("有新消息")
                // 设置通知的图标
                .setSmallIcon(R.mipmap.ic_launcher)
                // 设置通知的标题
                .setContentTitle("不好了！！！")
                // 设置通知的内容
                .setContentText("你家猪跑了")
                // 设置使用系统默认的声音、LED
                .setDefaults(
                        Notification.DEFAULT_LIGHTS
                                | Notification.DEFAULT_SOUND)
                // 设置通知发布时间
                .setWhen(System.currentTimeMillis())
                // 设置将要启动的活动
                .setContentIntent(pi).build();

        Notification notification = builder.build();

        manager.notify(ID, notification);
    }

    //弹出对话框，点击跳转
    @OnClick(R.id.btn_cancle_notification)
    void cancle(){
        Toast.makeText(this,"取消成功",Toast.LENGTH_SHORT).show();
        manager.cancel(ID);
    }

    //发送广播
    @OnClick(R.id.send_broadcast)
    void sendBroadCast(){
        Intent intent=new Intent();
        //与清单文件的receiver的anction对应
        intent.setAction("com.broadcast.test");
        intent.putExtra("name","测试静态注册广播");
        //发送广播
        sendBroadcast(intent);
        //发送指定的广播
//        sendOrderedBroadcast(intent,null);
//        sendOrderedBroadcast(intent,null,new MyReceiver(),null,0,null,null);
    }

    //开启服务
    @OnClick(R.id.start_service)
    void startService(){
        Intent intent=new Intent(this,TestService.class);
        //开启服务的两种方法
//        startService(intent);

        //绑定服务
        bindService(intent,new myConn(),BIND_AUTO_CREATE);
    }

    class myConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            myBind= (TestService.MyBind) iBinder;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }
    //停止服务
    @OnClick(R.id.stop_service)
    void  stopService(){
        Intent intent=new Intent(this,TestService.class);
        stopService(intent);
    }

    //调用服务中的方法
    @OnClick(R.id.service_function)
    void function(){
        myBind.callTest("唱歌了");
    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG,"onSaveInstanceState111");
    }

    @Override
    protected void onStart() {
        super.onStart();

//        CheckNetwork();
        Log.i(TAG,"onStart");
    }

    /**
     * 网络检查
     */
    private void CheckNetwork() {
        ConnectivityManager manager= (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
            Toast.makeText(this,"网络可用！",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"网络不可用！",Toast.LENGTH_SHORT).show();
        }
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
        if (mActivityRef!=null){
            mActivityRef.clear();
        }
        //解除广播
        unregisterReceiver(myReceiver);
    }
}
