package com.example.alvin.reminderprj;

import android.graphics.Bitmap;

public class blogContent {

    private String title;
    private String description;
    private String img;
    private String usercheck;
    private Bitmap emoji;
    private long date;
    public blogContent(){

    }
    public blogContent(String title, String img, String description, String usercheck){
        this.title = title;
        this.description = description;
        this.img = img;
        this.usercheck = usercheck;
        this.emoji = emoji;
        this.date = date;
    }

    public void setUsercheck(String usercheck) {
        this.usercheck = usercheck;
    }

    public String getUsercheck() {
        return usercheck;

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }
    public void  setImg(String img) {
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
