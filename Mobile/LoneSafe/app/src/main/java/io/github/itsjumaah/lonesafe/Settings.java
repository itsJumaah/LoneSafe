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
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Settings extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref
    int Hours = 1;
    int RiskLevel = 1;
    String FinishTime;


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
        String[] num = new String[4];
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

                Toast.makeText(Settings.this, "Value was: " + Integer.toString(oldVal) + " is now: " + Integer.toString(RiskLevel), Toast.LENGTH_SHORT).show();

            }
        });

        //------------------------------------------------------------------------------------------

        final String getHour = sharedPreference.getValue(context,"Hours");
        if (getHour != null){
            Hours = Integer.parseInt(getHour);
            System.out.println("---------> #HoursSP = " + Hours);
        }

        NumberPicker npHours = (NumberPicker) findViewById(R.id.numberPicker);
        String[] nums = new String[23];
        //  for(int i=0; i<nums.length; i++)
      //      nums[i] = Integer.toString(i);
        String[] test = getResources().getStringArray(R.array.hours);

        npHours.setMinValue(0);
        npHours.setMaxValue(23);
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

                Toast.makeText(Settings.this, "Value was: " + Integer.toString(oldVal) + " is now: " + Integer.toString(Hours), Toast.LENGTH_SHORT).show();

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
                getTime();

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
                }

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

                final AlertDialog.Builder confirm = new AlertDialog.Builder(Settings.this);
                confirm.setMessage("RiskLevel: " + RiskLevel + "\n\nHours: " + Hours + "\n\n You will finish at: " + FinishTime);
                confirm.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //START FOREGROUND SERVICE
                        Intent service = new Intent(Settings.this, ForegroundService.class);
                        if (!ForegroundService.IS_SERVICE_RUNNING) {
                            service.setAction(ForegroundService.Constants.ACTION.STARTFOREGROUND_ACTION);
                            ForegroundService.IS_SERVICE_RUNNING = true;
                        }
                        startService(service);

                        Intent intent = new Intent(Settings.this, Home.class);
                        Settings.this.startActivity(intent);
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
            }
        });

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
