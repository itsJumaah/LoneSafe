package io.github.itsjumaah.lonesafe;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//---

public class CheckinNotification extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref

    String checkin1 = "null";
    String checkin2 = "null";
    String checkin3 = "null";
    String checkin4 = "null";
    String checkin5 = "null";
    String checkin6 = "null";
    String checkin7 = "null";
    String checkin8 = "null";

    String Checkedin = "Checked";
    String Escalation1 = "1";
    String Escalation2 = "2";
    String Escalation3 = "3";
    CountDownTimer countDownTimer;

    private LocationManager locationManager;
    private LocationListener listener;
    String lng;
    String lat;
    boolean gps_enabled = false;
    boolean network_enabled = false;

    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
    private Ringtone ringtone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.activity_checkin);

        sharedPreference = new SharedPreference();

     //   checkinCounter = ((MyApplication) this.getApplication()).getCheckinCounter();
       // ForegroundService.counter = 1;

        final View ImageView = findViewById(R.id.imageView4);

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(ImageView,
                PropertyValuesHolder.ofFloat("scaleX", 1.4f),
                PropertyValuesHolder.ofFloat("scaleY", 1.4f));
        scaleDown.setInterpolator(new FastOutSlowInInterpolator());
        scaleDown.setDuration(350);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
               // t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                double longitude = location.getLongitude();
                double latitude =  location.getLatitude();

                lng =  String.valueOf(longitude);
                lat = String.valueOf(latitude);

                try {
                    locationManager.removeUpdates(listener);
                    Log.d("SOSLOCATION","SOS LOCATION STOPPED!!");
                } catch (SecurityException e) {
                    Log.e("PERMISSION_EXCEPTION","PERMISSION_NOT_GRANTED");
                }
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
        getLocation();
        getCheckinValue();
        DisplayNotification();
    }

   void getLocation(){
       // first check for permissions
       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                       ,10);
           }
           return;
       }

       // this code won't execute IF permissions are not allowed, because in the line above there is return statement.

       locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
       System.out.println("CHECKIN LOCATION ACQUIRED _ NETWORK!!");


       // gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
      // network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

       /*
       if (gps_enabled){
           System.out.println("CHECKIN LOCATION ACQUIRED _GPS!!");
           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

       }
       else if (network_enabled){
           System.out.println("CHECKIN LOCATION ACQUIRED _ NETWORK!!");
           locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
       }
       */
   }

    //----------------------------------------------------------------------------------------------

    void DisplayNotification(){

/*
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 1000, 1000};

        */

        countDownTimer = new CountDownTimer(30000, 1000) { //Phone would ring for 30 seconds
            //If user hasn't checked in
            public void onFinish() {

                Log.i("Onfinish", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> OnFinish called ");
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);;

                ringtone.stop();
                vibrator.cancel();

                ForegroundService.EscalationCounter++;
                setCheckinValue();
                SaveToDataBase();


                if(ForegroundService.EscalationCounter == 1 || ForegroundService.EscalationCounter == 2){
                    Log.i("*** Escalation1/2", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);

                   // getLocation();
                    SaveLocationToDB();


                    Intent toServiceIntent = new Intent(CheckinNotification.this,ForegroundService.class);

                    /*
                    if(ForegroundService.IS_SERVICE_RUNNING && ForegroundService.LAST_CHECKIN){
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.STOPFOREGROUND_ACTION);
                        startService(toServiceIntent);
                        finish();
                    }
                    else
                    */
                    if (ForegroundService.IS_SERVICE_RUNNING) {
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.ESCALATION_ACTION);
                        startService(toServiceIntent);
                        finish();
                    }
                    else {
                        finish();
                    }

                }
                if(ForegroundService.EscalationCounter == 3){

                   // getLocation();
                    SaveLocationToDB();

                    Log.i("*** Escalation3", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);
                    ForegroundService.EscalationCounter = 0;
                    Log.i("*** Escalation3->0", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);

                    Intent toServiceIntent = new Intent(CheckinNotification.this,ForegroundService.class);
                    if(ForegroundService.IS_SERVICE_RUNNING && ForegroundService.LAST_CHECKIN){
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.STOPFOREGROUND_ACTION);
                        Toast.makeText(CheckinNotification.this, "Job Ended!", Toast.LENGTH_LONG).show();
                        startService(toServiceIntent);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            finishAffinity();
                        }
                        else {
                            finish();
                        }
                    }
                    else if (ForegroundService.IS_SERVICE_RUNNING) {
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.CHECKIN_ACTION);
                        startService(toServiceIntent);
                        finish();
                    }
                    else {
                        finish();
                    }
                }

            }

            public void onTick(long millisUntilFinished) {
            }
        };
        countDownTimer.start();

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);;
        long[] pattern = {0, 1000, 1000};

        ringtone = RingtoneManager.getRingtone(this, uri);
        ringtone.play();
        vibrator.vibrate(pattern, 0);

