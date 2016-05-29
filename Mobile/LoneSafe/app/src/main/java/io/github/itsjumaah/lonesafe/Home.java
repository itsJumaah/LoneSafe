package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    int i = 0;



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

        String Value = sharedPreference.getValue(context,"RiskLevel");

        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(Value);

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

        final Button btnStop = (Button) findViewById(R.id.btnStop);
        assert btnStop != null;
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                countDownTimer.onFinish();
            }
        });

        final Button btnSOS = (Button) findViewById(R.id.btnSOS);
        assert btnSOS != null;
        btnSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoCustomActivityDialog = new Intent(Home.this, SosActivity.class);
                startActivity(GotoCustomActivityDialog);
            }
        });


//------------------ Countdown Timer and Progress bar -- temp only until server is working ---
        Intent intent = getIntent();
        long time = intent.getLongExtra("getdata",1);
        //      long time = sharedPreference.getTimeMiliSec(context,"TimeKey");
        final TextView milVal = (TextView) findViewById(R.id.tvMil);
        milVal.setText(String.valueOf(time));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(i);
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                progressBar.setProgress(i);

            }
            @Override
            public void onFinish() {
                i++;
                progressBar.setProgress(i);
                Toast.makeText(Home.this,"Progress bar done",Toast.LENGTH_LONG).show();
            }
        };
        countDownTimer.start();
    }

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
}



