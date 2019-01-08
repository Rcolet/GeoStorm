package com.example.rcolet.geoquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    protected void hightScoreCultureQuizz(View view) {
        Intent hightScoreCultureQuizz = new Intent(this, ScoreCultureQuizzActivity.class);
        startActivity(hightScoreCultureQuizz);
    }
}
