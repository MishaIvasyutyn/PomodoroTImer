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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class Timer extends AppCompatActivity {
    private boolean play = true;
    TextView messege;
    private static final long START_TIME_IN_MILLIS = 1500000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button Pause;
    private Button mButtonReset;
    LottieAnimationView officebreak;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        messege = findViewById(R.id.time_for_a_work);
        mTextViewCountDown = findViewById(R.id._25_00__);
        Pause = findViewById(R.id.imageButton2);
        mButtonStartPause = findViewById(R.id.imageButton1);
        mButtonReset = findViewById(R.id.button4);
        mButtonStartPause.setVisibility(View.INVISIBLE);
        officebreak = findViewById(R.id.officebreak);

        Pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Pause.setVisibility(View.VISIBLE);
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
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

    public void sendOnChannel2() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.images)
                .setContentTitle("Pomadoro")
                .setContentText("Your work time is over")
                .setVibrate(new long[]{1000, 1000})
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mButtonStartPause.setVisibility(View.VISIBLE);
                Pause.setVisibility(View.INVISIBLE);
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                MediaPlayer player;
                player = MediaPlayer.create(Timer.this, R.raw.turu);
                player.start();
                Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibr.vibrate(1000);
                sendOnChannel2();
                mTimerRunning = false;
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
                officebreak.setVisibility(View.VISIBLE);
                messege.setText("Time for a break");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(Timer.this, Rellax.class);
                        startActivity(i);
                        finish();
                    }
                }, 12000);
            }
        }.start();

        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        Pause.setVisibility(View.VISIBLE);
        mButtonStartPause.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
//        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    public void next(View view) {
        Toast.makeText(this, "Develop by Silver" + ("\ud83d\ude0a"), Toast.LENGTH_LONG).show();
    }


    public void skip(View view) {
        Intent i = new Intent(Timer.this, Rellax.class);
        startActivity(i);
        finish();//може баг
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "your work time is not over yet " + ("\ud83d\ude04"), Toast.LENGTH_LONG).show();
        //    super.onBackPressed();
    }
}

