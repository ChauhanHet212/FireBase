package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.firebase.fragments.AddProductFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigation;
    Toolbar toolbar;

    FirebaseAuth mAuth;
    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        mAuth = FirebaseAuth.getInstance();
        method = StartActivity.preferences.getString("method", "h");

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigation.setCheckedItem(R.id.nav_add_product);
        setFragment(new AddProductFragment());
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_add_product){
                    toolbar.setTitle("Add Product");
                    setFragment(new AddProductFragment());
                } else if (item.getItemId() == R.id.menu_logout){
                    mAuth.signOut();
                    startActivity(new Intent(MainActivity.this, StartActivity.class));
                    finish();
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        View headerView = navigation.getHeaderView(0);
        LinearLayout linear = headerView.findViewById(R.id.linear);
        ImageView img = headerView.findViewById(R.id.profileImg);

        if (method.equals("google")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.green));
            navigation.setBackgroundColor(getResources().getColor(R.color.light_green));
            linear.setBackgroundColor(getResources().getColor(R.color.green));
            img.setImageResource(R.drawable.user3);
        } else if (method.equals("email")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
            navigation.setBackgroundColor(getResources().getColor(R.color.light_blue));
            linear.setBackgroundColor(getResources().getColor(R.color.blue));
            img.setImageResource(R.drawable.user1);
        } else if (method.equals("phone")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.yellow));
            navigation.setBackgroundColor(getResources().getColor(R.color.light_yellow));
            linear.setBackgroundColor(getResources().getColor(R.color.yellow));
            img.setImageResource(R.drawable.user2);
        }
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}