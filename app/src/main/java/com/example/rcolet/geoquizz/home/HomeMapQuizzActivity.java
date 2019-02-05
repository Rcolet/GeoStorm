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
        startMapQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(startMapQuizz);
    }

    protected void highScoreMapQuizz(View view) {
        Intent highScoreMapQuizz = new Intent(this, ScoreMapQuizzActivity.class);
        highScoreMapQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(highScoreMapQuizz);
    }

    public void exit(View view) { finish(); }
}
