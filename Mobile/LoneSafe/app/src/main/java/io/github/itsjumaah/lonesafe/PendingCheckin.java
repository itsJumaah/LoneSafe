package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashneelkumar on 14/03/17.
 */
public class PendingCheckin extends AppCompatActivity {

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
    String NextCheckin = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreference = new SharedPreference();


        SaveToDataBase();
        NextCheckintoDB();

        ForegroundService.NEED_TO_SEND_CHECKIN = false;
        finish();
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
        NextCheckin = ((MyApplication) this.getApplication()).getNextCheckin();


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

        String job_num = sharedPreference.getValue(context,"UserID");
        Log.i("JSON: ", "JOB NUM IS: " + job_num);

        getCheckinValue();

        CheckinRequest checkinRequest = new CheckinRequest(job_num,checkin1, checkin2, checkin3, checkin4, checkin5,
                checkin6, checkin7, checkin8, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PendingCheckin.this);
        queue.add(checkinRequest);
    }

    void NextCheckintoDB(){

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
        getCheckinValue();

        nextCheckinRequest nextCheckinRequest = new nextCheckinRequest(job_num, NextCheckin, responseListener);
        RequestQueue queue = Volley.newRequestQueue(PendingCheckin.this);
        queue.add(nextCheckinRequest);
        //----
    }



}
