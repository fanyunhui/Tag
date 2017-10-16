package com.example.andy.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Author: fyh
 * Date: 2017/10/16 15:47
 * Email: 3122156086@qq.com
 * Des:
 */

public class TestAdapter extends BaseAdapter {
    public LayoutInflater mInflater;
    public List<String> data;

    public TestAdapter(Context context) {
        mInflater= LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder.tv=convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(data.get(position));
        return null;
    }

    class ViewHolder{
        TextView tv;
    }


    public List<String> setData(List<String> data1){
        this.data=data1;
        return data;
    }

}
