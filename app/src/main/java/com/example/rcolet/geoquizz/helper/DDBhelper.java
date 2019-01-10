package com.example.rcolet.geoquizz.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DDBhelper extends SQLiteOpenHelper {

    public final static String LOG_TAG = "DB log";
    public final static String DB_NAME = "CarsDB";
    public final static int VERSION = 1;

    //TODO : changer les collones (sauf ID)
    final static String TABLE_NAME = "Pays";
    final static String _ID = "_id";
    final static String CAPITAL = "capital";
    final static String HABNB = "habnb";
    final static String DEVISE = "devise";
    final static String REFIMAGE = "refimage";

    final static String[] columns = {_ID, CAPITAL, HABNB, DEVISE, REFIMAGE};

    Context context;


    public DDBhelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context, "Creation de la table", Toast.LENGTH_LONG).show();

        //TODO : a changer
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAPITAL + " TEXT NOT NULL, "
                + HABNB + " INTEGER NOT NULL, "
                + DEVISE + " TEXT NOT NULL, "
                + REFIMAGE + " TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteDatabase() {
        context.deleteDatabase(DB_NAME);
    }
}

