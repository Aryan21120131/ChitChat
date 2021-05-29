package com.example.chitchat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onStart() {
        super.onStart();

        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(StartActivity.this,HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //motion gradient background
        ConstraintLayout layout=findViewById(R.id.con_lay);
        AnimationDrawable animationDrawable=(AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Button login = findViewById(R.id.login_home);
        Button register = findViewById(R.id.register_home);

        //Check if user already loged in
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null){
//            Intent home=new Intent(StartActivity.this,HomeActivity.class);
//            startActivity(home);
//            finish();
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_home=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(login_home);
            }
        });
        register.setOnClickListener(v -> {
            Intent register_home =new Intent(StartActivity.this, RegisterActivity.class);
            startActivity(register_home);

        });
    }
}