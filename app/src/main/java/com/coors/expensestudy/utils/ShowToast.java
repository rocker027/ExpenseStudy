package com.coors.expensestudy.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by z8v on 2017/11/12.
 */

public class ShowToast {
    private Toast toast;
    static ShowToast instance;
    private Context mContext;
    public static final int DURATION =Toast.LENGTH_SHORT;

    public ShowToast(Context context) {
        mContext = context;
    }

    public static ShowToast getInstance(Context context) {
        if (instance == null) {
            instance = new ShowToast(context);
        }
        return instance;
    }

    public void show(String msg) {
        if (toast == null) {
            toast = android.widget.Toast.makeText(mContext, msg, DURATION);
        } else {
            toast.setText(msg);
            toast.setDuration(DURATION);
        }
        toast.show();
    }

    public void show(int stringId) {
        if (toast == null) {
            toast = android.widget.Toast.makeText(mContext, mContext.getString(stringId), DURATION);
        } else {
            toast.setText(mContext.getString(stringId));
            toast.setDuration(DURATION);
        }
        toast.show();
    }
}
