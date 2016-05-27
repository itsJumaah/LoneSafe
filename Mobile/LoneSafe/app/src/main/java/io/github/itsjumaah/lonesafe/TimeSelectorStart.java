package io.github.itsjumaah.lonesafe;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimeSelectorStart extends AppCompatActivity {
    private TimePicker timePicker1;
    private TextView tvTime1;
    private Calendar calendar;
    private String format = "";
    private String time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selector_end);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker2);
        tvTime1 = (TextView) findViewById(R.id.tvTime2);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);

        final Button btnSet = (Button) findViewById(R.id.btnSet);

        assert btnSet != null;
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = 0;
                int min = 0;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = timePicker1.getHour();
                } else {
                    hour = timePicker1.getCurrentHour();
                }
                if (Build.VERSION.SDK_INT >= 23 ){
                    min = timePicker1.getMinute();
                } else {
                    min = timePicker1.getCurrentMinute();
                }
                showTime(hour, min);

                Intent startTimeIntent = new Intent(TimeSelectorStart.this, Settings.class);
                TimeSelectorStart.this.startActivity(startTimeIntent);
                startTimeIntent.putExtra("starttime", time1);
                startActivity(startTimeIntent);

            }
        });
    }
    public void setTime(View view) {
        int hour = 0;
        int min = 0;
        if (Build.VERSION.SDK_INT >= 23 )
            hour = timePicker1.getHour();
        else
            hour = timePicker1.getCurrentHour();
        if (Build.VERSION.SDK_INT >= 23 )
            min = timePicker1.getMinute();
        else
            min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }

    public String showTime(int hour, int min) {
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
        tvTime1.setText(new StringBuilder().append(String.format("%02d:%02d ",hour,min)).append(format));
        // tvTime.setText(new StringBuilder().append(hour).append(" : ").append(min)
        //      .append(" ").append(format));
        return time1 = tvTime1.getText().toString();
        // return time = Integer.parseInt(tvTime.getText().toString());
        //Need send time to server from this class. As Int
        //Maybe another function similar to this, but returns int and send that to server?
    }
}
