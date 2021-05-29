package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email_log);
        password=findViewById(R.id.password_log);
        login=findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")||password.getText().toString().equals("")){
                    email.setHint("Null email");
                    password.setHint("Null password");
                }
                else {
                    setLogin(email.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void setLogin(String email,String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        Intent home=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(home);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        final View customLayout=getLayoutInflater().inflate(R.layout.alert_dialog,null);
                        AlertDialog alert=new AlertDialog.Builder(LoginActivity.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                                .setView(customLayout)
                                .show();
                    }
                });
    }
}