package io.github.itsjumaah.lonesafe;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class Settings extends AppCompatActivity {

//    private TextView tvStartTime;
    private Calendar calendar;
    private String format = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);

        // Display user details
        String message ="You are logged in as " + name;
        tvWelcomeMsg.setText(message);

        //Spinner riskSelector = (Spinner) findViewById(R.id.riskLvlDropdown);

       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Settings.this,
          //      R.array.risk_levels, android.R.layout.simple_spinner_item);

        //riskSelector.setAdapter(adapter);

        // Spinner click listener
       // riskSelector.setOnItemSelectedListener(this);

        final TextView StartTime = (TextView) findViewById(R.id.tvStartTime);
        final TextView FinishTime = (TextView) findViewById(R.id.tvEndTime);
        final Button btnstart = (Button) findViewById(R.id.btnStart);
        final Button btnFinish = (Button) findViewById(R.id.btnFinish);

        assert btnstart != null;
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, TimeSelector.class);
                Settings.this.startActivity(intent);

            }

        });

       /*
        btnstart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
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
                       // StartTime.setText(new StringBuilder().append(selectedHour).append(" : ").append(selectedMinute)
                         //       .append(" ").append(format));
                        StartTime.setText( selectedHour + ":" + selectedMinute);
                       // showTime(selectedHour, selectedMinute);
                    }
                }, hour, minute, false);//false for 24 hour time
                mTimePicker.setTitle("Select Start Time");
                mTimePicker.show();


            }

        });
        */

    }

}

