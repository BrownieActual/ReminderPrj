package com.example.alvin.reminderprj;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class blogContent extends AppCompatActivity {

    private String title;
    private String description;
    private Bitmap img;
    private Bitmap emoji;
    public blogContent(){

    }
    public blogContent(String title, String description, Bitmap img, Bitmap emoji){
        this.title = title;
        this.description = description;
        this.img = img;
        this.emoji = emoji;
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
}
