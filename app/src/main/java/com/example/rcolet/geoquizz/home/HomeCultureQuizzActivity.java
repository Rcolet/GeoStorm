package com.example.rcolet.geoquizz.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rcolet.geoquizz.quizz.CultureQuizzActivity;
import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.score.ScoreCultureQuizzActivity;

public class HomeCultureQuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_culture_quizz);
    }

    protected void startGameCultureQuizz(View view) {
        Intent startCultureQuizz = new Intent(this, CultureQuizzActivity.class);
        startActivity(startCultureQuizz);
    }

    protected void highScoreCultureQuizz(View view) {
        Intent hightScoreCultureQuizz = new Intent(this, ScoreCultureQuizzActivity.class);
        startActivity(hightScoreCultureQuizz);
    }
}
