package io.github.itsjumaah.lonesafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = new Intent();
        String rl = intent.getStringExtra("riskLevel");

        String riskLevel = "Your risk level is:" + rl;
        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(riskLevel);


    }
}
