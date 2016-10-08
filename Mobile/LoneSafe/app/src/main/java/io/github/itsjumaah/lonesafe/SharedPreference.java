package io.github.itsjumaah.lonesafe;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPreference {

    public static final String PREFS_NAME = "AOP_PREFS";

    public static final String User = "User";
    public static final String Name = "Name";
    public static final String Pass = "Pass";
    public static final String UserID = "UserID";

    public static final String TimeStart = "TimeStart";
    public static final String FinishTime = "FinishTime";
    public static final String SaveRiskLevel = "SaveRiskLevel";
    public static final String Hours = "Hours";



    public SharedPreference() {
        super();
    }

    public void saveName (Context context, String text) {
        save(context,Name,text);

    }

    public void savePass (Context context, String text) {
        save(context,Pass,text);

    }

    public void saveUser (Context context, String text){
        save(context,User,text);
    }

    public void saveUserID (Context context, String text){
        save(context,UserID,text);
    }

    public void saveHours (Context context, String text){
        save(context,Hours,text);
    }

    public void saveRL (Context context, String text) {
        save(context,SaveRiskLevel,text);
    }

    public void saveTimeStart (Context context, String text) {
        save(context,TimeStart,text);
    }


    public void saveTimeEnd (Context context, String text) {
        save(context,FinishTime,text);

    }

    public void save (Context context,String key, String text){

        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(key, text); //3
        //editor.commit(); //4
        editor.apply(); //4

    }

    /*

    public void setRLPos (Context context,int pos) {
        SharedPreferences settings;
        Editor editor;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit(); //2
        editor.putInt("last_val",pos);
        editor.commit();
    }

    public int getRLPos (Context context){
        SharedPreferences settings;
        int id;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        id = settings.getInt("last_val",0);
        return id;

    }

    */


    //-----------------------------------------------------


    public String getValue(Context context,String key) {
        SharedPreferences settings;
        String text;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(key,null);
        return text;
    }

    //Clears everything from shared Pref (i.e. when User logs out)
    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
       // editor.commit();
        editor.apply();

    }

    //For removing indiviual values from shared Preference
    public void removeValue(Context context, String key) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(key);
        //editor.commit();
        editor.apply();

    }
}
