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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class mainActivity extends AppCompatActivity {
    public String username;
    public String userUid;
    FirebaseListAdapter<blogContent> adapter;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         username = getIntent().getStringExtra("username");

        userUid = getIntent().getStringExtra("userUid");

        setTitle("Welcome to ReminderPrj, " + username);
        final String user = username;

        final ListView listView = (ListView) findViewById(R.id.activityList);
        adapter = new FirebaseListAdapter<blogContent>(this, blogContent.class, R.layout.activity_blog_content,
                FirebaseDatabase.getInstance().getReference(userUid)) {
            @Override
            protected void populateView(View v, blogContent model, int position) {
                    String checking = model.getUsercheck();
                if(user.equals(checking)) {

                        TextView title = (TextView) v.findViewById(R.id.title);
                        ImageView img = (ImageView) v.findViewById(R.id.img);
                        TextView des = (TextView) v.findViewById(R.id.description);

                        byte[] decodedString = Base64.decode(model.getImg(), Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        title.setText(model.getTitle());
                        des.setText(model.getDescription());
                        img.setImageBitmap(bitmap);
                        Log.d("TAG", model.getImg());

                }else{
                    Log.d("TAG","no user i sud");
                }
            }

        };
        listView.setAdapter(adapter);
    }
    public void addActivityIntent(View view){
        Intent intent = new Intent(mainActivity.this, addActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }

}
