package com.coors.expensestudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rocker on 2017/11/9.
 */

public class ExpenseHelper extends SQLiteOpenHelper{

    public ExpenseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        getWritableDatabase().execSQL(
                "CREATE TABLE expense" +
                        "(_id INTEGER PRIMARY KEY NOT NULL , " +
                        "cdate DATETIME NOT NULL , " +
                        "info VARCHAR , " +
                        "amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
