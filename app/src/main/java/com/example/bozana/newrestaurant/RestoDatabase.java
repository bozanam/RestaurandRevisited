package com.example.bozana.newrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bozana on 04/12/2017.
 */

public class RestoDatabase extends SQLiteOpenHelper{
    private RestoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static RestoDatabase instance;

    public static RestoDatabase getInstance(Context context) {

        if (instance == null) {
            instance = new RestoDatabase(context, "Restodb", null, 3);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Resto (_id INTEGER PRIMARY KEY, title TEXT, price INT, amount INT DEFAULT 1);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "Resto");
        onCreate(db);

    }

    public Cursor selectAll() {

        return getWritableDatabase().rawQuery("SELECT * FROM Resto", new String[]{});

    }

    public void insert(String title, int price){
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("price", price);
        getWritableDatabase().insert("Resto", null, cv);
    }


    public void clear(){
        getWritableDatabase().delete("Resto", null, null);
    }
}
