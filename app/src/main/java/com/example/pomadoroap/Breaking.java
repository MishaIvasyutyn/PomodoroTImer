package com.example.pomadoroap;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;


public class Breaking extends AppCompatActivity {
    MediaPlayer player;
    TextView messege;
    ImageView work;
    public static final String CHANNEL_1_ID = "channel1";
    public static final long START_TIME_IN_MILLIS = 300000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    LottieAnimationView Imgcoinlike;
    LottieAnimationView workslon;
    static int count;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breaak);
        messege = findViewById(R.id.time_for_a_work);
        work = findViewById(R.id.imageView);
        Imgcoinlike = findViewById(R.id.animation_viewww);
        mTextViewCountDown = findViewById(R.id._25_00__);
        mButtonStartPause = findViewById(R.id.button2);
        workslon = findViewById(R.id.workinslon);
        count += 1;
        if (count % 4 == 0) {
            mTextViewCountDown.setText("20:00");
            mTimeLeftInMillis = 1200000;

        } else {
            mTimeLeftInMillis = 300000;
        }
        createNotificationChannels();
        notificationManager = NotificationManagerCompat.from(this);
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

    public void sendOnChannel1() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.images)
                .setContentTitle("Pomadoro")
                .setContentText("Your break is over")
                .setVibrate(new long[]{1000, 1000})
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

    public void lolbreak(View v) {
        {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    Imgcoinlike.setVisibility(View.VISIBLE);
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    MediaPlayer player;
                    player = MediaPlayer.create(Breaking.this, R.raw.turu);
                    player.start();
                    Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibr.vibrate(1000);
                    sendOnChannel1();
                    workslon.setVisibility(View.VISIBLE);
                    work.setImageResource(R.drawable.work100);
                    mTimerRunning = false;
                    mButtonStartPause.setText("Start");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    Imgcoinlike.setVisibility(View.INVISIBLE);
                    messege.setText("Time for a work");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(Breaking.this, Timer.class);
                            startActivity(i);
                            finish();
                        }
                    }, 12000);
                }
            }.start();

            mTimerRunning = true;
            mButtonStartPause.setVisibility(View.INVISIBLE);

        }
    }


    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "your break is not over yet" + ("\ud83d\ude09"), Toast.LENGTH_LONG).show();
        //    super.onBackPressed();
    }
}
