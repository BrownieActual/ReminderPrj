package com.example.alvin.reminderprj;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

public class blogContent extends AppCompatActivity {

    private String title;
    private String description;
    private Bitmap img;
    private Bitmap emoji;
    private long date;
    public blogContent(){

    }
    public blogContent(String title, String description, Bitmap img, Bitmap emoji, long date){
        this.title = title;
        this.description = description;
        this.img = img;
        this.emoji = emoji;
        this.date = date;
    }

    public String getBlogTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogDescription() {
        return description;
    }

    public void setBlogDescription(String description) {
        this.description = description;
    }

    public Bitmap getBlogImg() {
        return img;
    }

    public void  setBlogImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getEmoji(){
        return emoji;
    }

    public void setEmoji(Bitmap emoji){
        this.emoji=emoji;
    }

    public long getDate(){
        return date;
    }
    public void setDate(long date){
        this.date = date;
    }
}
