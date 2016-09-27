package io.github.itsjumaah.lonesafe;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashneelkumar on 23/08/16.
 */
public class ForegroundService extends Service {

    private static final String LOG_TAG = "ForegroundService";
    public static boolean IS_SERVICE_RUNNING = false;
   // public static boolean SOS_TRIGGERED = false;


    public final static String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR = "io.github.itsjumaah.lonesafe.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    public static final String SERVICE_BR = "io.github.itsjumaah.lonesafe.countdown_br";
    Intent serviceIntent = new Intent(SERVICE_BR);

    CountDownTimer checkinCountdown = null;
    CountDownTimer EscalationCountdown = null;
    Timer myTimer = new Timer();

    long jobTime = 54;
    long checkInterval = 21;
    long minutesRemaining = 60;

    long FinTime = jobTime; //
    // public static boolean killService = false;

    public static int counter = 1;
    public static int EscalationCounter = 0;
    public static Boolean JobCancelled = false;
    public Boolean EscalationActive = false;
    public Boolean CheckinCounterActive = false;


    TimerTask myTask = new TimerTask() {

        public void run()
        {
            if(IS_SERVICE_RUNNING){
                IS_SERVICE_RUNNING = false;
                Log.i("SERVER TIMER: ", "RUN");

                sendBroadcast(serviceIntent);
                jobFinishedNotification();
                stopForeground(true);
                stopSelf();
            }
        }
    };

    private void checkinTimer (){
        Log.i(TAG, "Starting timer...");

        final int max = (int) TimeUnit.MILLISECONDS.toSeconds(checkInterval);
        bi.putExtra("max", max);
        sendBroadcast(bi);

        //checkinInterval instead of 30000
        checkinCountdown = new CountDownTimer(checkInterval, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                CheckinCounterActive = true;

                minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                Log.i(TAG, ">>>>>>>>>>>>>>** minutes Remaining:  " + minutesRemaining);

                int progress = (int) (millisUntilFinished/1000);

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);

                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
               // bi.putExtra("max", max);
                bi.putExtra("DisplayCheckin",false);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {

                CheckinCounterActive = false;

                int progress = (int) (minutesRemaining/1000);
                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
                bi.putExtra("DisplayCheckin",true);
                sendBroadcast(bi);

                Intent checkinIntent = new Intent(ForegroundService.this, CheckinNotification.class);
                checkinIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ForegroundService.this.startActivity(checkinIntent);

                Log.i(TAG, "Timer finished");
            }
        };

        checkinCountdown.start();

    }
    private void EscalationTimer(){

        EscalationCountdown = new CountDownTimer(180000, 1000) { //3 minute timer
            @Override
            public void onTick(long millisUntilFinished) {

                EscalationActive = true;

                minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                Log.i(TAG, ">>>>>>>>>>>>>>** ESCALATION minutes Remaining:  " + minutesRemaining);

                int progress = (int) (millisUntilFinished/1000);

                Log.i(TAG, " ESCALATIONCountdown seconds remaining: " + millisUntilFinished / 1000);

                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
                // bi.putExtra("max", max);
              //  bi.putExtra("DisplayCheckin",false);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {

                EscalationActive = false;

                int progress = (int) (minutesRemaining/1000);
                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
              //  bi.putExtra("DisplayCheckin",true);
                sendBroadcast(bi);

                Intent checkinIntent = new Intent(ForegroundService.this, CheckinNotification.class);
                checkinIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ForegroundService.this.startActivity(checkinIntent);

                Log.i(TAG, "Timer finished");
            }
        };

        EscalationCountdown.start();


    }
    @Override
    public void onCreate() {
        super.onCreate();

      //  Log.i("BOOOL", "IS_SERVICE_RUNNING" + IS_SERVICE_RUNNING);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction().equals(Constants.ACTION.CHECKIN_ACTION)){

            if(EscalationCounter == 0){
                if(EscalationActive){
                    EscalationCountdown.cancel();
                }
            }
            if(!CheckinCounterActive){
                checkinTimer();
            }
        }
        if (intent.getAction().equals(Constants.ACTION.ESCALATION_ACTION)){

           // EscalationActive = true;
            EscalationTimer();
        }
        if(intent.getAction().equals(Constants.ACTION.SOS_ACTION)){
            if(EscalationActive){
                EscalationCountdown.cancel();
                EscalationCounter = 0;
                counter++;
                checkinTimer();
            }
        }


        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            jobTime = intent.getIntExtra("jobTime", 69);
            checkInterval = intent.getLongExtra("interval", 38);

            FinTime = TimeUnit.HOURS.toMillis(jobTime);
           // FinTime = 60000; // FOR TESTING 1 minutes

            Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + jobTime + " Hour(s) ------");
            Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + FinTime + " Milliseonds ------");
            Log.i(TAG, ">>>>>>>>>>>>>>** Checkin Remaining:  " + checkInterval);

            checkinTimer();

            myTimer.schedule(myTask, FinTime);
            Log.i("SERVIER TIMER: ", "STARTED");


            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Job Started!", Toast.LENGTH_SHORT).show();


        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            checkinCountdown.cancel();
            if(EscalationActive){
                EscalationCountdown.cancel();
            }
            stopForeground(true);
            stopSelf();

        }
        if(intent.getAction().equals(Constants.ACTION.STOP_ACTION)){
            Intent intent1 = new Intent(this, Home.class);
            this.startActivity(intent1);
        }
        return START_STICKY;
    }

    private void jobFinishedNotification(){

        Intent notificationIntent = new Intent(this, Home.class);
        notificationIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("LoneSafe")
                .setTicker("LoneSafe")
                .setContentText("Job Finished")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, true))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
             //   .addAction(0, "OK",
             //           pendingIntent)

                //android.R.drawable.ic_media_previous
                .build();
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(105,notification);

       // startForeground(Constants.NOTIFICATION_ID.FINISH_FOREGROUND_SERVICE,
       //         notification);

    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, Home.class);
        notificationIntent.setAction(Constants.ACTION.STOP_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent stopIntent = new Intent(this, ForegroundService.class);
        stopIntent.setAction(Constants.ACTION.MAIN_ACTION);
        PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                stopIntent, 0);


        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);

        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("LoneSafe")
                .setTicker("LoneSafe")
                .setContentText("Job in Progress...")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, true))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setProgress(0, 0, true) //indeterminate progress bar. --> maybe update to determinate later?
                .addAction(0, "",
                        null)


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
            public static String CHECKIN_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.CheckinTimer";
            public static String ESCALATION_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.EscalationTimer";
            public static String STARTFOREGROUND_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.startforeground";
            public static String STOPFOREGROUND_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.stopforeground";
            public static String SOS_ACTION = "io.github.itsjumaah.lonesafe.foregroundservice.action.sos";

        }

        public interface NOTIFICATION_ID {
            public static int FOREGROUND_SERVICE = 101;
            public static int FINISH_FOREGROUND_SERVICE = 105;


        }
    }
}

