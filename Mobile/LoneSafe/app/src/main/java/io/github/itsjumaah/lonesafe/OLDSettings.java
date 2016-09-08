package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class OLDSettings extends AppCompatActivity {

    private static String strStartTime = "";
    private static String strFinishTime = "";
    int id = 0;        //For RiskLevel Spinner
    private static String getTimeDifference;


    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_settings);

        sharedPreference = new SharedPreference();
        strFinishTime = sharedPreference.getValue(context,"TimeEnd"); //Gets FinishTime from sharedPref

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
        //------------------------------------- RISK LEVEL SPINNER ---------------------------------
        //------------------------------------------------------------------------------------------

        /*
        final Spinner riskSelector = (Spinner) findViewById(R.id.riskLvlDropdown);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(OLDSettings.this,
                  R.array.risk_levels,  R.layout.spinner_item);

        riskSelector.setAdapter(adapter);

        //Gets last risklevel if available
        id = sharedPreference.getRLPos(context);
        riskSelector.setSelection(id);

        riskSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 //   ((TextView) parent.getChildAt(0)).setTextColor(Color.rgb(24,143,251));
                 //   ((TextView) parent.getChildAt(0)).setTextSize(20);


                Toast.makeText(parent.getContext(),
                        "Risk Level : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();

                sharedPreference.setRLPos(context, position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DO Nothing

            }
        });

        //------------------------------------------------------------------------------------------
        //-------------------- Buttons Init (StartTime, FinishTime, Start) -------------------------
        // -----------------------------------------------------------------------------------------
        startTimeSet();

        final Button btnStartTime = (Button) findViewById(R.id.btnStartTime);
        assert btnStartTime != null;
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeSet();

                Intent test = new Intent(OLDSettings.this, Settings.class );
                OLDSettings.this.startActivity(test);

            }
        });



        final Button btnFinishTime = (Button) findViewById(R.id.btnFinishTime);
        final TextView tvFinishTime = (TextView) findViewById(R.id.tvEndTime);
        tvFinishTime.setText(strFinishTime);

        assert btnFinishTime != null;
        btnFinishTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTimePicker();
            }
        });

        final Button btnStart = (Button) findViewById(R.id.btnStart);
        assert btnStart !=null;
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Alert user if no network connection
                if(!NetworkChangeReceiver.isNetworkStatusAvialable(context)){
                    AlertDialog.Builder alert = new AlertDialog.Builder(OLDSettings.this);
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
                }
                //Refresh start time, so it is current time
                startTimeSet();

                final String selectedItem = riskSelector.getSelectedItem().toString();
                sharedPreference.saveRL(context, selectedItem);
                final String User_id =  sharedPreference.getValue(context,"UserID");
                final String startTime = sharedPreference.getValue(context,"TimeStart");
                final String endTime = sharedPreference.getValue(context,"TimeEnd");
               // final String rskLevel = sharedPreference.getValue(context,"RiskLevel");

               // onFieldHours
                getTimeDifference = getTimeDifference(strStartTime,strFinishTime);

                if (strStartTime != null && strFinishTime != null){
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                    final AlertDialog.Builder confirm = new AlertDialog.Builder(OLDSettings.this);
                                    confirm.setMessage("Start: " +  strStartTime+ "  |  " + "Finish: " + strFinishTime
                                    +"\n\nRisk Level: " + selectedItem + "\n\nHours: " + getTimeDifference);
                                    confirm.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //START FOREGROUND SERVICE
                                            Intent service = new Intent(OLDSettings.this, ForegroundService.class);
                                            if (!ForegroundService.IS_SERVICE_RUNNING) {
                                                service.setAction(ForegroundService.Constants.ACTION.STARTFOREGROUND_ACTION);
                                                ForegroundService.IS_SERVICE_RUNNING = true;
                                            }
                                            startService(service);

                                            Intent intent = new Intent(OLDSettings.this, Home.class);
                                            OLDSettings.this.startActivity(intent);
                                            finish();
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

                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(OLDSettings.this);
                                    builder.setMessage("Something went wrong!")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    //TEST CODE----------------------
                    System.out.println("--> START TIME (SP): " + startTime);
                    System.out.println("--> END TIME (SP): " + endTime);
                    //----------------

                    InsertDataRequest registerRequest = new InsertDataRequest(User_id, endTime, selectedItem, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(OLDSettings.this);
                    queue.add(registerRequest);
                }
                else {
                    Toast.makeText(OLDSettings.this, "Please Select Finish Time", Toast.LENGTH_LONG).show();
                }
            }
        });

        */
    }

    //----------------------------------------------------------------------------------------------
    //------------------------------------- TIME SELECTION -----------------------------------------
    //----------------------------------------------------------------------------------------------

    /*

    void startTimeSet (){
        final TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        strStartTime = timeFormat(hour, minute);
        tvStartTime.setText(strStartTime);
        sharedPreference.saveTimeStart(context, strStartTime);
        System.out.println("-- START TIME: " + strStartTime);

    }

    void finishTimePicker() {

        final TextView tvFinishTime = (TextView) findViewById(R.id.tvEndTime);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(OLDSettings.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                strFinishTime = timeFormat(selectedHour,selectedMinute);
                tvFinishTime.setText(strFinishTime);
                sharedPreference.saveTimeEnd(context, strFinishTime);
                System.out.println("-- END TIME: " + strFinishTime);
            }

        }, hour, minute, DateFormat.is24HourFormat(context));//No 24 hour time
        mTimePicker.setTitle("What time will you finish?");
        mTimePicker.show();
    }

    //Formats the time in AM/PM format. HH:mm: aa
    public String timeFormat(int hour, int minute){
        String format;
        String formattedTime;
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        formattedTime = (String.format(Locale.getDefault(),"%02d:%02d ",hour,minute)+ format);
        return formattedTime;
    }

    //Calculates job Hours Given start and end time
    public static String getTimeDifference(String startTime,String endTime){
        try{
          //  Date time1 = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).parse(endTime);
          //  Calendar calendar1 = Calendar.getInstance();
          //  calendar1.setTime(time1);
          //  Date time2 = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).parse(startTime);
            // Calendar calendar2 = Calendar.getInstance();
            //   calendar2.setTime(time2);

            //Converts startTime to 24hr Format
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            Date date11 = parseFormat.parse(startTime);
            System.out.println("TESTTET--START " + parseFormat.format(date11) + " = " + displayFormat.format(date11));

            Date date22 = parseFormat.parse(endTime);
            System.out.println("TESTTET--END " + parseFormat.format(date22) + " = " + displayFormat.format(date22));


            //TEST
          //  SimpleDateFormat df = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
         //   Date date1 = df.parse(startTime);
         //   Date date2 = df.parse(endTime);
         //   long difference = time2.getTime() - time1.getTime();

            long difference = date22.getTime() - date11.getTime();

            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);


            //TEST CODE
            System.out.println("########### ------> START TIME: " + date11.getTime());
            System.out.println("########### ------> END  TIME: " + date22.getTime());
            System.out.println("########### ------> DIFF  TIME: " + difference);

            //-------


            hours = (hours < 0 ? -hours : hours);
            min = (min < 0 ? -min : min);

            String totalDiff = (hours + " Hour(s) " + min + " minutes");

            Log.i("======= Hours"," :: "+hours + " " + min);


            return totalDiff;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";

    }

    */


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
                        Intent logoutIntent = new Intent(OLDSettings.this, Login.class);
                        OLDSettings.this.startActivity(logoutIntent);
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

