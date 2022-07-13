package com.example.pomadoroap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;



public class MainActivity extends AppCompatActivity {
    LottieAnimationView imgIconLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgIconLike = findViewById(R.id.animation_view);
    }
    public void imageClick(View v) {
        imgIconLike.playAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Main2HeadActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }
}
