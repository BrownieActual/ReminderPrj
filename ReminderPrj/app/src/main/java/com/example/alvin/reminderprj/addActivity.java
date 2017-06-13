package com.example.alvin.reminderprj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class addActivity extends AppCompatActivity {

    private String titleString;
    private String descriptionString;
    private Bitmap imgString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add");
    }
    private void add(){

    }
    private void discard(){

    }

    private void toMainActivity(){
        Intent intent = new Intent(addActivity.this, mainActivity.class);
        startActivity(intent);
    }
    private void clearFields(){

    }

}
