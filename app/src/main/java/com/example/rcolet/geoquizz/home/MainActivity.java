package com.example.rcolet.geoquizz.home;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.helper.CSVReaderHelper;
import com.example.rcolet.geoquizz.helper.DDBhelper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private SQLiteDatabase sqlitedb = null;
    private DDBhelper myddb = null;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.TestCSV);
        tv.setMovementMethod(new ScrollingMovementMethod());


        myddb = new DDBhelper(this);
        sqlitedb = myddb.getWritableDatabase();


        /* --- Si la table est vide, on ajoute tout le fichier CSV --- */
        Cursor testEmpty = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
        if(testEmpty.getCount()==0)
        {
            /*
            for(String[] stab : ar)
            {
                addnewEntry(stab[1], stab[2], Long.parseLong(stab[3]), stab[4], "");
            }
            */

            /* --- création de l'ISR pour le CSV reader/parser --- */
            String filename = "pays.csv";

            InputStreamReader isr = null;
            try {
                isr = new InputStreamReader(getAssets().open(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //lecture du CSV
            ArrayList<String[]> ar = CSVReaderHelper.load(isr);

            if(ar!=null && !ar.isEmpty())
            {
                for(String[] stab : ar)
                {
                    addnewEntry(stab[1], stab[2], Long.parseLong(stab[3]), stab[4], "");
                }
            }


        }


        /* --- création de l'ISR pour le CSV reader/parser --- */
        /*
        String filename = "pays.csv";

        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(getAssets().open(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //lecture du CSV
        ArrayList<String[]> ar = CSVReaderHelper.load(isr);
        */

        //if(ar!=null && !ar.isEmpty())
        //{

        /* --- Si la table est vide, on ajoute tout le fichier CSV --- */
            /*
            Cursor testEmpty = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
            if(testEmpty.getCount()==0)
            {
                for(String[] stab : ar)
                {
                    addnewEntry(stab[1], stab[2], Long.parseLong(stab[3]), stab[4], "");
                }
            }
            */

        /* --- Affichage dans le textView tv (select *) --- */
        Cursor answer = sqlitedb.rawQuery("select * from " + DDBhelper.TABLE_NAME/* + " where " + DDBhelper._ID + "='" + 1 + "'"*/,null);

        String reponse = "Nb entries : " + answer.getCount() + "\n";
        while(answer.moveToNext()){

            reponse+=("_ID : "+ answer.getString(0)+"\n");
            reponse+=("CAPITAL : "+ answer.getString(1)+"\n");
            reponse+=("HABNB : "+ answer.getString(2)+"\n");
            reponse+=("DEVISE : "+ answer.getString(3)+"\n");
            reponse+=("DEVISE : "+ answer.getString(4)+"\n\n");
        }

        tv.setText(reponse);

        //}
        //else
        //{
        //    tv.setText("ERROR : " + filename);
        //}


        Toast.makeText(this, "Database name : " + myddb.getDatabaseName(), Toast.LENGTH_LONG).show();
    }

    public boolean addnewEntry(String pays, String capital, long nbhab, String devise, String refimage){
        if( (pays == "") || (capital == "") || (devise == "") )
            return false;
        ContentValues cv = new ContentValues();
        cv.put(DDBhelper.columns[1],pays);
        cv.put(DDBhelper.columns[2],capital);
        cv.put(DDBhelper.columns[3],nbhab);
        cv.put(DDBhelper.columns[4],devise);
        //5 TODO ajout image
        sqlitedb.insert(DDBhelper.TABLE_NAME, null, cv);

        return true;
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

    public void emptyDDB(View view) {
        sqlitedb.execSQL("delete from "+ DDBhelper.TABLE_NAME);
        //sqlitedb.delete(myddb.getDatabaseName(), null, null);
    }

    public void answer4(View view) {
    }

    public void answer3(View view) {
    }

    public void answer2(View view) {
    }

    public void answer1(View view) {
    }
}
