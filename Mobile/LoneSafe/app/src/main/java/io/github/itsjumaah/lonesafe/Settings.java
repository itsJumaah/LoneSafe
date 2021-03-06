package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Settings extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref


    int Hours = 1;
    int RiskLevel = 1;
    String FinishTime;
    String jobID;
    ProgressBar loadProgressBar;

    String currentTime;
    long milliseconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreference = new SharedPreference();

        //init action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setTitle(" LoneSafe");

        //init GPS to ensure it is turned on in users device
        InitiateGPS();

        // Display user details
        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);
        String message = "Welcome " + sharedPreference.getValue(context,"Name");
        tvWelcomeMsg.setText(message);

        SaveVersion();



        //------------------------------------------------------------------------------------------
        //------------------------------------- RISK LEVEL & HOURS PICKER --------------------------
        //------------------------------------------------------------------------------------------


        //--------------------------------RiskLvl Picker--------------------------------------------

        final String getRL = sharedPreference.getValue(context,"SaveRiskLevel");
        if (getRL != null){
            RiskLevel = Integer.parseInt(getRL);
            System.out.println("---------> #RISKLEVELSP = " + RiskLevel);

        }

        android.widget.NumberPicker npRiskLvl = (android.widget.NumberPicker) findViewById(R.id.numberPicker2);

        npRiskLvl.setMinValue(0);
        npRiskLvl.setMaxValue(4);
        npRiskLvl.setWrapSelectorWheel(false);
        npRiskLvl.setDisplayedValues( new String[] {"1 - Very Low","2 - Low","3 - Medium","4 - High","5 - Very High"});
        npRiskLvl.setValue(RiskLevel-1);

        npRiskLvl.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                oldVal++;
                RiskLevel = newVal;
                RiskLevel = RiskLevel + 1;

                String SaveRiskLevel = String.valueOf(RiskLevel);
                System.out.println("---------> #RL = " + RiskLevel);
                sharedPreference.saveRL(context, SaveRiskLevel);

            }
        });

        //--------------------------------Hours Picker----------------------------------------------

        final String getHour = sharedPreference.getValue(context,"Hours");
        if (getHour != null){
            Hours = Integer.parseInt(getHour);
            System.out.println("---------> #HoursSP = " + Hours);
        }

        NumberPicker npHours = (NumberPicker) findViewById(R.id.numberPicker);
        String[] test = getResources().getStringArray(R.array.hours);

        npHours.setMinValue(0);
        npHours.setMaxValue(11);
        npHours.setWrapSelectorWheel(true);
        npHours.setDisplayedValues(test);
        npHours.setValue(Hours-1);

        npHours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                oldVal++;
                Hours = newVal;
                Hours = Hours + 1;

                String SaveHourStr = String.valueOf(Hours);
                System.out.println("---------> #HOURSSAVED = " + Hours);
                sharedPreference.saveHours(context, SaveHourStr);
            }
        });

        //------------------------------------------------------------------------------------------
        //------------------------------------- START BUTTON ---------------------------------------
        //------------------------------------------------------------------------------------------


        final Button btnStart = (Button) findViewById(R.id.btnStart);
        assert btnStart !=null;

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    final long interval = checkinInterval (Hours, RiskLevel);

                String ns = Context.NOTIFICATION_SERVICE;
                NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
                nMgr.cancel(105);


                saveStartTime();
                getTime();


                //Checks if SharedPref Null---------------

                if(getHour == null){
                    String SaveHourStr = String.valueOf(Hours);
                    System.out.println("-----||----> #HOURSSAVED = " + Hours);
                    sharedPreference.saveHours(context, SaveHourStr);
                }

                if (getRL == null){
                    String SaveRiskLevel = String.valueOf(RiskLevel);
                    // System.out.println("---------> #RL = " + RiskLevel);
                    sharedPreference.saveRL(context, SaveRiskLevel);
                }
                //----------|
                // Alert user if no network connection
                if (!NetworkChangeReceiver.isNetworkStatusAvialable(context)) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
                    alert.setMessage("PLEASE ENSURE YOUR DATA IS TURNED ON")
                            .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //dialog.dismiss();
                                    onRestart();
                                }
                            })
                            .setTitle("NO INTERNET CONNECTION")
                            .create();
                    alert.show();
                } else {

                    final AlertDialog.Builder confirm = new AlertDialog.Builder(Settings.this);
                    confirm.setMessage("RiskLevel: " + RiskLevel + "\n\nHours: " + Hours + "\n\n You will finish at: " + FinishTime);
                    confirm.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                            loadProgressBar.setVisibility(View.VISIBLE);

                            setCheckinnull();
                            checkForActiveJobs();
                            createJobDB();

                            String username = sharedPreference.getValue(context,"User");
                            String starttime = sharedPreference.getValue(context,"TimeStart");
                            String endtime = sharedPreference.getValue(context,"FinishTime");
                            String workinghours = sharedPreference.getValue(context,"Hours");
                            String risklevel = sharedPreference.getValue(context,"SaveRiskLevel");

                            SavetoFirebase(username, starttime,endtime,workinghours,risklevel);
                           // setNextCheckinInit();
                          //  updateOnJob();

                        }
                    });

                    confirm.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    confirm.setCancelable(false);
                    confirm.setTitle("Confirm").create().show();
                }

            }
        });

    }

    //----------------------------------------------------------------------------------------------
    //------------------------------------- Save Job to Database -----------------------------------
    //----------------------------------------------------------------------------------------------

    /*
    * Creates a new entry in jobs table, and starts the foreground service
    */

    void SaveVersion(){

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
        String version = BuildConfig.VERSION_NAME;

        SaveVersionRequest saveVersionRequest = new SaveVersionRequest(username, version, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(saveVersionRequest);

    }

    void createJobDB(){
        final long interval = checkinInterval (Hours, RiskLevel);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.i("JSON: ", "Response true");

                        //TODO note storing as userid, change to jobid in sharedpref
                        int job_num = jsonResponse.getInt("job_num");
                        String userId = String.valueOf(job_num);

                        sharedPreference.saveUserID(context, userId);

                        Log.i("JSON: ", "JOB ID = " + userId);

                       // checkForActiveJobs();

                        updateOnJob();
                        updateJobActive();
                        SaveLocationToDB();
                        getTimeFromServer();
                       // setNextCheckinInit();
                        setCoverage();


                        String username = sharedPreference.getValue(context,"User");
                       // String job_num = sharedPreference.getValue(context,"UserID");


                        //START FOREGROUND SERVICE
                        Intent service = new Intent(Settings.this, ForegroundService.class);
                        service.putExtra("jobTime", Hours);//
                        service.putExtra("interval",interval);
                        service.putExtra("username", username);
                        service.putExtra("job_num", userId);

                        if (!ForegroundService.IS_SERVICE_RUNNING) {
                            service.setAction(ForegroundService.Constants.ACTION.STARTFOREGROUND_ACTION);
                            ForegroundService.IS_SERVICE_RUNNING = true;
                            ForegroundService.counter = 1;
                            startService(service);
                        }

                        loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                        loadProgressBar.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(Settings.this, Home.class);
                        Settings.this.startActivity(intent);
                        finish();

                    } else {

                        Log.i("JSON: ", "Response false");
                        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                        builder.setMessage("INSERT Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        String username = sharedPreference.getValue(context,"User");
        String starttime = sharedPreference.getValue(context,"TimeStart");
        String endtime = sharedPreference.getValue(context,"FinishTime");
        String workinghours = sharedPreference.getValue(context,"Hours");
        String risklevel = sharedPreference.getValue(context,"SaveRiskLevel");


        InsertDataRequest insertDataRequest = new InsertDataRequest(username,starttime,endtime, workinghours, risklevel ,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(insertDataRequest);

    }

    /*
    * set the value for checkin null incase the user starts another job. This ensures there are no values
    * stored from the previous job
    */

    void setCheckinnull(){
        ((MyApplication) this.getApplication()).setCheckin1("null");
        ((MyApplication) this.getApplication()).setCheckin2("null");
        ((MyApplication) this.getApplication()).setCheckin3("null");
        ((MyApplication) this.getApplication()).setCheckin4("null");
        ((MyApplication) this.getApplication()).setCheckin5("null");
        ((MyApplication) this.getApplication()).setCheckin6("null");
        ((MyApplication) this.getApplication()).setCheckin7("null");
        ((MyApplication) this.getApplication()).setCheckin8("null");
    }

    /*
    * method updates a variable called onjob in the users table to indicate the user is active
    * updateJobActive updates a similar variable in the jobs table to show the current job
    */
    void updateOnJob(){

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
        String onjob = "1";

        UpdateOnjobRequest updateOnjobRequest = new UpdateOnjobRequest(username, onjob, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(updateOnjobRequest);

        //---

    }
    void updateJobActive(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr_JOBACTIVE", "["+response+"]");
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
        String isactive = "1";

        UpdateJobActiveRequest updateJobActiveRequest = new UpdateJobActiveRequest(job_num, isactive, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(updateJobActiveRequest);
    }

    void setCoverage(){

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

        String job_num = sharedPreference.getValue(context,"UserID");

        CoverageRequest coverageRequest = new CoverageRequest(job_num, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(coverageRequest);

    }

    /*
    * -- Not sending location here. --
    * Creates an entry in the locations table which is then grabbed by the desktop app.
    * The values for Longitude and Latitude would by NULL unless a SOS or Escalation is triggered.
    */
    void SaveLocationToDB(){

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

        String job_num = sharedPreference.getValue(context,"UserID");
        String lng ="null";
        String lat = "null";

        LocationRequest locationRequest = new LocationRequest(job_num, lng, lat, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(locationRequest);

    }

    void checkForActiveJobs(){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr_JOBACTIVE", "["+response+"]");
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

        DeleteJobRequest deleteJobRequest = new DeleteJobRequest(username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(deleteJobRequest);
    }

    //----------------------------------------------------------------------------------------------
    //------------------------------------- Save Job to FIREBASE Database --------------------------
    //TODO FIREBASE
    //----------------------------------------------------------------------------------------------

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();

    private void SavetoFirebase (String username, String starttime, String endtime, String workinghours, String risklevel){

        String checkin1 = "null";
        String checkin2 = "null";
        String checkin3 = "null";
        String checkin4 = "null";
        String checkin5 = "null";
        String checkin6 = "null";
        String checkin7 = "null";
        String checkin8 = "null";
        String NextCheckin = "null";

        jobInfo job = new jobInfo(username, starttime,endtime, workinghours, risklevel, checkin1, checkin2, checkin3, checkin4, checkin5,
                checkin6, checkin7, checkin8, NextCheckin);

        /*
        myRef.child("Job").push().child("user").child(username);
        myRef.child("Job").child("startTime").child(starttime);
        myRef.child("Job").child("endTime").child(endtime);
        myRef.child("Job").child("workingHours").child(workinghours);
        myRef.child("Job").child("riskLevel").child(risklevel);
        myRef.setValue(job);
        */
        jobID = myRef.push().getKey();
        sharedPreference.saveJobID(context,jobID);

        myRef.child("Job").child(jobID).setValue(job);





        // myRef.child("Job").child(endtime).setValue(job);
        //myRef.child("Job").child(workinghours).setValue(job);
        //myRef.child("Job").child(risklevel).setValue(job);



    }

    //----------------------------------------------------------------------------------------------
    //----------------------------- Calculate Checkin Interval  + Time   ---------------------------
    //----------------------------------------------------------------------------------------------

    /*
    * Method checkinInterval calculates the checkin interval based on the risk level and the hours
    * See the Developers manual for the checkin interval rules
    */
    public long checkinInterval (long hours, int risklvl) {

        long inMinutes = TimeUnit.HOURS.toMinutes(hours);

        System.out.println("+++INFUCN+++++++ RL = " + risklvl + " <---------------------");
        System.out.println("++++INFUCN++++++ HOURS = " + hours + " <---------------------");
        System.out.println("++++INFUCN++++++ inMinutes = " + inMinutes + " <---------------------");

        long interval = inMinutes;

        if (risklvl == 1){
            if(hours == 1 || hours == 2 || hours == 3 || hours == 4){
                interval = inMinutes / 2;
            } else if (hours == 5 || hours == 6 || hours == 7) {
                interval = inMinutes / 3;
            }else if(hours == 8 || hours == 9 || hours == 10){
                interval = inMinutes / 3;
            }else if(hours == 11 || hours == 12){
                interval = inMinutes / 4;
            }
        }

        if (risklvl == 2 ){
            if(hours == 1 || hours == 2 || hours == 3 || hours == 4){
                interval = inMinutes/3;
            } else if (hours == 5 || hours == 6 || hours == 7) {
                interval = inMinutes / 4;
            }else if(hours == 8 || hours == 9 || hours == 10){
                interval = inMinutes / 4;
            }else if(hours == 11 || hours == 12){
                interval = inMinutes / 5;
            }
        }

        if (risklvl == 3 ){
            if(hours == 1){
                interval = inMinutes / 3;
            }else if(hours == 2 || hours == 3 || hours == 4){
                interval = inMinutes / 4;

            } else if (hours == 5 || hours == 6 || hours == 7) {
                interval = inMinutes / 5;
            }else if(hours == 8 || hours == 9 || hours == 10){
                interval = inMinutes / 5;
            }else if(hours == 11 || hours == 12){
                interval = inMinutes / 6;
            }
        }

        if (risklvl == 4 ){
            if(hours == 1){
                interval = inMinutes / 3;
            }else if(hours == 2 || hours == 3 || hours == 4){
                interval = inMinutes / 5;
            } else if (hours == 5 || hours == 6 || hours == 7) {
                interval = inMinutes / 6;
            }else if(hours == 8 || hours == 9 || hours == 10){
                interval = inMinutes / 7;
            }else if(hours == 11 || hours == 12){
                interval = inMinutes / 7;
            }
        }

        if (risklvl == 5 ){
            if(hours == 1){
                interval = inMinutes / 4;
            }else if(hours == 2 || hours == 3 || hours == 4){
                interval = inMinutes / 6;
            } else if (hours == 5 || hours == 6 || hours == 7) {
                interval = inMinutes / 7;
            }else if(hours == 8 || hours == 9 || hours == 10){
                interval = inMinutes / 8;
            }else if(hours == 11 || hours == 12){
                interval = inMinutes / 8;
            }
        }

       // interval = 3;
     //   interval = interval - 1; //minus 1 minute so last checkin comes before job ends
       // long milli = TimeUnit.MINUTES.toMillis(interval);

        long intervalInMilli = TimeUnit.MINUTES.toMillis(interval);
        ((MyApplication) this.getApplication()).setinterval(intervalInMilli);
        return intervalInMilli;

    }

    /*
    * Gets the current system time as the start time
    */
    void saveStartTime(){
        //save current time as start time

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        Date time = cal.getTime();

       // SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext()); // Gets system time format
        String startTime = timeFormat.format(time);
        sharedPreference.saveTimeStart(context, startTime);

    }

    /*
    * Gets the finish time based on the hours user selected
    */
    void getTime(){

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, Hours); // adds one hour
        Date time = cal.getTime(); // returns new date object, one hour in the future

       // SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
       // FinishTime = formatter.format(time);

        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext()); // Gets system time format
        FinishTime = timeFormat.format(time);

        sharedPreference.saveTimeEnd(context, FinishTime);


    }


    //------------->>>>

    void getTimeFromServer(){

        System.out.print(" $$$getTimeFromServer() inCALLED  ");

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    currentTime = jsonResponse.getString("time");

                    setNextCheckinInit();

                    System.out.print(" @@@@@@@@@@@@@@@@@@ ## CURRENT TIME IS = " + currentTime);


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
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(serverTimeRequest);



    }







    void setNextCheckinInit(){

        System.out.print(" $$$setNextCheckin() inCALLED  ");

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


        //Set NextCheckin Value
        long interval = ((MyApplication) this.getApplication()).getinterval();
        //DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext()); // Gets system time format
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        long TestnextTime = System.currentTimeMillis() + interval;
        long nextTime = milliseconds + interval;
        String NextCheckin = formatter.format(nextTime);
        String TestNextCheckin = formatter.format(TestnextTime);
        System.out.print(" @@@@@@@@@@@@@@@@@@ ## NEXT CHECKIN TIME = " + NextCheckin);
        System.out.print(" @@@@@@@@@@@@@@@@@@ ## ACTUAL NEXT CHECKIN TIME = " + TestNextCheckin);


        //Save to db
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr_NEXTCHECK", "["+response+"]");
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

        //get all current checkin Value
        /*
        String checkin1 = ((MyApplication) this.getApplication()).getCheckin1();
        String checkin2 = ((MyApplication) this.getApplication()).getCheckin2();
        String checkin3 = ((MyApplication) this.getApplication()).getCheckin3();
        String checkin4 = ((MyApplication) this.getApplication()).getCheckin4();
        String checkin5 = ((MyApplication) this.getApplication()).getCheckin5();
        String checkin6 = ((MyApplication) this.getApplication()).getCheckin6();
        String checkin7 = ((MyApplication) this.getApplication()).getCheckin7();
        String checkin8 = ((MyApplication) this.getApplication()).getCheckin8();
        */
        //  String NextCheckin = ((MyApplication) this.getApplication()).getNextCheckin();

        nextCheckinRequest nextCheckinRequest = new nextCheckinRequest(job_num, NextCheckin, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(nextCheckinRequest);

    }

    //------------<<<


    //----------------------------------------------------------------------------------------------
    //------------------------------------- Init Location Services ---------------------------------
    //----------------------------------------------------------------------------------------------
    /*
    * Initiate GPS here to ensure GPS permission is turned on and user is notified if its not.
    * Otherwise user only gets prompted when first checkin happens, which tends to cause problems of
    * checkin not appearing.
    */
    public void InitiateGPS(){

         LocationManager locationManager;
         LocationListener listener;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        ,10);
            }
        }
       // locationManager.requestLocationUpdates("gps", 0, 0, listener);
    }

    @Override
    public void onBackPressed() {
        //DO Nothing!
        //This Disables the back button.
    }

    //----------------------------------------------------------------------------------------------
    //------------------------------------- Menu Item - Logout Button ------------------------------
    //----------------------------------------------------------------------------------------------

    //Menu Items on Action bar -- Logout Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                final AlertDialog.Builder logoutcheck = new AlertDialog.Builder(this);
                logoutcheck.setMessage("Are you sure you want to Logout?");
                logoutcheck.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  sharedPreference.clearSharedPreference(context);

                        sharedPreference.removeValue(context,"User");
                        sharedPreference.removeValue(context,"Name");
                        sharedPreference.removeValue(context,"Pass");
                        sharedPreference.removeValue(context,"UserID");
                        sharedPreference.removeValue(context,"TimeStart");
                        sharedPreference.removeValue(context,"FinishTime");
                        sharedPreference.removeValue(context,"SaveRiskLevel");
                        sharedPreference.removeValue(context,"Hours");


                        Intent logoutIntent = new Intent(Settings.this, Login.class);
                        Settings.this.startActivity(logoutIntent);
                        finish();
                    }
                });
                logoutcheck.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                logoutcheck.setTitle("Logout?").create().show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
