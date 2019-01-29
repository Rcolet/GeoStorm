package com.example.rcolet.geoquizz.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

public class MapQuizzSelectHelper {

    Context context;
    private static SQLiteDatabase sqlitedb = null;
    private static DDBhelper myddb = null;

    public MapQuizzSelectHelper(Context context) {
        this.context = context;
        myddb = new DDBhelper(this.context);
        sqlitedb = myddb.getReadableDatabase();
    }

    public static String SelectQuestion() {
        if (myddb == null) {
            return "";
        }

        String sRet = "";

        Random r = new Random();

        Cursor all = sqlitedb.rawQuery("select * from "+DDBhelper.TABLE_NAME,null);

        int row = r.nextInt(all.getCount())+1;

        int cpt = 0;
        while(cpt!=row)
        {
            all.moveToNext();
            cpt++;
        }
        sRet=all.getString(1);//Pays only

        // TODO : to continue

        return sRet;
    }
}
