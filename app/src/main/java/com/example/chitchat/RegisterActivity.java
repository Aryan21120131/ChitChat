package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText username,email,password;
    Button register;

    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);

        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")){
                    username.setHint("Empty username");
                    email.setHint("Empty email");
                    password.setHint("Empty password");
                }
                else {
                    setRegister(username.getText().toString(),email.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    public void setRegister(String username, String email, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    String userID=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference("USERS").child(userID);

                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("ID",userID);
                    hashMap.put("Username",username);
                    hashMap.put("Images","default");

                    reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                            Intent home=new Intent(RegisterActivity.this,HomeActivity.class);
                            startActivity(home);
                            finish();
                        }
                    })
                            .addOnFailureListener(e -> {
                            });
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }
}