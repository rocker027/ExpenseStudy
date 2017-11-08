package com.coors.expensestudy;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by z8v on 2017/11/7.
 */

public class SharedPrefHandler {
    private static SharedPrefHandler ourInstance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String PREF_XML_NAME = "expense";

    static SharedPrefHandler getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPrefHandler(context);
        }
        return ourInstance;
    }

    private SharedPrefHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_XML_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
