package com.example.alvin.reminderprj;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class addActivity extends AppCompatActivity {

    private static final int UPLOAD_PHOTO = 100;
    private static final int TAKE_PHOTO = 200;
    private String imageB64;
    private Bitmap emojiString;
    private long dateString;
    public String username;
    public String userUid;
//    private String REF;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add");

        username = getIntent().getStringExtra("username");
        userUid = getIntent().getStringExtra("userUid");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


    }

    public void add(View view) {

        EditText title = (EditText) findViewById(R.id.editTitle);
        EditText description = (EditText) findViewById(R.id.editDescription);
         String titleMessage = title.getText().toString();
        //image64 = String of image
         String descripMessage = description.getText().toString();


        blogContent chat = new blogContent(titleMessage, imageB64, descripMessage, username);
        FirebaseDatabase.getInstance().getReference(userUid).push().setValue(chat);

        toMainActivity();
        clearFields();
    }
    public void setHappy(View view){
        ImageView image = (ImageView) findViewById(R.id.happyIcon);
        image.buildDrawingCache();
        emojiString = image.getDrawingCache();
    }
    public void setNeut(View view){
        ImageView image = (ImageView) findViewById(R.id.neutIcon);
        image.buildDrawingCache();
        emojiString = image.getDrawingCache();
    }
    public void setSad(View view){
        ImageView image = (ImageView) findViewById(R.id.sadIcon);
        image.buildDrawingCache();
        emojiString = image.getDrawingCache();
    }

    public void discard(View view) {
        toMainActivity();
        clearFields();
    }

    private void toMainActivity() {
        this.finish();
    }

    private void clearFields() {
        EditText title = (EditText) findViewById(R.id.editTitle);
        title.setText("");

        ImageView image = (ImageView) findViewById(R.id.editImage);
        image.setImageResource(R.drawable.camera);

        EditText description = (EditText) findViewById(R.id.editDescription);
        description.setText("");
    }

    public void SelectImage(View view) {
        CharSequence picker[] = new CharSequence[] {"Upload a photo", "Take a photo"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an Action");
        builder.setItems(picker, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch(which){
                    case 0:{
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(intent, UPLOAD_PHOTO);
                        }
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(intent, TAKE_PHOTO);
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        upload photo
        if (requestCode == UPLOAD_PHOTO && resultCode == RESULT_OK) {
                Uri uri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ImageView imageView = (ImageView) findViewById(R.id.editImage);
                    imageView.setImageBitmap(bitmap);

                    getImageData(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
//        take photo
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            Bitmap bitmap = (Bitmap) extra.get("data");
            ImageView imageView = (ImageView) findViewById(R.id.editImage);
            imageView.setImageBitmap(bitmap);
            getImageData(bitmap);
        }
    }

    public String getImageData(Bitmap bmp) {
            //convert bitmap to base64 to store in database
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bao); // bmp is bitmap from user image file
        byte[] byteArray = bao.toByteArray();
         imageB64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("TAG", imageB64);

        //convert back to bitmap
//        byte[] decodedString = Base64.decode(imageB64, Base64.DEFAULT);
//         decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


//        Toast toast = Toast.makeText(getApplicationContext(),,Toast.LENGTH_SHORT);
//        toast.show();

        return imageB64;
    }

}

