//Activity is the first page and takes care of login through firebase

package com.example.alvin.reminderprj;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                    String checking = model.getUsercheck();
                if(user.equals(checking)) { //exists

                        TextView title = (TextView) v.findViewById(R.id.title);
                        ImageView img = (ImageView) v.findViewById(R.id.img);
                        TextView des = (TextView) v.findViewById(R.id.description);

                        byte[] decodedString = Base64.decode(model.getImg(), Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        title.setText(model.getTitle());
                        des.setText(model.getDescription());
                        img.setImageBitmap(bitmap);
                        Log.d("TAG", model.getImg());

                }else{ //if doesn't exist
                    Log.d("TAG","no such user found");
                }
            }

        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                blogContent itemRef = adapter.getItem(position);

                CharSequence picker[] = new CharSequence[] {"Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);
                builder.setTitle("Choose an Action");
                builder.setItems(picker, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case 0:{
                                Toast.makeText(mainActivity.this, "0", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case 1:{
                                Toast.makeText(mainActivity.this, "1", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                    }
                });
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
