package com.example.alvin.reminderprj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class addActivity extends AppCompatActivity {

    private String titleString;
    private String descriptionString;
    private ImageView imgString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
}
