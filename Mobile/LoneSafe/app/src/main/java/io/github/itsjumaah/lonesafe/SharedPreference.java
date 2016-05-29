package io.github.itsjumaah.lonesafe;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * Created by ashneelkumar on 28/05/16.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "AOP_PREFS";

    public static final String User = "User";
    public static final String Name = "Name";
    public static final String Pass = "Pass";

    public static final String TimeStart = "TimeStart";
    public static final String TimeEnd = "TimeEnd";
    public static final String RiskLevel = "RiskLevel";



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

    public void saveRL (Context context, String text) {
        save(context,RiskLevel,text);
    }

    public void saveTimeStart (Context context, String text) {
        save(context,TimeStart,text);
    }

    public void saveTimeEnd (Context context, String text) {
        save(context,TimeEnd,text);

    }

    public void save (Context context,String key, String text){

        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(key, text); //3
        editor.commit(); //4

    }

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


    //-----------------------------------------------------
/*  // This is redundant if we apply correct Key when we call the function -- DELETE
    public String getName (Context context){
        returnText = getValue(context,Name);
        return returnText;
    }

    public String getPass (Context context){
        returnText = getValue(context,Pass);
        return returnText;
    }

    public String getTimeStart (Context context){
        returnText = getValue(context,TimeStart);
        return returnText;
    }
    public String getTimeEnd (Context context){
        returnText = getValue(context,TimeEnd);
        return returnText;
    }

    public String getRiskLevel (Context context){
        returnText = getValue(context,RiskLevel);
        return returnText;
    }

*/
    public String getValue(Context context,String key) {
        SharedPreferences settings;
        String text;
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(key,null);
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context, String key) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(key);
        editor.commit();
    }
}
/*

public class SavedPreference {
    public static final String PREF_NAME = "your preferences name";
    public static final int MODE = Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;

    public static final String DEFAULT = "N/A";

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

}
*/