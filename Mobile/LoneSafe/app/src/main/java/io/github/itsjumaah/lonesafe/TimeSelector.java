package io.github.itsjumaah.lonesafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class TimeSelector extends AppCompatActivity {
    private TimePicker timePicker1;
    private TextView tvTime;
    private Calendar calendar;
    private String format = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        tvTime = (TextView) findViewById(R.id.tvTime);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);

    }

    public void setTime(View view) {
        int hour =  calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
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
        tvTime.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }

}
