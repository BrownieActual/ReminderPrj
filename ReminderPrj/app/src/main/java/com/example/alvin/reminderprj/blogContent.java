//Displays blog content

package com.example.alvin.reminderprj;

public class blogContent {

    private String title;
    private String description;
    private String img;

    public blogContent(){

    }
    public blogContent(String title, String img, String description){  //recieve information from below functions
        this.title = title;
        this.description = description;
        this.img = img;

    }

    // Get/Set functions for various datas



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
