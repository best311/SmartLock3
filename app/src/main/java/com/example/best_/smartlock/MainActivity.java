package com.example.best_.smartlock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.ftMain, new HomeFragment());
                    ft1.commit();
                    return true;

                case R.id.navigation_dashboard:
                    android.support.v4.app.FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.ftMain, new RoomFragment());
                    ft2.commit();
                    return true;

                case R.id.navigation_inactive:
                    android.support.v4.app.FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.ftMain, new RoutineFragment());
                    ft3.commit();
                    return true;
                case R.id.navigation_note:
                    android.support.v4.app.FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.ftMain, new NoteFragment());
                    ft4.commit();
                    return true;
                case R.id.navigation_menu:
                    android.support.v4.app.FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                    ft5.replace(R.id.ftMain, new SettingFragment());
                    ft5.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        android.support.v4.app.FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.ftMain, new HomeFragment());
        ft1.commit();
    }}
