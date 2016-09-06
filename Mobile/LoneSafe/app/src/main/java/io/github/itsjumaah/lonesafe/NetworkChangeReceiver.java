package io.github.itsjumaah.lonesafe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ashneelkumar on 9/08/16.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    static TSnackbar snackbar; //make it as global

    @Override
    public void onReceive(Context context, Intent intent) {

        isNetworkStatusAvialable(context);

    }
    public static boolean isNetworkStatusAvialable (Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            NetworkChangeReceiver.snack(null, "No Internet Connectivity.",context.getApplicationContext());
            return false;
            //  MyApplication.appactivity.setTheme(R.style.testTheme);

        }else{
            NetworkChangeReceiver.hideSnackbar();
            return true;
            //  MyApplication.appactivity.setTheme(R.style.AppTheme);

        }
    }

    public static void snack (HashMap<String,View.OnClickListener> actions, String message, Context context) {
        if(MyApplication.appactivity != null){
            snackbar = TSnackbar.make(MyApplication.appactivity.findViewById(android.R.id.content), message, TSnackbar.LENGTH_INDEFINITE);//MyApplication.appactivity from Application class.
            View view = snackbar.getView();
            FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            if (actions != null) {
                Iterator iterator = actions.entrySet().iterator();
                snackbar.setDuration(TSnackbar.LENGTH_INDEFINITE);
                while (iterator.hasNext()) {
                    Map.Entry pair = (Map.Entry) iterator.next();
                    snackbar.setAction((String) pair.getKey(), (View.OnClickListener) pair.getValue());
                    iterator.remove(); // avoids a ConcurrentModificationException
                }
            }
            //switch (priority) {
              //  case 0:
            view.setBackgroundColor(Color.parseColor("#B20000"));
               //     break;
              //  case 1:
               //     snackbar.getView().setBackgroundColor(Color.parseColor("#66ccff"));
               //     break;
               // case 2:
               //     snackbar.getView().setBackgroundColor(Color.parseColor("#66ff33"));
               //     break;
           // }
            TextView txtv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                txtv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            else {
                txtv.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            snackbar.show();
        }
    }
    private static void hideSnackbar(){
        if(snackbar !=null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
}

