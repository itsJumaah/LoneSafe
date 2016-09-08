package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    int i = 0;
    long milli;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //--- action bar icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(" LoneSafe");


        sharedPreference = new SharedPreference();

        // Display user details
        final TextView tvWelcome = (TextView) findViewById(R.id.tvUser1);
        String message = "Welcome " + sharedPreference.getValue(context,"Name");
        tvWelcome.setText(message);

        String Value = sharedPreference.getValue(context,"SaveRiskLevel");

        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(Value);

        //Get current time //------------------
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        Date time = cal.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        String startTime = formatter.format(time);

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
        milli = TimeUnit.HOURS.toMillis(Hours);


        //--------------------------------------|


        //TODO Delete from Shared Preferences
        /*
        //Retrieve a value from SharedPreference
        String startTime = sharedPreference.getValue(context,"TimeStart");
        final TextView tvStTime = (TextView) findViewById(R.id.tvStTime);
        tvStTime.setText(startTime);

        String FinishTime = sharedPreference.getValue(context,"TimeEnd");
        final TextView tvFnTime = (TextView) findViewById(R.id.tvFnTime);
        tvFnTime.setText(FinishTime);
        */

        final Button btnStop = (Button) findViewById(R.id.btnStop);
        assert btnStop != null;
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                countDownTimer.onFinish();

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
                    final AlertDialog.Builder logoutcheck = new AlertDialog.Builder(Home.this);
                    logoutcheck.setMessage("Are you sure you want to stop the job?");
                    logoutcheck.setPositiveButton("Yes, Stop", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //STOP FOREGROUND SERVICE
                            Intent service = new Intent(Home.this, ForegroundService.class);
                            if (ForegroundService.IS_SERVICE_RUNNING) {
                                service.setAction(ForegroundService.Constants.ACTION.STOPFOREGROUND_ACTION);
                                ForegroundService.IS_SERVICE_RUNNING = false;
                            }
                            startService(service);

                            Intent intent = new Intent(Home.this, Settings.class);
                            Home.this.startActivity(intent);
                        }
                    });
                    logoutcheck.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    logoutcheck.setTitle("Stop Job?").create().show();
                }
            }
        });

        final Button btnSOS = (Button) findViewById(R.id.btnSOS);
        assert btnSOS != null;
        btnSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent GotoCustomActivityDialog = new Intent(Home.this, SosActivity.class);
               // startActivity(GotoCustomActivityDialog);
                Intent sosintent = new Intent(Home.this, SosActivity.class);
                Home.this.startActivity(sosintent);

            }
        });


//------------------ Countdown Timer and Progress bar -- temp only until server is working ---

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(i);


       final TextView tvTimer = (TextView) findViewById(R.id.tvTimer);


        countDownTimer = new CountDownTimer(milli, 60000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Long minleft = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                tvTimer.setText(" " + minleft + " minutes");
                //tvTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                progressBar.setProgress(i);

            }
            @Override
            public void onFinish() {
                i++;
                progressBar.setProgress(i);
                Toast.makeText(Home.this,"Job Finished",Toast.LENGTH_LONG).show();
            }
        };
        countDownTimer.start();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //TODO Remove logout option from Homepage?

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
                        Intent logoutIntent = new Intent(Home.this, Login.class);
                        Home.this.startActivity(logoutIntent);
                        finish();
                    }
                });
                logoutcheck.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                logoutcheck.setTitle("logout?").create().show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}



