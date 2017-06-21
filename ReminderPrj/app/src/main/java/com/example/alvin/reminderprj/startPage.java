package com.example.alvin.reminderprj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private String userUid;


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
                Button signinBtn = (Button) findViewById(R.id.email_sign_in_button);
                Button signOutBtn = (Button) findViewById(R.id.email_sign_out_button);
                Button creataccBtn = (Button) findViewById(R.id.email_create_account_button);
                Button startProgram = (Button) findViewById(R.id.start_program);
                TextView hi = (TextView) findViewById(R.id.hi_username);
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                            Toast.makeText(startPage.this, "Signed In Successfully",
//                                    Toast.LENGTH_SHORT).show();
                    username = user.getEmail();
                    userUid = user.getUid();
                    Toast.makeText(getApplicationContext(),"Signin successfully", Toast.LENGTH_SHORT).show();
                    emailEdt.setText("");
                    passwordEdt.setText("");
                    signinBtn.setVisibility(View.GONE);
                    creataccBtn.setVisibility(View.GONE);
                    signOutBtn.setVisibility(View.VISIBLE);
                    startProgram.setVisibility(View.VISIBLE);
                    hi.setText("Hi, " + username);


                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getApplicationContext(),"Signout successfully", Toast.LENGTH_SHORT).show();
                    signinBtn.setVisibility(View.VISIBLE);
                    creataccBtn.setVisibility(View.VISIBLE);
                    signOutBtn.setVisibility(View.GONE);
                    startProgram.setVisibility(View.GONE);
                    hi.setText("Please sign in or create an account.");

                }
                // ...
            }
        };
        emailEdt = (EditText) findViewById(R.id.field_email);
        passwordEdt = (EditText) findViewById(R.id.field_password);

        emailEdt.setText("a@hotmail.com");
        passwordEdt.setText("123456");
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
                        }

                        // ...
                    }
                });
    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
    }
    public void startProgram(View view){
        Intent intent = new Intent(startPage.this, mainActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("userUid",userUid);
        startActivity(intent);
    }
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
