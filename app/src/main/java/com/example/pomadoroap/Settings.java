package com.example.pomadoroap;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Settings.this, Timer.class);
        startActivity(i);
    }


}