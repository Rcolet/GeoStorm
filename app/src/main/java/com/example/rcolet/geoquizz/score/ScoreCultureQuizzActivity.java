package com.example.rcolet.geoquizz.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rcolet.geoquizz.R;

public class ScoreCultureQuizzActivity extends AppCompatActivity {

    TextView TXscore = null;

    int testScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_culture_quizz);

        Intent intent = getIntent();
        testScore = intent.getIntExtra("score1", testScore);


        TXscore = (TextView)findViewById(R.id.score1);

        TXscore.setText("Score : " + testScore);


    }

}
