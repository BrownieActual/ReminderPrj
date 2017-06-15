package com.example.alvin.reminderprj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "123";
    private EditText emailEdt;
    private EditText passwordEdt;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        setTitle("Welcome");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        emailEdt = (EditText) findViewById(R.id.field_email);
        passwordEdt = (EditText) findViewById(R.id.field_password);
    }
    public void createAccount(View view){
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(startPage.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(startPage.this, "Register Successfully",
                                    Toast.LENGTH_SHORT).show();
                            emailEdt.setText("");
                            passwordEdt.setText("");
                        }

                        // ...
                    }
                });
    }
    public void signIn(View view){
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(startPage.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
//                            Toast.makeText(startPage.this, "Signed In Successfully",
//                                    Toast.LENGTH_SHORT).show();
                            String username = (emailEdt.getText().toString());
                            emailEdt.setText("");
                            passwordEdt.setText("");

                            Intent intent = new Intent(startPage.this, mainActivity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                        }

                        // ...
                    }
                });
    }
    //NOT NEEDED
    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();}
    //NOT NEEDED

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
