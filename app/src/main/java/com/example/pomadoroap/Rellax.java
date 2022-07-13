package com.example.pomadoroap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Rellax extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relax);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Rellax.this, Breaking.class);
                startActivity(i);
                finish();
            }
        },6000);
    }

    public void lolClick(View v) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Rellax.this, Breaking.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Wait"+ ("\ud83d\ude01"), Toast.LENGTH_LONG).show();
        //    super.onBackPressed();
    }
}

