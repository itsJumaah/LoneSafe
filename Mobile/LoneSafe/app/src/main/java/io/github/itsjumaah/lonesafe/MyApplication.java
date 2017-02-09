package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by ashneelkumar on 9/08/16.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    public static SharedPreferences sharedPreferences;

    public String checkin1 = "null";
    public String checkin2 = "null";
    public String checkin3 = "null";
    public String checkin4 = "null";
    public String checkin5 = "null";
    public String checkin6 = "null";
    public String checkin7 = "null";
    public String checkin8 = "null";
    public String NextCheckin = "null";

    public long interval = 0;

    public void setinterval(long interval){
        this.interval = interval;
    }
    public long getinterval(){
        return interval;
    }

    public String getCheckin1(){
        return checkin1;
    }
    public void setCheckin1(String checkin1){
        this.checkin1 = checkin1;
    }
    public String getCheckin2(){
        return checkin2;
    }
    public void setCheckin2(String checkin2){
        this.checkin2 = checkin2;
    }
    public String getCheckin3(){
        return checkin3;
    }
    public void setCheckin3(String checkin3){
        this.checkin3 = checkin3;
    }
    public String getCheckin4(){
        return checkin4;
    }
    public void setCheckin4(String checkin4){
        this.checkin4 = checkin4;
    }
    public String getCheckin5(){
        return checkin5;
    }
    public void setCheckin5(String checkin5){
        this.checkin5 = checkin5;
    }
    public String getCheckin6(){
        return checkin6;
    }
    public void setCheckin6(String checkin6){
        this.checkin6 = checkin6;
    }
    public String getCheckin7(){
        return checkin7;
    }
    public void setCheckin7(String checkin7){
        this.checkin7 = checkin7;
    }
    public String getCheckin8(){
        return checkin8;
    }
    public void setCheckin8(String checkin8){
        this.checkin8 = checkin8;
    }
    public String getNextCheckin(){
        return NextCheckin;
    }
    public void setNextCheckin(String NextCheckin){
        this.NextCheckin = NextCheckin;
    }


    public static Activity appactivity;
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);

    }
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        appactivity = activity;//here we get the activity
        Intent i = new Intent(this, NetworkChangeReceiver.class);
        sendBroadcast(i);//here we are calling the broadcastreceiver to check connection state.
    }

    public void onActivityStopped(Activity activity){

    }
    public void onActivityPaused(Activity activity){

    }
    public void onActivitySaveInstanceState(Activity activity, Bundle outState){

    }
    public void onActivityDestroyed(Activity activity){

    }

}