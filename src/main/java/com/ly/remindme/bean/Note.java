package com.ly.remindme.bean;

/**
 * Created by Lo__oY on 2016/7/8.
 */
public class Note {
    private int ids;
    private String title;
    private String content;
    private long time;

    public Note() {
        this.setIds(-1);
        this.setTitle(null);
        this.setContent(null);
        this.setTime(-1);
    }

    public Note(String title, int ids, String content, long times) {
        this.setIds(ids);
        this.setTitle(title);
        this.setContent(content);
        this.setTime(times);
    }

    public Note(String title, String content, long times) {
        this.setTitle(title);
        this.setContent(content);
        this.setTime(times);
    }

    @Override
    public String toString() {
        return new String("title:" + this.getTitle() + " content:" + this.getContent()+
                " time"+this.getTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
}
