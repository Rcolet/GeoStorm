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
        startCultureQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(startCultureQuizz);
    }

    protected void highScoreCultureQuizz(View view) {
        Intent highScoreCultureQuizz = new Intent(this, ScoreCultureQuizzActivity.class);
        highScoreCultureQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(highScoreCultureQuizz);
    }

    public void exit(View view) {
        finish();
    }
}
