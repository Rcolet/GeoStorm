package com.example.rcolet.geoquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Vérification si c'est la bonne vue
    public void startCultureGame(View view) {
        Intent intentCultureQuizz = new Intent(this, HomeCultureQuizzActivity.class);
        startActivity(intentCultureQuizz);
    }

    public void startMapGame(View view) {
        Intent intentMapQuizz = new Intent(this, HomeMapQuizzActivity.class);
        startActivity(intentMapQuizz);
    }

    //sauvegarde de la base de donnée dans le onStop

    public void exitButton(View view) {
        finishAndRemoveTask();
    }
}
