package com.example.rcolet.geoquizz.score;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rcolet.geoquizz.R;

import java.util.Map;

public class ScoreCultureQuizzActivity extends AppCompatActivity {

    TextView TXscore = null;
    TextView Leaderboard = null;

    EditText input;

    SharedPreferences sharedPref;

    int testScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_culture_quizz);

        Intent intent = getIntent();
        testScore = intent.getIntExtra("score1", testScore);

        input = new EditText(this);
        input.setHint("Username");
        input.setMaxLines(1);
        input.setLines(1);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        sharedPref = this.getSharedPreferences(
                "TestSP", this.MODE_PRIVATE);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        TXscore = (TextView)findViewById(R.id.score1);
        Leaderboard = (TextView)findViewById(R.id.leaderboard1);
        Leaderboard.setMovementMethod(new ScrollingMovementMethod());


        //TEST

        if(testScore > 0)
        {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Enregistrer le score?")
                    .setMessage("Voulez-vous enregistrer votre score?")
                    .setView(input)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete

                            String s = input.getText().toString();

                            if(s.length()<1)
                            {
                                s = "User";
                            }

                            TXscore.setText("Score : " + /*666*/ testScore  + " (" + s + ")");

                            SharedPreferences.Editor editor = sharedPref.edit();

                            editor.putString("c" + s, testScore + "");
                            editor.commit();

                            String sss = "SCORES :\n\n";

                            Map<String, ?> allEntries = sharedPref.getAll();
                            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                                Log.i("map values", entry.getKey() + ": " + entry.getValue().toString());

                                if(entry.getKey().charAt(0)=='c')
                                {
                                    sss+=entry.getKey().replaceFirst("c", "") + ":" + entry.getValue().toString() + "\n";
                                }

                            }

                            Leaderboard.setText(sss);
                            testScore=0;



                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing

                            String sss = "SCORES :\n\n";


                            Map<String, ?> allEntries = sharedPref.getAll();
                            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                                Log.i("map values", entry.getKey() + ": " + entry.getValue().toString());

                                if(entry.getKey().charAt(0)=='c')
                                {
                                    sss+=entry.getKey().replaceFirst("c", "") + ":" + entry.getValue().toString() + "\n";
                                }

                            }

                            Leaderboard.setText(sss);
                            testScore=0;



                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        String sss = "SCORES :\n\n";

        Map<String, ?> allEntries = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.i("map values", entry.getKey() + ": " + entry.getValue().toString());

            if(entry.getKey().charAt(0)=='c')
            {
                sss+=entry.getKey().replaceFirst("c", "") + ":" + entry.getValue().toString() + "\n";
            }

        }

        Leaderboard.setText(sss);


    }

}
