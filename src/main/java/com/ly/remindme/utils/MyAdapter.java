package com.ly.remindme.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ly.remindme.R;
import com.ly.remindme.bean.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lo__oY on 2016/7/9.
 */
public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Note> array;

    public MyAdapter(LayoutInflater inf, ArrayList<Note> arry) {
        this.inflater = inf;
        this.array = arry;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_listview, null);//注意导包，别导系统包
            vh.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.tv1.setText(array.get(position).getTitle());
        vh.tv2.setText(dateFormat(array.get(position).getTime()));
        return convertView;
    }

    public String dateFormat(long time){
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");

        return formatter.format(new Date(time));
    }
    class ViewHolder {
        TextView tv1, tv2;
    }
}
