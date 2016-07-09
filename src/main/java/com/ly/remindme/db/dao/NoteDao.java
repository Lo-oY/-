package com.ly.remindme.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ly.remindme.bean.Note;

import java.util.ArrayList;

/**
 * Created by Lo__oY on 2016/7/8.
 * 数据库操作类，包括增删改查等操作
 */
public class NoteDao {

    private Context context;
    private MyDatabaseHelper dbHelper;
    public NoteDao(Context context){
        this.context = context;
        dbHelper = new MyDatabaseHelper(context,"mynote");
    }

    /**
     * 得到数据库中的所有便签
     * @return
     */
    public ArrayList<Note> getNoteList(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        ArrayList<Note> lists = new ArrayList<>();
        Cursor cursor = db.query("note", new String[]{"id","title", "content", "time"}, null, null, null, null, null);

        while (cursor.moveToNext()){
            Note note = new Note();
            note.setIds(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setTime(cursor.getLong(3));
            lists.add(note);
        }
        cursor.close();
        db.close();
        return lists;
    }

    /**
     * 在数据库中插入一条记录
     * @param note
     * @return
     */
    public boolean add(Note note){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        values.put("time",note.getTime());
        long rowId = db.insert("note", null, values);
        db.close();
        if (rowId == -1){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 修改一个笔记
     * @param note
     * @return
     */
    public boolean update(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        values.put("time",note.getTime());
        int rows = db.update("note", values, "id=?", new String[]{String.valueOf(note.getIds())});
        db.close();
        if (rows == 0){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除一条备忘录记录
     * @param note
     * @return
     */
    public boolean delete(Note note){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("note", "id=?", new String[]{String.valueOf(note.getIds())});
        db.close();
        if (rows == 0){
            return false;
        } else{
            return true;
        }
    }

    /**
     * 根据id在
     * @param ids
     * @return
     */
    public Note getTiandCon(int ids) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("note", new String[]{"id", "title", "content", "time"}, "id=?", new String[]{String.valueOf(ids)}, null, null, null);
        Note note = new Note();
        if(cursor.moveToNext()){
            note.setIds(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setTime(cursor.getLong(3));
        }
        return note;
    }
}


