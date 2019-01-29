package com.example.rcolet.geoquizz.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rcolet.geoquizz.quizz.MapQuizzActivity;
import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.score.ScoreMapQuizzActivity;

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

    protected void highScoreMapQuizz(View view) {
        Intent hightScoreMapQuizz = new Intent(this, ScoreMapQuizzActivity.class);
        startActivity(hightScoreMapQuizz);
    }

    public void exit(View view) { finish(); }
}
