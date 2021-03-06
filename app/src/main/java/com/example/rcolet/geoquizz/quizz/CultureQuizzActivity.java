package com.example.rcolet.geoquizz.quizz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.helper.CultureQuizzSelectHelper;
import com.example.rcolet.geoquizz.score.ScoreCultureQuizzActivity;

public class CultureQuizzActivity extends AppCompatActivity {

    public static int counter = 1;
    public static int goodAnswer = 0;

    public static final int MAXQUESTION = 5;


    TextView TXquestion;
    TextView TXgoodAnswer;

    CultureQuizzSelectHelper CQSH = null;

    Button[] buttons = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_quizz);


        CQSH = new CultureQuizzSelectHelper(this);

        TXquestion = (TextView)findViewById(R.id.question);
        TXgoodAnswer = (TextView)findViewById(R.id.goodanswer);


        buttons = new Button[4];
        buttons[0] = (Button)findViewById(R.id.answer1);
        buttons[1] = (Button)findViewById(R.id.answer2);
        buttons[2] = (Button)findViewById(R.id.answer3);
        buttons[3] = (Button)findViewById(R.id.answer4);


        TXquestion.setText(" Question n°" + counter + " (" + goodAnswer + "): " + CQSH.SelectQuestion());
        TXgoodAnswer = (TextView)findViewById(R.id.goodanswer);


        buttons[0].setText(CQSH.getAnswers()[0]);
        buttons[1].setText(CQSH.getAnswers()[1]);
        buttons[2].setText(CQSH.getAnswers()[2]);
        buttons[3].setText(CQSH.getAnswers()[3]);




    }

    protected void answer1(View view) throws InterruptedException {

        String str = Integer.toString(CQSH.getGoodAnswer());

        if(str.charAt(str.length() - 1) == '1')
        {
            goodAnswer++;
        }

        answerAction(view);
    }

    protected void answer2(View view) throws InterruptedException {

        String str = Integer.toString(CQSH.getGoodAnswer());

        if(str.charAt(str.length() - 1) == '2')
        {
            goodAnswer++;
        }

        answerAction(view);
    }

    protected void answer3(View view) throws InterruptedException {

        String str = Integer.toString(CQSH.getGoodAnswer());

        if(str.charAt(str.length() - 1) == '3')
        {
            goodAnswer++;
        }

        answerAction(view);
    }

    protected void answer4(View view) throws InterruptedException {

        String str = Integer.toString(CQSH.getGoodAnswer());

        if(str.charAt(str.length() - 1) == '4')
        {
            goodAnswer++;
        }

        answerAction(view);
    }

    public void answerAction(View view) throws InterruptedException {
        counter++;

        TXgoodAnswer.setText("La bonne réponse été : " + CQSH.getAnswers()[CQSH.getGoodAnswer() -1] + " (" + Integer.toString(CQSH.getGoodAnswer()) + ")" );

        for(Button b : buttons)
        {
            b.setTextColor(Color.RED);
            b.setEnabled(false);

        }


        buttons[CQSH.getGoodAnswer() - 1].setTextColor(Color.GREEN);


        final Context currentcontext = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                if(counter>MAXQUESTION)
                {
                    Intent startCultureQuizz = new Intent(currentcontext, ScoreCultureQuizzActivity.class);
                    startCultureQuizz.putExtra("score1", goodAnswer );

                    counter = 1;
                    goodAnswer = 0;

                    startCultureQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                    startActivity(startCultureQuizz);
                }
                else
                {
                    Intent startCultureQuizz = new Intent(currentcontext, CultureQuizzActivity.class);
                    startCultureQuizz.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(startCultureQuizz);
                }



            }
        }, 2000);

    }
}
