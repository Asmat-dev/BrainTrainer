package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class PlayAgainActivity extends AppCompatActivity {


    public void playAgain(View v) {
        again();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Bundle b = getIntent().getExtras();
        assert b != null;
        String obtainedScore = b.getString("obtained");
        String totalScore = b.getString("total");

        TextView obtainedScoreTextView, totalScoreTextView;

        obtainedScoreTextView = findViewById(R.id.obtainedScoreid);
        totalScoreTextView = findViewById(R.id.totalScoreid);

        obtainedScoreTextView.setText(obtainedScore);
        totalScoreTextView.setText(totalScore);
    }

    public void again() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
