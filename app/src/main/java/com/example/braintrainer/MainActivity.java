package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView equationTextView, obtainedScoreTextView, correctQScoreTextView, timeLeft;
    private Button btn0, btn1, btn2, btn3;
    private ImageView tickImage;

    String equation;
    String signOfEquation;
    int obtainedScore = 0;
    int totalScore = 0;
    int locationOfCorrectAnswer;
    MediaPlayer mediaPlayer;

    private ArrayList<Integer> answers = new ArrayList<>();

    //Function to Play the game Again
    public void playAgain(){

//        mediaPlayer.start();
        newQuestion();
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                String timeLeftString = millisUntilFinished / 1000 + "s";
                if (millisUntilFinished / 1000 <=5){
                    timeLeft.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                timeLeft.setText(timeLeftString);
            }
            @Override
            public void onFinish() {
                mediaPlayer.stop();
                timeLeft.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));
                delayTask();

                Intent intent = new Intent(getApplicationContext(), PlayAgainActivity.class);
                intent.putExtra("obtained", String.format(Locale.getDefault(), "%d", obtainedScore));
                intent.putExtra("total", String.format(Locale.getDefault(), "%d", totalScore));
                startActivity(intent);
                finish();
            }
        }.start();

        totalScore = 0;
        obtainedScore = 0;


    }

    //Function to update UI Elements
    public void updateUIElements(){

        btn0.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));
        btn1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));
        btn2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));
        btn3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));

        equationTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.slategrey));
        tickImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_tick));

        correctQScoreTextView.animate().alpha(0).setDuration(600);

    }

    //Delay the task
    public void delayTask(){

        new Timer().schedule(
                new TimerTask(){

                    @Override
                    public void run(){
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                updateUIElements();
                                newQuestion();

                            }
                        });
                    }

                }, 600);
    }

    //Function to generate new question
    public void newQuestion(){
        //Random Integers generated
        Random rand = new Random();
        int a, b;

        signOfEquation = getRandomSign();

        switch (signOfEquation) {
            case "+":

                a = rand.nextInt(21);
                b = rand.nextInt(21);
                equation = a + " + " + b + " = ?";
                generateArrayList(a, b, signOfEquation);

                break;
            case "-":

                a = rand.nextInt(21);
                b = rand.nextInt(15);
                while ( a == 0 || a < b ){

                    a = rand.nextInt(21);
                }
                equation = a + " - " + b + " = ?";
                generateArrayList(a, b, signOfEquation);

                break;
            case "×":

                a = rand.nextInt(15);
                b = rand.nextInt(9);
                equation = a + " × " + b + " = ?";
                generateArrayList(a, b, signOfEquation);

                break;
            case "÷":

                a = Integer.parseInt(getValuesForDivisionA(2));
                b = Integer.parseInt(getValuesForDivisionB(1));
                while (a % b != 0) {
                    a = Integer.parseInt(getValuesForDivisionA(2));
                    b = Integer.parseInt(getValuesForDivisionB(1));
                }
                equation = a + " ÷ " + b + " = ?";
                generateArrayList(a, b, signOfEquation);

                break;
        }

        equationTextView.setText(equation);
        btn0.setText(String.format(Locale.getDefault(), "%d", answers.get(0)));
        btn1.setText(String.format(Locale.getDefault(), "%d", answers.get(1)));
        btn2.setText(String.format(Locale.getDefault(), "%d", answers.get(2)));
        btn3.setText(String.format(Locale.getDefault(), "%d", answers.get(3)));

    }


    public void generateArrayList(int a, int b, String sign){

        //Random Integers generated
        Random rand = new Random();
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();        //To clear the Array list before inserting values
        for (int i = 0; i < 4; i++){

            if (i == locationOfCorrectAnswer){

                switch (sign){

                    case "+":
                        answers.add(a+b);
                        break;

                    case "-":
                        answers.add(a-b);
                        break;

                    case "×":
                        answers.add(a*b);
                        break;

                    case "÷":
                        answers.add(a/b);
                        break;
                }

            } else {

                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
    }

    //Generates random sign
    public static String getRandomSign(){

        final String signs = "+-×÷+-×÷";
        Random random = new Random();

        return String.valueOf(signs.charAt(random.nextInt(signs.length())));
    }

    //Generates random sign
    public static String getValuesForDivisionA(int i){

        final String valueforDivision = "1122334455665544332211";
        StringBuilder result = new StringBuilder();

        while (i > 0){
            Random random = new Random();
            result.append(valueforDivision.charAt(random.nextInt(valueforDivision.length())));
            i--;
        }

        return result.toString();
    }

    //Generates random sign
    public static String getValuesForDivisionB(int i){

        final String valueforDivision = "2233445566778899";
        StringBuilder result = new StringBuilder();

        while (i > 0){
            Random random = new Random();
            result.append(valueforDivision.charAt(random.nextInt(valueforDivision.length())));
            i--;
        }

        return result.toString();
    }


    //To Check Correct Answer
    public void chooseAnswer(View view){

        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){

            view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.green));
            equationTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
            tickImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_tickgreen));
            correctQScoreTextView.animate().alpha(1).setDuration(500);
            delayTask();

            obtainedScore = obtainedScore + 40;

        } else {

            view.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.red));
            equationTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
            tickImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_tickred));
            delayTask();
        }
        totalScore = totalScore + 40;
        obtainedScoreTextView.setText(String.format(Locale.getDefault(), "%d", obtainedScore));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

//        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.clocktick);

        //Getting Views
        equationTextView = findViewById(R.id.equationtextid);
        obtainedScoreTextView = findViewById(R.id.obtainedpointsid);
        correctQScoreTextView = findViewById(R.id.correctquestionscore);
        timeLeft = findViewById(R.id.timeleftid);

        tickImage = findViewById(R.id.tickimageid);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        playAgain();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("asdsdd", "onPause: ");
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.clocktick);
        }
        Log.i("asdsdd", "onResume: ");
//        assert mediaPlayer != null;
        mediaPlayer.start();
    }
}
