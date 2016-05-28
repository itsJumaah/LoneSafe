package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreference = new SharedPreference();



        Intent i = getIntent();
        String Value = sharedPreference.getValue(context,"RiskLevel");

        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(Value);

        //TextView tvTest = (TextView) findViewById(R.id.tvTest);

        //Retrieve a value from SharedPreference
        String startTime = sharedPreference.getValue(context,"TimeStart");
        final TextView tvStTime = (TextView) findViewById(R.id.tvStTime);
        tvStTime.setText(startTime);

        String FinishTime = sharedPreference.getValue(context,"TimeEnd");
        final TextView tvFnTime = (TextView) findViewById(R.id.tvFnTime);
        tvFnTime.setText(FinishTime);

        final Button btnsettings = (Button) findViewById(R.id.btnSettings);

        assert btnsettings != null;
        btnsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Settings.class);
                Home.this.startActivity(intent);
            }
        });


    }
}

