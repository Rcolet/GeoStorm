package com.example.rcolet.geoquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeMapQuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_map_quizz);
    }

    protected void startGameMapQuizz(View view) {
        Intent startMapQuizz = new Intent(this, MapQuizzActivity.class);
        startActivity(startMapQuizz);
    }

    protected void hightScoreMapQuizz(View view) {
        Intent hightScoreMapQuizz = new Intent(this, ScoreMapQuizzActivity.class);
        startActivity(hightScoreMapQuizz);
    }
}
