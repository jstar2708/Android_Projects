package com.example.cricketcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int currentScore = 0;
    int currentWickets = 0;
    int currentBalls = 0;
    int target = -1;
    int innings = 1;

    public void sixRuns(View view){
        currentScore += 6;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void fiveRuns(View view){
        currentScore += 5;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void fourRuns(View view){
        currentScore += 4;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void threeRuns(View view){
        currentScore += 3;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void twoRuns(View view){
        currentScore += 2;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void oneRuns(View view){
        currentScore += 1;
        currentBalls ++;
        display();
        displayOvers();
        toast(view);
    }
    public void out(View view){
        if(currentWickets == 9){
            currentWickets++;
            currentBalls ++;
            display();
            displayOvers();
            endInnings(view);
            return;
        }
        currentWickets++;
        currentBalls ++;
        display();
        displayOvers();
    }
    public void dot(View view){
        currentBalls ++;
        display();
        displayOvers();
    }
    public void wide(View view){
        currentScore ++;
        display();
        displayOvers();
        toast(view);
    }
    public void noBall(View view){
        currentScore ++;
        display();
        displayOvers();
        toast(view);
    }
    public void reset(View view){
        currentScore = 0;
        currentWickets = 0;
        currentBalls = 0;
        target = -1;
        display();
        displayOvers();
        innings = 1;
        display();
        displayOvers();
    }
    public void endInnings(View view){
        if(innings == 2){
            innings++;
            toast(view);
            reset(view);
            return;
        }
        target = currentScore;
        currentScore = 0;
        currentWickets = 0;
        currentBalls = 0;
        innings = 2;
        display();
        displayOvers();
    }
    private void display(){
        TextView score;
        if(innings == 1){
            score = (TextView) findViewById(R.id.runs1_text_view);
            score.setText(currentScore + "/" + currentWickets);
        }
        else{
            score = (TextView) findViewById(R.id.runs2_text_view);
            score.setText(currentScore + "/" + currentWickets);
        }
    }
    private void displayOvers(){
        TextView score;
        if(innings == 1){
            score = (TextView) findViewById(R.id.balls1_text_view);
            score.setText(currentBalls/6 + "." + currentBalls % 6);
        }
        else{
            score = (TextView) findViewById(R.id.balls2_text_view);
            score.setText(currentBalls/6 + "." + currentBalls % 6);
        }
    }
    private void toast(View view){
        if(target != -1 && currentScore > target){
            Toast.makeText(getApplicationContext(),getString(R.string.team2) +" has won the match",Toast.LENGTH_LONG).show();
            reset(view);
        }
        else if(target != -1 && currentWickets == 10){
            Toast.makeText(getApplicationContext(),getString(R.string.team1) +" has won the match",Toast.LENGTH_LONG).show();
        }
        else if(target != -1 && currentScore < target && innings == 3 ){
            Toast.makeText(getApplicationContext(),getString(R.string.team1) +" has won the match",Toast.LENGTH_LONG).show();
            reset(view);
        }
        return;
    }
}