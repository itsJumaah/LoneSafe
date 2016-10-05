package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

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
    ProgressBar loadProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreference = new SharedPreference();

        //init action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(" LoneSafe");


        // Display user details
        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);
        String message = "Welcome " + sharedPreference.getValue(context,"Name");
        tvWelcomeMsg.setText(message);


        //------------------------------------------------------------------------------------------
        //------------------------------------- RISK LEVEL & HOURS PICKER --------------------------
        //------------------------------------------------------------------------------------------


        final String getRL = sharedPreference.getValue(context,"SaveRiskLevel");
        if (getRL != null){
            RiskLevel = Integer.parseInt(getRL);
            System.out.println("---------> #RISKLEVELSP = " + RiskLevel);
        }

        android.widget.NumberPicker npRiskLvl = (android.widget.NumberPicker) findViewById(R.id.numberPicker2);
       // String[] num = new String[4];
       // String[] arrayString= new String[]{"5 - Very High","4 - High","3 - Medium","2 - Low","1 - Very Low"};
       // for(int i=0; i<num.length; i++)
       //     num[i] = Integer.toString(i);

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

                // Toast.makeText(Settings.this, "Value was: " + Integer.toString(oldVal) + " is now: " + Integer.toString(RiskLevel), Toast.LENGTH_SHORT).show();

            }
        });

        //------------------------------------------------------------------------------------------

        final String getHour = sharedPreference.getValue(context,"Hours");
        if (getHour != null){
            Hours = Integer.parseInt(getHour);
            System.out.println("---------> #HoursSP = " + Hours);
        }

        NumberPicker npHours = (NumberPicker) findViewById(R.id.numberPicker);
       // String[] nums = new String[23];
        //  for(int i=0; i<nums.length; i++)
      //      nums[i] = Integer.toString(i);
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

              //  Toast.makeText(Settings.this, "Value was: " + Integer.toString(oldVal) + " is now: " + Integer.toString(Hours), Toast.LENGTH_SHORT).show();

              //  getTime();
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
                            createJobDB();
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

                        //TODO note temp storing as userid, change to jobid in sharedpref
                        int job_num = jsonResponse.getInt("job_num");
                        String userId = String.valueOf(job_num);

                        sharedPreference.saveUserID(context, userId);

                        Log.i("JSON: ", "JOB ID = " + userId);

                        updateOnJob();
                        updateJobActive();

                        //START FOREGROUND SERVICE
                        Intent service = new Intent(Settings.this, ForegroundService.class);
                        service.putExtra("jobTime", Hours);//
                        service.putExtra("interval",interval);

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
        String workinghours = sharedPreference.getValue(context,"Hours");
        String risklevel = sharedPreference.getValue(context,"SaveRiskLevel");

        InsertDataRequest insertDataRequest = new InsertDataRequest(username, workinghours, risklevel ,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Settings.this);
        queue.add(insertDataRequest);

    }
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
        interval = interval - 1; //minus 1 minute so last checkin comes before job ends
       // long milli = TimeUnit.MINUTES.toMillis(interval);

        long intervalInMilli = TimeUnit.MINUTES.toMillis(interval);
        ((MyApplication) this.getApplication()).setinterval(intervalInMilli);
        return intervalInMilli;

    }

    void saveStartTime(){
        //save current time as start time

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        Date time = cal.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        String startTime = formatter.format(time);

        sharedPreference.saveTimeStart(context, startTime);

    }

    void getTime(){

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, Hours); // adds one hour
        Date time = cal.getTime(); // returns new date object, one hour in the future

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        FinishTime = formatter.format(time);

        sharedPreference.saveTimeEnd(context, FinishTime);


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
                        sharedPreference.clearSharedPreference(context);
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
