package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chitchat.Model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    FirebaseUser user;
    DatabaseReference reference;

    String Username_string;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //back anim grad
        ConstraintLayout layout = findViewById(R.id.cons_lay);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("USERS").child(user.getUid());

        {
            //navigation elements
            NavigationView navigationView = findViewById(R.id.navigation_drawer);
            navigationView.setNavigationItemSelectedListener(item -> {
                // Navigation drawer item click listener
                switch (item.getItemId()) {
                    case R.id.menu_rate:
                        //Replace your own action here
                        Toast.makeText(HomeActivity.this, "Rate", Toast.LENGTH_SHORT).show();
                    case R.id.menu_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(HomeActivity.this,StartActivity.class));
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
            Toolbar toolbar = findViewById(R.id.toolBar);
            drawerLayout = findViewById(R.id.drawer_layout);
            toolbar.setTitle("Chit Chat");

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            View headerView = navigationView.getHeaderView(0);
            TextView name_nav = headerView.findViewById(R.id.username_nav);
            name_nav.setText("USERNAME");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    User user=snapshot.getValue(User.class);
                    Username_string=user.getUsername();
                    Toast.makeText(HomeActivity.this, Username_string, Toast.LENGTH_SHORT).show();
                    View headerView = navigationView.getHeaderView(0);
                    TextView name_nav = headerView.findViewById(R.id.username_nav);
                    name_nav.setText(Username_string);
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }
    }
}