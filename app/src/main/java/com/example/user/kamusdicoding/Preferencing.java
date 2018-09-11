package com.example.user.kamusdicoding;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencing {
    SharedPreferences pref;
    Context context;

    public Preferencing(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = pref.edit();
        String key = "app_first_run";
        editor.putBoolean(key,  input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        String key = "app_first_run";
        return pref.getBoolean(key, true);
    }
}
