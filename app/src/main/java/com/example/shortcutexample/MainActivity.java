package com.example.shortcutexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    public static String FRAGMENTTWO = "com.example.shortcutexample.MainActivity.FRAGMENTTWO";
    public static String FRAGMENTTHREE = "com.example.shortcutexample.MainActivity.FRAGMENTTHREE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        if (MainActivity.this.getIntent().getAction() != null) {
            if(FRAGMENTTWO.equals(MainActivity.this.getIntent().getAction())){
                ft=fm.beginTransaction();
                ft.replace(R.id.myFrame,new FragmentTwo());
                ft.commit();
            }else if(FRAGMENTTHREE.equals(MainActivity.this.getIntent().getAction())){
                ft=fm.beginTransaction();
                ft.replace(R.id.myFrame,new FragmentThree());
                ft.commit();
            }else{
                ft=fm.beginTransaction();
                ft.replace(R.id.myFrame,new FragmentOne());
                ft.commit();
            }
        }else{
            ft=fm.beginTransaction();
            ft.replace(R.id.myFrame,new FragmentOne());
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fManager=MainActivity.this.getSupportFragmentManager();
        Fragment f = fManager.findFragmentById(R.id.myFrame);
        if(f instanceof FragmentTwo){
            ft=fm.beginTransaction();
            ft.replace(R.id.myFrame,new FragmentOne());
            ft.commit();
        }else if(f instanceof FragmentThree){
            ft=fm.beginTransaction();
            ft.replace(R.id.myFrame,new FragmentOne());
            ft.commit();
        }else{
            super.onBackPressed();
        }

    }
}
