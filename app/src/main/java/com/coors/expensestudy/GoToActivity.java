package com.coors.expensestudy;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by z8v on 2017/11/7.
 */

public class GoToActivity {
    public static final int HOME_MAIN_ACTIVITY = 1000;
    public static final int LOGIN_ACTIVITY = 1001;
    public static final int ADD_ACTIVITY = 1002;
    private static Intent intent;

    static void goTo(Activity activity, int activityId) {
        switch (activityId) {
            case HOME_MAIN_ACTIVITY:
                if (activity instanceof MainActivity) {
                    intent = null;
                } else {
                    intent = new Intent(activity, MainActivity.class);
                }
                break;
            case LOGIN_ACTIVITY:
                if (activity instanceof LoginActivity) {
                    intent = null;
                } else {
                    intent = new Intent(activity, LoginActivity.class);
                }
                break;
            case ADD_ACTIVITY:
                if (activity instanceof LoginActivity) {
                    intent = null;
                } else {
                    intent = new Intent(activity, AddActivity.class);
                }
                break;
        }
        activity.startActivity(intent);
    }
}
