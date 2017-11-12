package com.coors.expensestudy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by z8v on 2017/11/7.
 */

public class SharedPrefHandler {
    private static final String PREF_USER_IS_LOGIN = "prefUserIsLogin";
    private static final String PREF_USER_NAME = "prefUserName";
    private static final String PREF_USER_PASSWORD = "prefUserPassword";
    private static SharedPrefHandler ourInstance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String PREF_XML_NAME = "expense";
    private boolean isLogin;

    public static SharedPrefHandler getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPrefHandler(context);
        }
        return ourInstance;
    }

    private SharedPrefHandler(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_XML_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean getIsLogin() {
        return sharedPreferences.getBoolean(PREF_USER_IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        editor.putBoolean(PREF_USER_IS_LOGIN, isLogin).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(PREF_USER_NAME, "");
    }

    public void setUserName(String userName) {
        editor.putString(PREF_USER_NAME, userName).apply();
    }
}
