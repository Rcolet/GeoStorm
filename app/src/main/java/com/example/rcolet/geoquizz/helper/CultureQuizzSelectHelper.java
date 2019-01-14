package com.example.rcolet.geoquizz.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

public class CultureQuizzSelectHelper {

    Context context;
    private static SQLiteDatabase sqlitedb = null;
    private static DDBhelper myddb = null;

    public CultureQuizzSelectHelper(Context context) {
        this.context = context;
        myddb = new DDBhelper(this.context);
        sqlitedb = myddb.getWritableDatabase();
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


        int row = r.nextInt(all.getCount()+1);

        int col1 = r.nextInt(3+1); // Pays, Capital, NbHab
        int col2 = col1;
        while(col2==col1)
        {
            col2 = r.nextInt(3+1); // Pays, Capital, NbHab
        }

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

}
