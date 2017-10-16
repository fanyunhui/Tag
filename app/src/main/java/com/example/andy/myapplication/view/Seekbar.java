package com.example.andy.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.andy.myapplication.R;

/**
 * Author: fyh
 * Date: 2017/9/19 11:50
 * Email: 3122156086@qq.com
 * Des:
 */

public class Seekbar extends View {

    private Paint paint=new Paint();
    public static String[] b={"A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int choose=-1 ;
    private TextView textView;
    //自定义接口五部曲

    //第一步 定义接口
    public interface  onTouchLetterListener{
        public void onTouchLetterChanged(String s);
    }
    //第二步 声明该接口的引用
    private onTouchLetterListener listener;
    //第三步 设置我们接口添加监听的set方法
    public void setListener(onTouchLetterListener listener){
        this.listener=listener;
    }

    public Seekbar(Context context) {
        super(context);
    }

    public Seekbar(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height=getHeight();
        int width=getWidth();
        int childHeight=height/b.length;//每个元素高度
        for (int i = 0; i <b.length ; i++) {
            paint.setColor(Color.rgb(33,66,77));
            paint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
            paint.setAntiAlias(true);//设置抗锯齿
            paint.setTextSize(40);
            float x=(width-paint.measureText(b[i]))/2;
            float y=childHeight*(i+1);
            if (i==choose){
                paint.setColor(Color.parseColor("#ee99ff"));
            }
            canvas.drawText(b[i],x,y,paint);
            paint.reset();//重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float y=event.getY();
        int index = (int) (y/getHeight()*b.length);
        if (index>=b.length){
            index=b.length-1;
        }else if (index<0){
            index=0;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                choose=-1;
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                if (textView!=null){
                    textView.setVisibility(GONE);
                }
                break;
            default:

                choose=index;
                setBackgroundColor(getResources().getColor(R.color.colorAccent));

                //第四步 设置我们的监听的触发
                if (listener!=null){
                    listener.onTouchLetterChanged(b[index]);
                }


                if (textView!=null){
                    textView.setVisibility(VISIBLE);
                    textView.setText(b[index]);
                }
                invalidate();
                break;

        }
        return true;
    }

    public void setText(TextView textView){
        this.textView=textView;

    }
}
