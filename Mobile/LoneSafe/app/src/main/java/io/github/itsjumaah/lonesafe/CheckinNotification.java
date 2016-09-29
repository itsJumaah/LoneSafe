package io.github.itsjumaah.lonesafe;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckinNotification extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref

    boolean checkedOnce = true;
    String checkin1 = "null";
    String checkin2 = "null";
    String checkin3 = "null";
    String checkin4 = "null";
    String checkin5 = "null";
    String checkin6 = "null";
    String checkin7 = "null";
    String checkin8 = "null";

    String Checkedin = "Checked";
    String Escalation1 = "l1";
    String Escalation2 = "l2";
    String Escalation3 = "l3";



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
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setInterpolator(new FastOutSlowInInterpolator());
        scaleDown.setDuration(350);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();

        getCheckinValue();
        DisplayNotification();

    }

    void DisplayNotification(){

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 1000, 1000};

        final CountDownTimer countDownTimer;
        countDownTimer = new CountDownTimer(30000, 1000) { //Phone would ring for 30 seconds
            //If user hasn't checked in
            public void onFinish() {

                ringtone.stop();
                vibrator.cancel();

                ForegroundService.EscalationCounter++;
                setCheckinValue();
                SaveToDataBase();


                if(ForegroundService.EscalationCounter == 1 || ForegroundService.EscalationCounter == 2){
                    Log.i("*** Escalation1/2", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);

                    Intent toServiceIntent = new Intent(CheckinNotification.this,ForegroundService.class);
                    if (ForegroundService.IS_SERVICE_RUNNING) {
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.ESCALATION_ACTION);
                        startService(toServiceIntent);
                    }
                    finish();
                }
                if(ForegroundService.EscalationCounter == 3){
                    Log.i("*** Escalation3", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);
                    ForegroundService.EscalationCounter = 0;
                    Log.i("*** Escalation3->0", "++++++++++++++++++++++++++++++++++++++++++++++++++++++++>> ESCAl counter is: " + ForegroundService.EscalationCounter);

                    Intent toServiceIntent = new Intent(CheckinNotification.this,ForegroundService.class);
                    if (ForegroundService.IS_SERVICE_RUNNING) {
                        toServiceIntent.setAction(ForegroundService.Constants.ACTION.CHECKIN_ACTION);
                        startService(toServiceIntent);
                    }
                    finish();
                }

            }

            public void onTick(long millisUntilFinished) {
            }
        };
        countDownTimer.start();

        ringtone.play();
        vibrator.vibrate(pattern, 0);

//        final Button btnCheckin = (Button) findViewById(R.id.btnCheckin);
        final View ImageView = findViewById(R.id.imageView4);

     //   assert btnCheckin != null;
        assert ImageView != null;

        ImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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

                if (ForegroundService.IS_SERVICE_RUNNING) {
                    toServiceIntent.setAction(ForegroundService.Constants.ACTION.CHECKIN_ACTION);
                    startService(toServiceIntent);
                }
                finish();

            }
        });
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

    @Override
    public void onBackPressed() {
        //DO Nothing!
        //This Disables the back button.
    }


}
