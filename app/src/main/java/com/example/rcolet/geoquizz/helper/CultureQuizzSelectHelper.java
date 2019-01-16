package com.example.rcolet.geoquizz.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.Random;

public class CultureQuizzSelectHelper {

    Context context;
    private static SQLiteDatabase sqlitedb = null;
    private static DDBhelper myddb = null;

    private static String answerNum1 = null;
    private static String answerNum2 = null;
    private static String answerNum3 = null;
    private static String answerNum4 = null;
    private static int goodanswer = 0;


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

        //int col1 = r.nextInt(3); // Pays, Capital, NbHab
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


        //TODO
        //Cursor answer = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);


        int cpt = 0;
        while(cpt!=row)
        {
            all.moveToNext();
            cpt++;
        }
        sAnswer=all.getString(col2+1);//décalage de 1



        if(col1==0)
        {
            sRet+="Quel est le Pays ";

            int randomPick = r.nextInt(all.getCount()) +1;
            /*
            for(int i = 0; i<4; i++)
            {
                if(i+1 != goodanswer)
                {
                    //...
                }
                else
                {
                    if(goodanswer==1)
                    {
                        answerNum1=all.getString(col1);
                    }
                    if(goodanswer==2)
                    {
                        answerNum2=all.getString(col1);
                    }
                    if(goodanswer==3)
                    {
                        answerNum3=all.getString(col1);
                    }
                    if(goodanswer==4)
                    {
                        answerNum4="";
                    }
                }
            }
            */

            // 1
            while(randomPick==row)
            {
                randomPick = r.nextInt(all.getCount()) +1;
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


            answerNum1=cu.getString(col1+1);

            // 2
            while(randomPick==row || randomPick==i1)
            {
                randomPick = r.nextInt(all.getCount()) +1;
            }

            cu = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
            int i2 = 0;
            while(i2!=randomPick)
            {
                cu.moveToNext();
                i2++;
            }

            Log.i("TAG", "num of random i2 : " + i2);

            answerNum2=cu.getString(col1+1);

            // 3
            while(randomPick==row || randomPick==i1 || randomPick==i2)
            {
                randomPick = r.nextInt(all.getCount()) +1;
            }

            cu = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
            int i3 = 0;
            while(i3!=randomPick)
            {
                cu.moveToNext();
                i3++;
            }

            Log.i("TAG", "num of random i3 : " + i3);

            answerNum3=cu.getString(col1+1);

            // 4
            while(randomPick==row || randomPick==i1 || randomPick==i2 || randomPick==i3)
            {
                randomPick = r.nextInt(all.getCount()) +1;
            }

            cu = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);
            int i4 = 0;
            while(i4!=randomPick)
            {
                cu.moveToNext();
                i4++;
            }

            Log.i("TAG", "num of random i4 : " + i4);


            answerNum4=cu.getString(col1+1);


            if(goodanswer==1)
            {
                answerNum1=all.getString(col1+1);
            }
            if(goodanswer==2)
            {
                answerNum2=all.getString(col1+1);
            }
            if(goodanswer==3)
            {
                answerNum3=all.getString(col1+1);
            }
            if(goodanswer==4)
            {
                answerNum4=all.getString(col1+1);
            }

            Log.i("TAG", "GoodAnswer : " + goodanswer);


        }
        if(col1==1)
        {
            sRet+="Quel est la Capital ";

        }
        if(col1==2)
        {
            sRet+="Quel est le Nombre d'habitant ";
        }



        if(col2==0)
        {
            sRet+="dont le pays est ";
        }
        if(col2==1)
        {
            sRet+="dont la capital est ";

        }
        if(col2==2)
        {
            sRet+="dont le Nombre d'habitant est ";
        }

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

    public static int getGoodAnswer()
    {
        return goodanswer;
    }

}
