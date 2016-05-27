package io.github.itsjumaah.lonesafe;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import java.util.Calendar;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;
import android.widget.Toast;



public class Settings extends AppCompatActivity {

    public static String strStartTime;
    public static String strFinishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);
        // Display user details
        String message ="You are logged in as " + name;
        tvWelcomeMsg.setText(message);

        //------------------------------------- SPINNER ---------------

        final Spinner riskSelector = (Spinner) findViewById(R.id.riskLvlDropdown);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Settings.this,
                  R.array.risk_levels, android.R.layout.simple_spinner_item);


        riskSelector.setAdapter(adapter);

        riskSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Risk Level : " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                //saves selected risk level as STRING
               // riskLevelTxt = riskSelector.getSelectedItem().toString();
               // riskLevelTxt = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //Maybe select the highest OR lowest as default if no selection made ??

            }
        });

        //------------------------------------- TIME SELECTION ---------------


        final Button btnStartTime = (Button) findViewById(R.id.btnStartTime);
        assert btnStartTime != null;
        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePicker();
            }
        });

        final Button btnFinishTime = (Button) findViewById(R.id.btnFinishTime);
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
                Intent i = new Intent(Settings.this, Home.class);
                String selectedItem = riskSelector.getSelectedItem().toString();
                i.putExtra("getdata", selectedItem);
                i.putExtra("getStartTime",strStartTime);
                i.putExtra("getFinishTime", strFinishTime);

                startActivity(i);
            }
        });
    }

    void startTimePicker (){
        final TextView tvStartTime = (TextView) findViewById(R.id.tvStartTime);
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

                // tvStartTime.setText(new StringBuilder().append(String.format("%02d:%02d ",selectedHour,selectedMinute)).append(format));
                tvStartTime.setText(String.format("%02d:%02d ",selectedHour,selectedMinute)+ format);
                strStartTime = tvStartTime.getText().toString();

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

            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select FINISH Time");
        mTimePicker.show();
    }
    /*
    // Also need to return int for correct hour format -- so can't use function
    public String timeFormat(int hour){
        String format = "";
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
        return format;
    }
    */

}

