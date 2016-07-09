package com.ly.remindme.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ly.remindme.R;
import com.ly.remindme.bean.Note;
import com.ly.remindme.db.dao.NoteDao;

/*
 *用来编辑日记
 *主要包括一个方法，isSave()用来保存数据;
 */
public class SecondActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button bt1;
    NoteDao dao;
    Note note;
    int ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ed1=(EditText) findViewById(R.id.editText1);
        ed2=(EditText) findViewById(R.id.editText2);
        bt1=(Button) findViewById(R.id.button1);
        dao=new NoteDao(this);

        Intent intent=this.getIntent();
        ids=intent.getIntExtra("ids", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if(ids!=0){
            note=dao.getTiandCon(ids);
            ed1.setText(note.getTitle());
            ed2.setText(note.getContent());
        }
        //保存按钮的点击事件，他和返回按钮是一样的功能，所以都调用isSave()方法；
        bt1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isSave();
            }
        });
    }
    /*
     * 返回按钮调用的方法。
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        long times = System.currentTimeMillis();
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(ids!=0){
            Note note=new Note(title,ids, content, times);
            dao.update(note);
            Intent intent=new Intent(SecondActivity.this,MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
        //新建日记
        else{
            if(title.equals("")&&content.equals("")){
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                SecondActivity.this.finish();
            }
            else{
                note=new Note(title,content,times);
                dao.add(note);
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                SecondActivity.this.finish();
            }

        }
    }
    private void isSave(){
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");
        Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
       // String times   =   formatter.format(curDate);
        long times = System.currentTimeMillis();
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(ids!=0){
            note=new Note(title,ids, content, times);
            dao.update(note);
            Intent intent=new Intent(SecondActivity.this,MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
        //新建日记
        else{
            note=new Note(title,content,times);
            dao.add(note);
            Intent intent=new Intent(SecondActivity.this,MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_ativity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "标题："+ed1.getText().toString()+"    内容："+ed2.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }


}
