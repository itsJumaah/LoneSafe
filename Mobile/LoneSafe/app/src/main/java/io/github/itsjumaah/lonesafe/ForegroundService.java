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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashneelkumar on 23/08/16.
 */
public class ForegroundService extends Service {

    private static final String LOG_TAG = "ForegroundService";
    public static boolean IS_SERVICE_RUNNING = false;
    public static boolean LAST_CHECKIN = false;
    public static boolean NEED_TO_SEND_CHECKIN = false;

    // public static boolean SOS_TRIGGERED = false;


    public final static String TAG = "BroadcastService";

    public static final String COUNTDOWN_BR = "io.github.itsjumaah.lonesafe.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    public static final String SERVICE_BR = "io.github.itsjumaah.lonesafe.service_br";
    Intent serviceIntent = new Intent(SERVICE_BR);

    CountDownTimer checkinCountdown = null;
    CountDownTimer EscalationCountdown = null;
    CountDownTimer JobCountdown = null;
   // Timer myTimer = new Timer();

    long jobTime = 54;
    long checkInterval = 21;
    long minutesRemaining = 60;
    String username;
    String job_num;
    String NextCheckin;

    String currentTime;
    long milliseconds;

    public static long jobTimerCurrentValue = 9000000;

    long FinTime; // = jobTime;

    final long TestInterval = 120000;

    public static int counter = 1;
    public static int EscalationCounter = 0;
    public Boolean EscalationActive = false;
    public Boolean CheckinCounterActive = false;

/*
    TimerTask myTask = new TimerTask() {

        public void run()
        {
            if(IS_SERVICE_RUNNING){
                IS_SERVICE_RUNNING = false;
                Log.i("SERVER TIMER: ", "RUN");

                sendBroadcast(serviceIntent);
                jobFinishedNotification();
               // UpdateonJobDB();

                stopForeground(true);
                stopSelf();


                if(CheckinCounterActive){
                    checkinCountdown.cancel();
                }
                if(EscalationActive){
                    EscalationCountdown.cancel();
                }

            }
        }
    };
    */

