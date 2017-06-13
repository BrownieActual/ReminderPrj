package com.example.alvin.reminderprj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class addActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private static final int MY_REQUEST = 100;
    private String titleString;
    private String descriptionString;
    private Bitmap imgString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add");
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    private void add() {

    }

    private void discard() {

    }

    private void toMainActivity() {
        Intent intent = new Intent(addActivity.this, mainActivity.class);
        startActivity(intent);
    }

    private void clearFields() {

    }

    public void SelectImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, MY_REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extra = data.getExtras();
        Bitmap bitmap = (Bitmap) extra.get("data");
        ImageView imageView = (ImageView) findViewById(R.id.editImage);
        imageView.setImageBitmap(bitmap);
    }
}

