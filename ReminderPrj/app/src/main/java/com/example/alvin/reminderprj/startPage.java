package com.example.alvin.reminderprj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class startPage extends AppCompatActivity {

    public String Username;
    public String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        setTitle("Welcome");


    }
}