    private void jobTimer(){
        Log.i(TAG, "Starting Job timer");

        Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + jobTime + " Hour(s) ------");
        Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + FinTime + " Milliseonds ------");

        FinTime = TimeUnit.HOURS.toMillis(jobTime);
        final int max = (int) TimeUnit.MILLISECONDS.toSeconds(FinTime);

        JobCountdown = new CountDownTimer(FinTime, 1000) { //1 minutes
            @Override
            public void onTick(long millisUntilFinished) {

               // jobTimerCurrentValue = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                jobTimerCurrentValue = millisUntilFinished;
                long FinishminutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);

                if(LAST_CHECKIN){
                    bi.putExtra("DisplayCheckin",true);
                    bi.putExtra("remaining", FinishminutesRemaining);
                    int progress = (int) (millisUntilFinished/1000);
                    bi.putExtra("countdown", progress);
                    bi.putExtra("max", max);
                    sendBroadcast(bi);
                }
            }

            @Override
            public void onFinish() {

                //IS_SERVICE_RUNNING = false;
                Log.i("SERVER TIMER: ", "OnFinish");

                /*
                if(CheckinCounterActive){
                    checkinCountdown.cancel();
                }
                if(EscalationActive){
                    EscalationCountdown.cancel();
                }
                */

               // sendBroadcast(serviceIntent);
               // jobFinishedNotification();
               // stopForeground(true);
               // stopSelf();

                //16-10-16 TEST
                Intent checkinIntent = new Intent(ForegroundService.this, CheckinNotification.class);
                checkinIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ForegroundService.this.startActivity(checkinIntent);

            }
        };
        JobCountdown.start();

    }

    private void checkinTimer (){

        // LAST_CHECKIN = false;

        Log.i(TAG, "Starting timer...");

        final int max = (int) TimeUnit.MILLISECONDS.toSeconds(checkInterval); //checkInterval
        bi.putExtra("max", max);
        sendBroadcast(bi);

        checkinCountdown = new CountDownTimer(checkInterval, 1000) {  //checkInterval
            @Override
            public void onTick(long millisUntilFinished) {
                CheckinCounterActive = true;

                minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                Log.i(TAG, ">>>>>>>>>>>>>>** minutes Remaining:  " + minutesRemaining);

                int progress = (int) (millisUntilFinished/1000);

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);

                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
                bi.putExtra("interval",checkInterval);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {

                CheckinCounterActive = false;

                int progress = (int) (minutesRemaining/1000);
                bi.putExtra("countdown", progress);
                bi.putExtra("remaining", minutesRemaining);
                bi.putExtra("interval",checkInterval);

                //  bi.putExtra("DisplayCheckin",true);
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


        final int max = (int) TimeUnit.MILLISECONDS.toSeconds(180000);
        bi.putExtra("max", max);
        sendBroadcast(bi);

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

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction().equals(Constants.ACTION.CHECKIN_ACTION)){

            if(EscalationCounter == 0){
                if(EscalationActive){
                    EscalationCountdown.cancel();
                }
            }

            if(jobTimerCurrentValue <= checkInterval){ //checkInterval
                System.out.println("LAST CHECKIN: " + checkInterval + "jobRemaining: " + jobTimerCurrentValue);
                LAST_CHECKIN = true;
               // nextCheckinTime();
                getServerTime();

               // bi.putExtra("DisplayCheckin",true);
               // sendBroadcast(bi);
            }

            else if(!CheckinCounterActive) {
               checkinTimer();
            }


        }
        if (intent.getAction().equals(Constants.ACTION.ESCALATION_ACTION)){

           // EscalationActive = true;
            EscalationTimer();

            /*
            if(jobTimerCurrentValue <= 180000){ //checkInterval
                System.out.println("Extend Escalation: " + checkInterval + "jobRemaining: " + jobTimerCurrentValue);
                FinTime = jobTimerCurrentValue + 180000;
            }
            */
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
            username = intent.getStringExtra("username");
            job_num = intent.getStringExtra("job_num");

         //   FinTime = TimeUnit.HOURS.toMillis(jobTime);
           // FinTime = 960000; // FOR TESTING 16 minutes

          //  Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + jobTime + " Hour(s) ------");
         //   Log.i(TAG, ">>>>>>>>>>>>>>** FINISH Remaining:  " + FinTime + " Milliseonds ------");
            Log.i(TAG, ">>>>>>>>>>>>>>** Checkin Remaining:  " + checkInterval);

            jobTimer();
            checkinTimer();


           // myTimer.schedule(myTask, FinTime);
            Log.i("SERVICE TIMER: ", "STARTED");


            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Job Started!", Toast.LENGTH_SHORT).show();


        }
        if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            UpdateonJobDB();
            JobCountdown.cancel();
            checkinCountdown.cancel();
            if(EscalationActive){
                EscalationCountdown.cancel();
            }

            stopForeground(true);
            stopSelf();

            if(LAST_CHECKIN){
               // sendBroadcast(serviceIntent);
                jobFinishedNotification();

                LAST_CHECKIN = false;
                IS_SERVICE_RUNNING = false;

                Intent Finishintent = new Intent(ForegroundService.this, Settings.class);
                Finishintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ForegroundService.this.startActivity(Finishintent);
            }
        }
        /*
        if(intent.getAction().equals(Constants.ACTION.STOP_ACTION)){

            Intent intent1 = new Intent(this, Settings.class);
            this.startActivity(intent1);

        }
        */
        return START_STICKY;
    }

    //---------------------

    void getServerTime(){

        //--------------------------------------------------------------------
        System.out.print(" $$$getTimeFromServer() CALLED for NextCheckin ");

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    currentTime = jsonResponse.getString("time");

                    nextCheckinTime();


                    if (success) {
                        Log.i("JSON: ", "Response true");


                    } else {
                        Log.i("JSON: ", "Response false");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ServerTimeRequest serverTimeRequest = new ServerTimeRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(ForegroundService.this);
        queue.add(serverTimeRequest);


        //--------------------------------------------------------------------

    }

    void nextCheckinTime(){

        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = sdformat.parse(currentTime);
            milliseconds = date.getTime(); //<--here gets the milliseconds
            System.out.println("******  Milliseconds==" + milliseconds);
        }
        catch (ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //long interval = ((MyApplication) this.getApplication()).getinterval();
       // DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext()); // Gets system time format

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        if(ForegroundService.LAST_CHECKIN) {
            // long nextTime = System.currentTimeMillis() + ForegroundService.jobTimerCurrentValue;
            long nextTime = milliseconds + ForegroundService.jobTimerCurrentValue;
            NextCheckin = formatter.format(nextTime);
            ((MyApplication) this.getApplication()).setNextCheckin(NextCheckin);
        }

        SaveToDataBase();
        System.out.print(" @@@@@@@@@@@@@@@@@@ ## NEXT CHECKIN TIME = " + NextCheckin);

    }

    void SaveToDataBase(){
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.i("JSON: ", "Response true");

                    } else {
                        Log.i("JSON: ", "Response false");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //String job_num = sharedPreference.getValue(context,"UserID");
        //Log.i("JSON: ", "JOB NUM IS: " + job_num);

        nextCheckinRequest nextCheckinRequest = new nextCheckinRequest(job_num, NextCheckin, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ForegroundService.this);
        queue.add(nextCheckinRequest);
    }

    //---------

    private void UpdateonJobDB(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.i("JSON: ", "Response true");

                    } else {

                        Log.i("JSON: ", "Response false");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


      //  String username = sharedPreference.getValue(context,"User");
        String onjob = "0";

        UpdateOnjobRequest updateOnjobRequest = new UpdateOnjobRequest(username, onjob, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ForegroundService.this);
        queue.add(updateOnjobRequest);

        //---
        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Log.i("JSON: ", "Response true");
                    } else {
                        Log.i("JSON: ", "Response false");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

       // String job_num = sharedPreference.getValue(context,"UserID");
        String isactive = "0";

        UpdateJobActiveRequest updateJobActiveRequest = new UpdateJobActiveRequest(job_num, isactive, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(ForegroundService.this);
        queue2.add(updateJobActiveRequest);
    }

    private void jobFinishedNotification(){

        Intent notificationIntent = new Intent(this, Settings.class);
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
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Job Finished - Click to start new job or swipe to dismiss"))
                .setContentText("Job Finished")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, true))
                .setContentIntent(pendingIntent)
                .setOngoing(false)
               // .addAction(0, "Dismiss",
               //         pendingIntent)

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
       // notificationIntent.setAction(Constants.ACTION.STOP_ACTION);
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
               // .addAction(0, "",
                 //       null)


                //android.R.drawable.ic_media_previous
                .build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
       // Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
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
            public static String SOS_REQUIRED = "io.github.itsjumaah.lonesafe.foregroundservice.action.sosrequired";


        }

        public interface NOTIFICATION_ID {
            public static int FOREGROUND_SERVICE = 101;
            public static int FINISH_FOREGROUND_SERVICE = 105;


        }
    }
}

