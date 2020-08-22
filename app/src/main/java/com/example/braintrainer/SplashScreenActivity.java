package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView appName = findViewById(R.id.AppNametextviewId);
        ImageView appLogo = findViewById(R.id.mainLogoImageId);
        Button playBtn = findViewById(R.id.playButtonId);


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
    }
}
