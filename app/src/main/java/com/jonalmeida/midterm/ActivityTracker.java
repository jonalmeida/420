package com.jonalmeida.midterm;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jonathan on 10/03/15.
 */
public class ActivityTracker {
    private static ActivityTracker ourInstance = new ActivityTracker();

    public ArrayList<Activity> ourActivityStack = new ArrayList<>();

    public static ActivityTracker getInstance() {
        return ourInstance;
    }

    private ActivityTracker() {
    }
}
