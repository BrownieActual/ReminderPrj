//Activity is the first page and takes care of login through firebase

package com.example.alvin.reminderprj;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainActivity extends AppCompatActivity {
    public String username;
    public String userUid;
    FirebaseListAdapter<blogContent> adapter; //Declare connection to firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Bsically the main page

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         username = getIntent().getStringExtra("username");
        userUid = getIntent().getStringExtra("userUid");
        setTitle("Welcome, " + username);
        final String user = username;
        final ListView listView = (ListView) findViewById(R.id.activityList);
        adapter = new FirebaseListAdapter<blogContent>(this, blogContent.class, R.layout.activity_blog_content, //connects to firebase
                FirebaseDatabase.getInstance().getReference(userUid)) { //Pulls user ID from firebase
            @Override
            protected void populateView(View v, blogContent model, int position) { //checks if user exists in database
                            TextView title = (TextView) v.findViewById(R.id.title);
                            title.setText(model.getTitle());

                            TextView des = (TextView) v.findViewById(R.id.description);
                            des.setText(model.getDescription());

                            ImageView img = (ImageView) v.findViewById(R.id.img);
                            byte[] decodedString = Base64.decode(model.getImg(), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            img.setImageBitmap(bitmap);
            }

        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    // itemRef is position of item clicked
                final DatabaseReference itemRef = adapter.getRef(position);

//                CharSequence picker[] = new CharSequence[] {"Edit", "Delete"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);
                builder.setTitle("Delete the file");
                builder.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemRef.removeValue();
                    }
                    })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                builder.show();
            }
        });
    }

    public void addActivityIntent(View view){ //get user and password and sends screen to addactivity
        Intent intent = new Intent(mainActivity.this, addActivity.class);  //declares destination
        intent.putExtra("username", username);
        intent.putExtra("userUid", userUid);
        startActivity(intent);  //sends user
    }

}
