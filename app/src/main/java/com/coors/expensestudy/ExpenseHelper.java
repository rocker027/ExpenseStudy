package com.coors.expensestudy;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rocker on 2017/11/9.
 */

public class ExpenseHelper extends SQLiteOpenHelper {
    public static ExpenseHelper instance;
    public static final String DB_NAME = "expense.db";
    public static final int DB_VERSION = 1;
    public static final String TAG = ExpenseHelper.class.getSimpleName();
    private Resources mResources;

    public ExpenseHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
        mResources = context.getResources();
    }

    public static ExpenseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseHelper(context);
        }
        return instance;
    }

    public ExpenseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_SCHEMA.TABLE_NAME +
                        "(" + TABLE_SCHEMA.COL_ID + " INTEGER PRIMARY KEY NOT NULL , " +
                        TABLE_SCHEMA.COL_CDATE + " DATETIME NOT NULL , " +
                        TABLE_SCHEMA.COL_INFO + " VARCHAR , " +
                        TABLE_SCHEMA.COL_AMOUNT + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    static class TABLE_SCHEMA {
        public static final String TABLE_NAME = "exp";
        public static final String COL_ID = "_id";
        public static final String COL_INFO = "info";
        public static final String COL_CDATE = "cdate";
        public static final String COL_AMOUNT = "amount";
    }
}
