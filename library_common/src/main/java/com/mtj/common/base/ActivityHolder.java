package com.mtj.common.base;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

public class ActivityHolder {

    public final static String MAIN = "/main/MainActivity";
    public final static String LOGIN = "/login/LoginActivity";
    public final static String WELCOME = "/welcome/WelcomeActivity";

    public final static String CAIYUN = "/cy/CaiYunActivity";


    private static ArrayList<Activity> activityArray = new ArrayList<Activity>();

    public static void addActivity(Context context) {
        activityArray.add((Activity) context);
    }

    public static void removeActivity(Context context) {
        activityArray.remove((Activity) context);
    }

    public static void removeAllActivity() {
        for (Activity activity : activityArray) {
            activity.finish();
        }
    }
}
