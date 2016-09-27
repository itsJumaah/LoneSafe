package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SosActivity extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    String sos = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        sharedPreference = new SharedPreference();


        //  this.setFinishOnTouchOutside(false);
        //Custom size for the dialog --- Delete if not using
        //WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.height = 2000;
        //params.width = 1200;
        //getWindow().setGravity(Gravity.CENTER);
       // getWindow().setAttributes(params);


        final Button btnCancel = (Button) findViewById(R.id.btnCancel);

        assert btnCancel != null;
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SosActivity.this,Home.class);
                startActivity(intent);
            }
        });

        final Button btnHelp = (Button) findViewById(R.id.btnHelp);

        assert btnHelp != null;
        btnHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //setSOS();
                sos = "1";
                saveSosToDB();

                /*
                Intent toServiceIntent = new Intent(SosActivity.this,ForegroundService.class);
                if (ForegroundService.IS_SERVICE_RUNNING) {
                    toServiceIntent.setAction(ForegroundService.Constants.ACTION.SOS_ACTION);
                    startService(toServiceIntent);
                }
                */

                final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
                sosAlert.setMessage("Someone will be notified!");
                sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                        Intent intent = new Intent(SosActivity.this,Home.class);
                        startActivity(intent);
                    }
                });
                sosAlert.setIcon(R.mipmap.ic_launcher);
                sosAlert.setCancelable(false);
                sosAlert.setTitle("S.O.S").create().show();
            }
        });
    }
/*
    void setSOS(){
        if(ForegroundService.counter > 1){
            sosCounter = ForegroundService.counter - 1;
        }
        else if(ForegroundService.counter == 8){
            sosCounter = ForegroundService.counter;
        }
        else{
            sosCounter = 1;
        }
        //---------
        if(sosCounter == 1){
            ((MyApplication) this.getApplication()).setCheckin1("sox");
        }
        else if(sosCounter == 2 ){
            ((MyApplication) this.getApplication()).setCheckin2("sox");
        }
        else if(sosCounter == 3 ){
            ((MyApplication) this.getApplication()).setCheckin3("sox");
        }
        else if(sosCounter == 4 ){
            ((MyApplication) this.getApplication()).setCheckin4("sox");
        }
        else if(sosCounter == 5 ){
            ((MyApplication) this.getApplication()).setCheckin5("sox");
        }
        else if(sosCounter == 6 ){
            ((MyApplication) this.getApplication()).setCheckin6("sox");
        }
        else if(sosCounter == 7 ){
            ((MyApplication) this.getApplication()).setCheckin7("sox");
        }
        else if(sosCounter == 8 ){
            ((MyApplication) this.getApplication()).setCheckin8("sox");
        }

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

        String checkin1 = ((MyApplication) this.getApplication()).getCheckin1();
        String checkin2 = ((MyApplication) this.getApplication()).getCheckin2();
        String checkin3 = ((MyApplication) this.getApplication()).getCheckin3();
        String checkin4 = ((MyApplication) this.getApplication()).getCheckin4();
        String checkin5 = ((MyApplication) this.getApplication()).getCheckin5();
        String checkin6 = ((MyApplication) this.getApplication()).getCheckin6();
        String checkin7 = ((MyApplication) this.getApplication()).getCheckin7();
        String checkin8 = ((MyApplication) this.getApplication()).getCheckin8();

        CheckinRequest checkinRequest = new CheckinRequest(job_num,checkin1, checkin2, checkin3, checkin4, checkin5,
                checkin6, checkin7, checkin8, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SosActivity.this);
        queue.add(checkinRequest);

    }
    */
    void saveSosToDB (){

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

        sos = "1";

        SosRequest sosRequest = new SosRequest(job_num, sos, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SosActivity.this);
        queue.add(sosRequest);


    }
}