package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;
import android.widget.Toast;



public class Settings extends AppCompatActivity {

    private static String strStartTime = "__:__";
    private static String strFinishTime = "__:__";
    int id = 0;
    private static long militime1;
    private static long militime2;
    private static String time;

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreference = new SharedPreference();

        strStartTime = sharedPreference.getValue(context,"TimeStart");
        strFinishTime = sharedPreference.getValue(context,"TimeEnd");

        //Add icon to action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(" LoneSafe");


        // Display user details
        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);
        String message = "Welcome " + sharedPreference.getValue(context,"Name");
        tvWelcomeMsg.setText(message);

        //------------------------------------- SPINNER --------------------------------------------

        final Spinner riskSelector = (Spinner) findViewById(R.id.riskLvlDropdown);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Settings.this,
                  R.array.risk_levels, android.R.layout.simple_spinner_item);

        riskSelector.setAdapter(adapter);

        //Gets last risklevel if available
        id = sharedPreference.getRLPos(context);
        riskSelector.setSelection(id);

        riskSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.rgb(24,143,251));
                ((TextView) parent.getChildAt(0)).setTextSize(20);

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

        //------------------------------------- TIME SELECTION -------------------------------------


        final Button btnStartTime = (Button) findViewById(R.id.btnStartTime);
        final TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvStartTime.setText(strStartTime);

        assert btnStartTime != null;
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePicker();
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
                String selectedItem = riskSelector.getSelectedItem().toString();
                sharedPreference.saveRL(context, selectedItem);

                if (strStartTime != null && strFinishTime != null){
                    Intent intent = new Intent(Settings.this, Home.class);
                    intent.putExtra("getdata", militime2);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Settings.this, "Please select start and finish time", Toast.LENGTH_SHORT).show();
                    // TODO: change this toast to alertdialog?
                }

            }
        });
    }

    void startTimePicker (){
        final TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String format = "";
                if (selectedHour == 0) {
                    selectedHour += 12;
                    format = "AM";
                }
                else if (selectedHour == 12) {
                    format = "PM";
                } else if (selectedHour > 12) {
                    selectedHour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }
                tvStartTime.setText(String.format("%02d:%02d ",selectedHour,selectedMinute)+ format);
                strStartTime = tvStartTime.getText().toString();
                sharedPreference.saveTimeStart(context, strStartTime);

                //Calculate millisec for progress bar on home -- Might not need this once we have server --
                //TODO Remove?
                long hour = TimeUnit.MILLISECONDS.convert(selectedHour,TimeUnit.HOURS);
                long min = TimeUnit.MILLISECONDS.convert(selectedMinute,TimeUnit.MINUTES);
                militime1 = hour + min;

            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select START Time");
        mTimePicker.show();

    }

    void finishTimePicker() {
        final TextView tvFinishTime = (TextView) findViewById(R.id.tvEndTime);
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String format = "";
                if (selectedHour == 0) {
                    selectedHour += 12;
                    format = "AM";
                }
                else if (selectedHour == 12) {
                    format = "PM";
                } else if (selectedHour > 12) {
                    selectedHour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }
                tvFinishTime.setText(String.format("%02d:%02d ",selectedHour,selectedMinute)+ format);
                strFinishTime = tvFinishTime.getText().toString();
                sharedPreference.saveTimeEnd(context, strFinishTime);

                //Calculate millisec for progress bar on home -- Might not need this once we have server --
                //TODO Remove?
                long hour = TimeUnit.MILLISECONDS.convert(selectedHour,TimeUnit.HOURS);
                long min = TimeUnit.MILLISECONDS.convert(selectedMinute,TimeUnit.MINUTES);
                militime2 = (hour + min) - militime1;

            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select FINISH Time");
        mTimePicker.show();
    }
    //------------------------------------- Menu Item - Logout Button ------------------------------
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
                logoutcheck.setMessage("Once you logout, you preferences will be deleted. You'll need to log back in to use the application again");
                logoutcheck.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreference.clearSharedPreference(context);
                        Intent logoutIntent = new Intent(Settings.this, Login.class);
                        Settings.this.startActivity(logoutIntent);
                    }
                });
                logoutcheck.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                logoutcheck.setTitle("Do you want to logout?").create().show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}

