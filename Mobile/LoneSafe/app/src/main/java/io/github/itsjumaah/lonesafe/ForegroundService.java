package io.github.itsjumaah.lonesafe;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ashneelkumar on 23/08/16.
 */
public class ForegroundService extends Service {

    private static final String LOG_TAG = "ForegroundService";
    public static boolean IS_SERVICE_RUNNING = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

       // } else if (intent.getAction().equals(Constants.ACTION. STOP_ACTION)) {
       //     Log.i(LOG_TAG, "Clicked Stop");

            //Set onJob Boolean to false ?

        //    Toast.makeText(this, "Stop Clicked", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, Home.class);
        notificationIntent.setAction(Constants.ACTION.STOP_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

/*        Intent stopIntent = new Intent(this, ForegroundService.class);
        stopIntent.setAction(Constants.ACTION.MAIN_ACTION);
        PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                stopIntent, 0);
*/

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("LoneSafe")
                .setTicker("LoneSafe")
                .setContentText("Job in Progress...")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, true))
               // .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setProgress(0, 0, true) //indeterminate progress bar. --> Update to determinate later or leave?
                .addAction(0, "STOP JOB",
                        pendingIntent)


                //android.R.drawable.ic_media_previous
                .build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
        //Set Onjob boolean false here
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }

//--------------------------------------------------------------------------------------------------
    public static class Constants {

        public interface ACTION {
            public static String MAIN_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.main";
            public static String STOP_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.stop";
            public static String STARTFOREGROUND_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.startforeground";
            public static String STOPFOREGROUND_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.stopforeground";
        }

        public interface NOTIFICATION_ID {
            public static int FOREGROUND_SERVICE = 101;
        }
    }
}

