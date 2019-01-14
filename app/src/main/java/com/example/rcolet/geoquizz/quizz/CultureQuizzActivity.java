package com.example.rcolet.geoquizz.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.helper.CultureQuizzSelectHelper;
import com.example.rcolet.geoquizz.score.ScoreCultureQuizzActivity;

public class CultureQuizzActivity extends AppCompatActivity {

    public static int counter = 0;
    TextView question;
    CultureQuizzSelectHelper CQSH = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_quizz);


        CQSH = new CultureQuizzSelectHelper(this);

        question = (TextView)findViewById(R.id.question);
        //question.setText("Question " + counter);

        question.setText(CQSH.SelectQuestion());

    }

    protected void answer1(View view) {
        answerAction(view);
    }

    protected void answer2(View view) {
        answerAction(view);
    }

    protected void answer3(View view) {
        answerAction(view);
    }

    protected void answer4(View view) {
        answerAction(view);
    }

    public void answerAction(View view){
        counter++;
        Intent startCultureQuizz = new Intent(this, CultureQuizzActivity.class);
        startCultureQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(startCultureQuizz);
    }
}
