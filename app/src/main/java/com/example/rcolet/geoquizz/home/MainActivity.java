package com.example.rcolet.geoquizz.home;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcolet.geoquizz.R;
import com.example.rcolet.geoquizz.helper.CSVReaderHelper;
import com.example.rcolet.geoquizz.helper.DDBhelper;

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

        myddb = new DDBhelper(this);
        sqlitedb = myddb.getWritableDatabase();

        ArrayList<String[]> ar = CSVReaderHelper.load("pays.csv");

        if(ar!=null)
        {
            // TODO ecriture via ar (et non lecture)

            /*
            Cursor answer = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);//sqlitedb.query(DDBhelper.TABLE_NAME, DDBhelper.columns,"brand=? and power>?");

            //StringBuffer sbf = new StringBuffer();

            String reponse = "Nb entries : " + answer.getCount() + "\n";
            while(answer.moveToNext()){
                //switch ()

                //sbf.append("Id :"+ answer.getString(0)+"\n");
                //sbf.append("Brand :"+ answer.getString(1)+"\n");
                //sbf.append("Model :"+ answer.getString(2)+"\n");
                //sbf.append("Power :"+ answer.getString(3)+"\n\n");

                reponse+=("_ID : "+ answer.getString(0)+"\n");
                reponse+=("CAPITAL : "+ answer.getString(1)+"\n");
                reponse+=("HABNB : "+ answer.getString(2)+"\n");
                reponse+=("DEVISE : "+ answer.getString(3)+"\n");
                reponse+=("DEVISE : "+ answer.getString(4)+"\n\n");
            }

            tv.setText(reponse);
            */

            //tv.setText("DB name : " + myddb.getDatabaseName());

        }
        else
        {
            tv.setText("ERROR");
        }


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
}
