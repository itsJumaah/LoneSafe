package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {


    private SharedPreference sharedPreference;
    Activity context = this;

    ProgressBar progressBar;
    int RL;

    public AlertDialog jobfinished;



    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); //
        }
    };


    /*
    //Broadcast receiver listens for service finish...
    private BroadcastReceiver serviceLife = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(!ForegroundService.IS_SERVICE_RUNNING){
                onServiceFinish();
            }
        }
    };

    */


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(ForegroundService.COUNTDOWN_BR));
       // registerReceiver(serviceLife, new IntentFilter(ForegroundService.SERVICE_BR));

        /*
        if(!ForegroundService.IS_SERVICE_RUNNING){
            onServiceFinish();
        }
        */

        // Log.i(TAG, "Registered broacast receiver");
        System.out.println("Registered broacast receiver");

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
       // unregisterReceiver(serviceLife);


        System.out.println("Unregistered broacast receiver");
        //Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
          //  unregisterReceiver(serviceLife);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //jobfinished.dismiss();
       // jobfinished.cancel();

    }

    /*
    public AlertDialog onServiceFinish (){

        updateOnJobtoZero();

        // unregisterReceiver(serviceLife); // <--- WIll this fix the problem Mornez has??

         return jobfinished = new AlertDialog.Builder(this)
                .setTitle("Job Finished")
               // .setMessage(mymessage)
                .setPositiveButton("Start a new job",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String ns = Context.NOTIFICATION_SERVICE;
                                NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
                                nMgr.cancel(105);

                                Intent intent = new Intent(Home.this, Settings.class);
                                Home.this.startActivity(intent);
                                finish();
                            }
                        })
                .show();

       // return jobfinished; <--- WIll this fix the problem Mornez has??

        //--------------
    }

    */

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {

            int progress = intent.getIntExtra("countdown", 0);
            long minutesRemaining = intent.getLongExtra("remaining", 0);
            int max = intent.getIntExtra("max", 900);
            boolean displayCheckin = intent.getBooleanExtra("DisplayCheckin", false);
            //minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(minutesRemaining);
            // Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
            minutesRemaining = minutesRemaining + 1; // adds one minute to the display value.
            System.out.println("|||PROGRESS: " +  progress);
            System.out.println("|||MINUTES: " +  minutesRemaining);
            System.out.println("|||MAX: " +  max);

            if(displayCheckin){
                final TextView tvTimer = (TextView) findViewById(R.id.tvNextCheckLbl);
                tvTimer.setText("Final checkin will be in: ");

                final TextView TvMinute = (TextView) findViewById(R.id.tvTimer);
                TvMinute.setText(" " + minutesRemaining + " Minutes");

               // progressBar.setVisibility(View.INVISIBLE);

                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setMax(max);
                progressBar.setProgress(progressBar.getMax()-progress);
            }
            else{
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setMax(max);

                final TextView tvTimer = (TextView) findViewById(R.id.tvTimer);
                tvTimer.setText(" " + minutesRemaining + " Minutes");
                progressBar.setProgress(progressBar.getMax()-progress); //max set as default in xml atm
                //progressBar.setProgress(max - progress); //max set as default in xml atm
            }

            System.out.println("|||BOOLCHECK: " +  displayCheckin);

            /*
            if(displayCheckin){
                if (ForegroundService.IS_SERVICE_RUNNING) {
                    Intent checkinIntent = new Intent(this, CheckinNotification.class);
                    Home.this.startActivity(checkinIntent);
                }
            }
            */
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //--- action bar icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setTitle(" LoneSafe");

//        registerReceiver(serviceLife, new IntentFilter(ForegroundService.SERVICE_BR));

        sharedPreference = new SharedPreference();

        // Display user details
        final TextView tvWelcome = (TextView) findViewById(R.id.tvUser1);
        String message = "Welcome " + sharedPreference.getValue(context,"Name");
        tvWelcome.setText(message);

        String Value = sharedPreference.getValue(context,"SaveRiskLevel");
        RL = Integer.parseInt(Value);
        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(Value);

        //Get current time //------------------
        /*
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        Date time = cal.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        String startTime = formatter.format(time);
        */

        String startTime = sharedPreference.getValue(context,"TimeStart");
        final TextView tvStTime = (TextView) findViewById(R.id.tvStTime);
        tvStTime.setText(startTime);
        //-------------------------------------|

        //Get Finish Time from SharedPref
        String FinishTime = sharedPreference.getValue(context,"FinishTime");
        final TextView tvFnTime = (TextView) findViewById(R.id.tvFnTime);
        tvFnTime.setText(FinishTime);

        //Get hours from shared Pref -----------|
        String getHour = sharedPreference.getValue(context,"Hours");
        final TextView tvFieldHours = (TextView) findViewById(R.id.tvFieldHours);
        tvFieldHours.setText(getHour);

        int Hours = Integer.parseInt(getHour);
        System.out.println("--------->| #HoursSP = " + Hours);
       // milli = TimeUnit.HOURS.toMillis(Hours);


        //--------------------------------------|

        final Button btnStop = (Button) findViewById(R.id.btnStop);
        assert btnStop != null;
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  countDownTimer.cancel();
             //   countDownTimer.onFinish();

                //Notify if there is no network connection
                if(!NetworkChangeReceiver.isNetworkStatusAvialable(context)){
                    AlertDialog.Builder alert = new AlertDialog.Builder(Home.this);
                    alert.setMessage("NO INTERNET CONNECTION" + "\n\nPlease try again once you have network connection")
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //dialog.dismiss();
                                    onRestart();
                                }
                            })
                            .setTitle("CANNOT STOP JOB")
                            .create();
                    alert.show();
                } else {
                    final AlertDialog.Builder stopjob = new AlertDialog.Builder(Home.this);
                    stopjob.setMessage("Are you sure you want to stop the job?");
                    stopjob.setPositiveButton("Yes, Stop", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(ForegroundService.EscalationCounter > 0){
                                ForegroundService.counter++;
                                setcheckinOnCancel();
                            }
                            else{
                                setcheckinOnCancel();
                            }

                            Toast.makeText(Home.this, "Job Stopped!", Toast.LENGTH_SHORT).show();

                            //STOP FOREGROUND SERVICE
                            Intent service = new Intent(Home.this, ForegroundService.class);
                            if (ForegroundService.IS_SERVICE_RUNNING) {
                                service.setAction(ForegroundService.Constants.ACTION.STOPFOREGROUND_ACTION);
                                ForegroundService.IS_SERVICE_RUNNING = false;

                                ForegroundService.counter = 1;
                                startService(service);
                            }

                            updateOnJobtoZero();

                            Intent intent = new Intent(Home.this, Settings.class);
                            Home.this.startActivity(intent);
                        }
                    });
                    stopjob.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    stopjob.setTitle("Stop Job?").create().show();
                }
            }
        });

        final Button btnSOS = (Button) findViewById(R.id.btnSOS);
        assert btnSOS != null;
        btnSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sosintent = new Intent(Home.this, SosActivity.class);
                Home.this.startActivity(sosintent);

            }
        });

    }

    //--------------------------------------------------------------------------------------------------
    void setcheckinOnCancel(){


        if(ForegroundService.counter == 1){
            ((MyApplication) this.getApplication()).setCheckin1("Cancelled");
        }
        else if(ForegroundService.counter == 2 ){
            ((MyApplication) this.getApplication()).setCheckin2("Cancelled");
        }
        else if(ForegroundService.counter == 3 ){
            ((MyApplication) this.getApplication()).setCheckin3("Cancelled");
        }
        else if(ForegroundService.counter == 4 ){
            ((MyApplication) this.getApplication()).setCheckin4("Cancelled");
        }
        else if(ForegroundService.counter == 5 ){
            ((MyApplication) this.getApplication()).setCheckin5("Cancelled");
        }
        else if(ForegroundService.counter == 6 ){
            ((MyApplication) this.getApplication()).setCheckin6("Cancelled");
        }
        else if(ForegroundService.counter == 7 ){
            ((MyApplication) this.getApplication()).setCheckin7("Cancelled");
        }
        else if(ForegroundService.counter == 8 ){
            ((MyApplication) this.getApplication()).setCheckin8("Cancelled");
        }

        //Get current checkin values..
        String checkin1 = ((MyApplication) this.getApplication()).getCheckin1();
        String checkin2 = ((MyApplication) this.getApplication()).getCheckin2();
        String checkin3 = ((MyApplication) this.getApplication()).getCheckin3();
        String checkin4 = ((MyApplication) this.getApplication()).getCheckin4();
        String checkin5 = ((MyApplication) this.getApplication()).getCheckin5();
        String checkin6 = ((MyApplication) this.getApplication()).getCheckin6();
        String checkin7 = ((MyApplication) this.getApplication()).getCheckin7();
        String checkin8 = ((MyApplication) this.getApplication()).getCheckin8();
        String NextCheckin = ((MyApplication) this.getApplication()).getNextCheckin();


        //Save to db
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

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

        String job_num = sharedPreference.getValue(context,"UserID");
        Log.i("JSON: ", "JOB NUM IS: " + job_num);


        CheckinRequest checkinRequest = new CheckinRequest(job_num,checkin1, checkin2, checkin3, checkin4, checkin5,
                checkin6, checkin7, checkin8, NextCheckin, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Home.this);
        queue.add(checkinRequest);

        //UPDATE ENDTIME IN DB
        Response.Listener<String> responseListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

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

        //get Current time
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        Date time = cal.getTime();
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext()); // Gets system time format
        String endtime = timeFormat.format(time);
        //----

        UpdateEndTimeRequest updateEndTimeRequest = new UpdateEndTimeRequest(job_num, endtime, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(Home.this);
        queue2.add(updateEndTimeRequest);
    }

    void updateOnJobtoZero(){

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

        String username = sharedPreference.getValue(context,"User");
        String onjob = "0";

        UpdateOnjobRequest updateOnjobRequest = new UpdateOnjobRequest(username, onjob, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Home.this);
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

        String job_num = sharedPreference.getValue(context,"UserID");
        String isactive = "0";

        UpdateJobActiveRequest updateJobActiveRequest = new UpdateJobActiveRequest(job_num, isactive, responseListener2);
        RequestQueue queue2 = Volley.newRequestQueue(Home.this);
        queue2.add(updateJobActiveRequest);

    }

    @Override
    public void onBackPressed() {
        //DO Nothing!
        //This Disables the back button.
    }

    //WHERE IS THIS CALLED?? TODO DELETE if not used
    public void showSosAlert (View view){
        final AlertDialog.Builder sosAlert = new AlertDialog.Builder(this);
        sosAlert.setMessage("You are about to trigger an alarm! Are you in Trouble?");
        sosAlert.setPositiveButton("SEND HELP!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        sosAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        sosAlert.setTitle("S.O.S").create().show();

    }


}



