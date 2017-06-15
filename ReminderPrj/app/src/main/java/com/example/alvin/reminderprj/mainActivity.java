package com.example.alvin.reminderprj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mainActivity extends AppCompatActivity {
    public String username = getIntent().getStringExtra("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Welcome to ReminderPrj, " + username);
    }

    public void addActivityIntent(View view){
        Intent intent = new Intent(mainActivity.this, addActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