//        final Button btnCheckin = (Button) findViewById(R.id.btnCheckin);
        final View ImageView = findViewById(R.id.imageView4);

     //   assert btnCheckin != null;
        assert ImageView != null;

        ImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userCheckedIn();
            }
        });
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        userCheckedIn();

    }

    void userCheckedIn(){
       // Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
       // final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        ringtone.stop();
        vibrator.cancel();
        countDownTimer.cancel();

        if(ForegroundService.EscalationCounter == 1){
            Escalation1 = "Escalation 1";
        }
        else if(ForegroundService.EscalationCounter == 2){
            Escalation2 = "Escalation 2";
        }
        else if(ForegroundService.EscalationCounter ==3){
            Escalation3 = "Escalation 3";
        }

        setCheckinValue();
        SaveToDataBase();

        ForegroundService.EscalationCounter = 0;
        ForegroundService.counter++;


        Intent toServiceIntent = new Intent(CheckinNotification.this,ForegroundService.class);

        if(ForegroundService.IS_SERVICE_RUNNING && ForegroundService.LAST_CHECKIN){
            toServiceIntent.setAction(ForegroundService.Constants.ACTION.STOPFOREGROUND_ACTION);
            System.out.println("STOP Service called from Checkin...");
            Toast.makeText(CheckinNotification.this, "Job Ended!", Toast.LENGTH_LONG).show();
            startService(toServiceIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                finishAffinity();
            }
            else {
                finish();
            }

        }
        else if (ForegroundService.IS_SERVICE_RUNNING) {
            toServiceIntent.setAction(ForegroundService.Constants.ACTION.CHECKIN_ACTION);
            startService(toServiceIntent);
            finish();
        }
        else {
            finish();
        }

       // finish();
    }

    void setCheckinValue(){

        if(ForegroundService.counter == 1){
           if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin1(Checkedin);
                //ForegroundService.counter = 2;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin1(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin1(Escalation2);

            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin1(Escalation3);

               // ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 2;
            }
            Log.i("****1 COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 2 ){
            ((MyApplication) this.getApplication()).setCheckin1("null");
            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin2(Checkedin);
               // ForegroundService.counter = 3;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin2(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin2(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin2(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 3;
            }
            Log.i("****2 COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 3 ){
            ((MyApplication) this.getApplication()).setCheckin2("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin3(Checkedin);
                //ForegroundService.counter = 4;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin3(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin3(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin3(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 4;
            }
            Log.i("****3 COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 4 ){
            ((MyApplication) this.getApplication()).setCheckin3("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin4(Checkedin);
               // ForegroundService.counter = 5;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin4(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin4(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin4(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 5;
            }
            Log.i("****4 COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 5 ){
            ((MyApplication) this.getApplication()).setCheckin4("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin5(Checkedin);
               // ForegroundService.counter = 6;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin5(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin5(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin5(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 6;
            }
            Log.i("** COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 6 ){
            ((MyApplication) this.getApplication()).setCheckin5("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin6(Checkedin);
              //  ForegroundService.counter = 7;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin6(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin6(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin6(Escalation3);
             //   ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 7;
            }
            Log.i("** COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 7 ){
            ((MyApplication) this.getApplication()).setCheckin6("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin7(Checkedin);
               // ForegroundService.counter = 8;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin7(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin7(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin7(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 8;
            }
            Log.i("** COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
        else if(ForegroundService.counter == 8 ){
            ((MyApplication) this.getApplication()).setCheckin7("null");

            if(ForegroundService.EscalationCounter == 0){
                ((MyApplication) this.getApplication()).setCheckin8(Checkedin);
               // ForegroundService.counter = -1;
            }
            else if(ForegroundService.EscalationCounter == 1){
                ((MyApplication) this.getApplication()).setCheckin8(Escalation1);
            }
            else if(ForegroundService.EscalationCounter == 2){
                ((MyApplication) this.getApplication()).setCheckin8(Escalation2);
            }
            else if(ForegroundService.EscalationCounter == 3){
                ((MyApplication) this.getApplication()).setCheckin8(Escalation3);
              //  ForegroundService.EscalationCounter = 0;
                ForegroundService.counter = 9;
            }
            Log.i("** COUNTER", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> Checkin counter is: " + ForegroundService.counter);
        }
    }
    void getCheckinValue(){
        //get all current checkin Value
        checkin1 = ((MyApplication) this.getApplication()).getCheckin1();
        checkin2 = ((MyApplication) this.getApplication()).getCheckin2();
        checkin3 = ((MyApplication) this.getApplication()).getCheckin3();
        checkin4 = ((MyApplication) this.getApplication()).getCheckin4();
        checkin5 = ((MyApplication) this.getApplication()).getCheckin5();
        checkin6 = ((MyApplication) this.getApplication()).getCheckin6();
        checkin7 = ((MyApplication) this.getApplication()).getCheckin7();
        checkin8 = ((MyApplication) this.getApplication()).getCheckin8();

    }

    void SaveToDataBase(){
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

        getCheckinValue();

        CheckinRequest checkinRequest = new CheckinRequest(job_num,checkin1, checkin2, checkin3, checkin4, checkin5,
                checkin6, checkin7, checkin8, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CheckinNotification.this);
        queue.add(checkinRequest);
    }


    //----------------------------------------------------------------------------------------------

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


        LocationRequest locationRequest = new LocationRequest(job_num, lng, lat, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CheckinNotification.this);
        queue.add(locationRequest);

    }


    @Override
    public void onBackPressed() {
        //DO Nothing!
        //This Disables the back button.
    }


}
