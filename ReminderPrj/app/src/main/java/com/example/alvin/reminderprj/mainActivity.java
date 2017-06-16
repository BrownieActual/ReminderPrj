package com.example.alvin.reminderprj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class mainActivity extends AppCompatActivity {
    private String REF = "messages";
    public String username;
    FirebaseListAdapter<blogContent> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        setTitle("Welcome to ReminderPrj, " + username);

        ListView listView = (ListView) findViewById(R.id.activityList);
        adapter = new FirebaseListAdapter<blogContent>(this, blogContent.class, R.layout.activity_blog_content,
                FirebaseDatabase.getInstance().getReference(REF)) {
            @Override
            protected void populateView(View v, blogContent model, int position) {
                TextView title = (TextView)v.findViewById(R.id.title);
                ImageView img = (ImageView) v.findViewById(R.id.img);
                TextView des = (TextView)v.findViewById(R.id.description);

                byte[] decodedString = Base64.decode(model.getImg().getBytes().toString(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                title.setText(model.getTitle());
                des.setText(model.getDescription());
                img.setImageBitmap(bitmap);
                Log.d("TAG", model.getImg().getBytes().toString());
            }

        };
        listView.setAdapter(adapter);
    }
    public void addActivityIntent(View view){
        Intent intent = new Intent(mainActivity.this, addActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
