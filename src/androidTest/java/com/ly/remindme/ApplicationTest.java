package com.ly.remindme;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.test.ApplicationTestCase;

import com.ly.remindme.bean.Note;
import com.ly.remindme.db.dao.NoteDao;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private Context mContext;
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {

        mContext = getContext();
        super.setUp();
    }

    public void testAdd() throws InterruptedException {

               for (int i = 0 ;i < 10 ; i++){
                   Note note = new Note();
                   note.setTitle("this is my "+i+ "title");
                   note.setContent("this is my "+i+ " content");
                   note.setTime(System.currentTimeMillis());
                   NoteDao dao = new NoteDao(mContext);
                   dao.add(note);
                   Thread.sleep(1000);
               }

    }

    public void testFind(){

        int i=0;
        NoteDao dao = new NoteDao(mContext);

        ArrayList<Note> list = dao.getNoteList();
        for (Note note  : list) {

            i++;
            if(i == 6){
                note.setTitle("this is my first update");
                dao.update(note);
            }

            if(i == 7){
                dao.delete(note);
            }
        }

        list = dao.getNoteList();
        for (Note note  : list) {

            System.out.println(note.toString());
        }
    }
}