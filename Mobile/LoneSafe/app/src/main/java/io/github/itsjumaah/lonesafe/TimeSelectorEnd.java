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

public class TimeSelectorEnd extends AppCompatActivity {
    private TimePicker timePicker2;
    private TextView tvTime2;
    private Calendar calendar;
    private String format = "";
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selector_end);

        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        tvTime2 = (TextView) findViewById(R.id.tvTime2);
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
                    hour = timePicker2.getHour();
                } else {
                    hour = timePicker2.getCurrentHour();
                }
                if (Build.VERSION.SDK_INT >= 23 ){
                    min = timePicker2.getMinute();
                } else {
                    min = timePicker2.getCurrentMinute();
                }
                showTime(hour, min);

                Intent endTimeIntent = new Intent(TimeSelectorEnd.this, Settings.class);
                TimeSelectorEnd.this.startActivity(endTimeIntent);
                endTimeIntent.putExtra("endtime", time);
                startActivity(endTimeIntent);

            }
        });
    }
    public void setTime(View view) {
        int hour = 0;
        int min = 0;
        if (Build.VERSION.SDK_INT >= 23 )
            hour = timePicker2.getHour();
        else
            hour = timePicker2.getCurrentHour();
        if (Build.VERSION.SDK_INT >= 23 )
            min = timePicker2.getMinute();
        else
            min = timePicker2.getCurrentMinute();
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
        tvTime2.setText(new StringBuilder().append(String.format("%02d:%02d ",hour,min)).append(format));
        // tvTime.setText(new StringBuilder().append(hour).append(" : ").append(min)
        //      .append(" ").append(format));
        return time = tvTime2.getText().toString();
        // return time = Integer.parseInt(tvTime.getText().toString());
        //Need send time to server from this class. As Int
        //Maybe another function similar to this, but returns int and send that to server?
    }
}
