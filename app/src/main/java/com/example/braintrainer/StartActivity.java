package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {


    //Function to delay a task
    public void delayTask(){

        new Timer().schedule(
                new TimerTask(){

                    @Override
                    public void run(){

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                // Stuff that updates the UI
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                    }

                }, 2500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView appName = findViewById(R.id.AppNametextviewId);
        ImageView appLogo = findViewById(R.id.mainLogoImageId);
        Button playBtn = findViewById(R.id.playButtonId);

//        appName.setX(-1000);
//        appLogo.setY(-2000);


        appName.animate().alpha(1).setDuration(1000);
        appLogo.animate().alpha(1).setDuration(1000);
        playBtn.animate().alpha(1).setDuration(1000);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        appName.animate().translationXBy(1000).setDuration(1000);
//        appLogo.animate().translationYBy(2000).setDuration(1000);
//        delayTask();

    }
}
