package com.example.rcolet.geoquizz.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

public class CultureQuizzSelectHelper {

    Context context;
    private static SQLiteDatabase sqlitedb = null;
    private static DDBhelper myddb = null;

    private static String answerNum1 = null;
    private static String answerNum2 = null;
    private static String answerNum3 = null;
    private static String answerNum4 = null;

    private static String[] answers = {
        null,
        null,
        null,
        null
    };

    private static int goodanswer = 0;

    private static String[] Part_1 = {
        "Quel est le Pays ",
        "Quel est la Capital ",
        "Quel est le Nombre d'habitant "
    };

    private static String[] Part_2 = {
        "dont le pays est ",
        "dont la capital est ",
        "dont le Nombre d'habitant est "
    };


    public CultureQuizzSelectHelper(Context context) {
        this.context = context;
        myddb = new DDBhelper(this.context);
        sqlitedb = myddb.getReadableDatabase();
    }

    public static String SelectQuestion(){
        if(myddb==null)
        {
            return "";
        }

        Random r = new Random();

        String sRet = "";
        String sAnswer = "";

        Cursor all = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);


        int row = r.nextInt(all.getCount())+1;

        int col1 = 0; // Pays uniquement //TEST

        int col2 = col1;
        while(col2==col1)
        {
            col2 = r.nextInt(3); // Pays, Capital, NbHab
        }

        goodanswer = r.nextInt(4) +1; // [1-4], 0 est réservé pour l'erreur


        Log.i("TAG", "num of random row : " + row);
        Log.i("TAG", "num of random col1 : " + col1);
        Log.i("TAG", "num of random col2 : " + col2);


        int cpt = 0;
        while(cpt!=row)
        {
            all.moveToNext();
            cpt++;
        }
        sAnswer=all.getString(col2+1);//décalage de 1


        if(col1==0)
        {
            sRet+=Part_1[(col1)];


            int randomPick = r.nextInt(all.getCount()) +1;

            for(int i = 0; i<answers.length; i++)
            {
                boolean Taken = true;

                while(Taken)
                {
                    randomPick = r.nextInt(all.getCount()) +1;
                    boolean GoodAnwser = randomPick== row;

                    while(GoodAnwser)
                    {
                        randomPick = r.nextInt(all.getCount()) +1;
                        GoodAnwser = randomPick== row;
                    }

                    Log.i("TAG", "num of random randomPick : " + randomPick);


                    Cursor cu = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
                    int i1 = 0;
                    while(i1!=randomPick)
                    {
                        cu.moveToNext();
                        i1++;
                    }

                    Log.i("TAG", "num of random i1 : " + i1);

                    Taken = Arrays.asList(answers).contains(cu.getString(col1+1));

                    if(!Taken){
                        answers[i]=cu.getString(col1+1);
                    }


                }
            }
            // bonne réponse override
            answers[goodanswer-1]=all.getString(col1+1);


        }

        sRet+=Part_2[col2];

        sRet+=sAnswer + "?";

        return sRet;
    }


    /* --- Getter --- */
    public static String getAnswer1()
    {
        return answerNum1;
    }

    public static String getAnswer2()
    {
        return answerNum2;
    }

    public static String getAnswer3()
    {
        return answerNum3;
    }

    public static String getAnswer4()
    {
        return answerNum4;
    }

    public static String[] getAnswers()
    {
        return answers;
    }

    public static int getGoodAnswer()
    {
        return goodanswer;
    }

}