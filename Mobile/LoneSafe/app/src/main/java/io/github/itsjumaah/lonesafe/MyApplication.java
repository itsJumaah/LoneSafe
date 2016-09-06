package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ashneelkumar on 9/08/16.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
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