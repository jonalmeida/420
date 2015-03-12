package com.jonalmeida.midterm;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityTracker {
    private static ActivityTracker ourInstance = new ActivityTracker();

    public ArrayList<Activity> ourActivityStack = new ArrayList<>();

    public static ActivityTracker getInstance() {
        return ourInstance;
    }

    private ActivityTracker() {
    }
}
