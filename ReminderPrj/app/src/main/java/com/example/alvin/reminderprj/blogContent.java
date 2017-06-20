//Displays blog content

package com.example.alvin.reminderprj;

import android.graphics.Bitmap;

public class blogContent {

    private String title;
    private String description;
    private String img;
    private String usercheck;

    public blogContent(){

    }
    public blogContent(String title, String img, String description, String usercheck){  //recieve information from below functions
        this.title = title;
        this.description = description;
        this.img = img;
        this.usercheck = usercheck;

    }


    // Get/Set functions for various datas

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




}
