package com.ly.remindme.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ly.remindme.R;
import com.ly.remindme.bean.Note;
import com.ly.remindme.db.dao.NoteDao;
import com.ly.remindme.utils.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button bt;
    ListView lv;
    LayoutInflater inflater;
    ArrayList<Note> array;
    NoteDao mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //  getActionBar().setIcon(R.drawable.icon_app);
        lv=(ListView) findViewById(R.id.listView1);
        bt=(Button) findViewById(R.id.button1);
        inflater=getLayoutInflater();

        mdb=new NoteDao(this);
        array=mdb.getNoteList();
        MyAdapter adapter=new MyAdapter(inflater,array);
        lv.setAdapter(adapter);
		/*
		 * 点击listView里面的item,进入到第二个页面，用来修改日记
		 */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("ids",array.get(position).getIds() );
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
		/*
		 * 长点后来判断是否删除数据
		 */
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                //AlertDialog,来判断是否删除日记。
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("删除")
                        .setMessage("是否删除笔记")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                mdb.delete(array.get(position));
                                array=mdb.getNoteList();
                                MyAdapter adapter=new MyAdapter(inflater,array);
                                lv.setAdapter(adapter);
                            }
                        })
                        .create().show();
                return true;
            }
        });
		/*
		 * 按钮点击事件，用来新建日记
		 */
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.item2:
                this.finish();
                break;
            default:
                break;
        }
        return true;

    }


}