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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;

public class Settings extends AppCompatActivity {

//    private TextView tvStartTime;
    private Calendar calendar;
    private String format = "";
    private Boolean first = false;
    private Boolean second = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvUser);
        final TextView StartTime = (TextView) findViewById(R.id.tvStartTime);
        final TextView FinishTime = (TextView) findViewById(R.id.tvEndTime);

        // Display user details
        final String message ="You are logged in as " + name;
        tvWelcomeMsg.setText(message);

        //----------------------------------Spinner Stuff-------------------------------------------

        //Spinner riskSelector = (Spinner) findViewById(R.id.riskLvlDropdown);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Settings.this,
          //      R.array.risk_levels, android.R.layout.simple_spinner_item);

        //riskSelector.setAdapter(adapter);
        // Spinner click listener
       // riskSelector.setOnItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

        final Button btnstart = (Button) findViewById(R.id.btnStart);
        final Button btnFinish = (Button) findViewById(R.id.btnFinish);


        assert btnstart != null;
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Settings.this, TimeSelectorStart.class);
                Settings.this.startActivity(intent1);
            }

        });


        Intent startTimeIntent = getIntent();
        String time1 = startTimeIntent.getStringExtra("starttime");
        StartTime.setText(time1);

        assert btnFinish != null;
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Settings.this, TimeSelectorEnd.class);
                Settings.this.startActivity(intent1);
            }
        });

        Intent endTimeIntent = getIntent();
        String time2 = endTimeIntent.getStringExtra("endtime");
        FinishTime.setText(time2);


    }


}

