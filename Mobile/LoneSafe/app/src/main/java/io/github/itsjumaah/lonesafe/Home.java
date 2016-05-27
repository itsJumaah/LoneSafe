package io.github.itsjumaah.lonesafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Intent intent =  getIntent();
       // String rl = intent.getStringExtra("riskLevel");

        Intent i = getIntent();
        String Value = i.getStringExtra("getdata");
        String startTime = i.getStringExtra("getStartTime");
        String FinishTime = i.getStringExtra("getFinishTime");

        final TextView tvRiskLvl = (TextView) findViewById(R.id.tvRiskLvl);
        tvRiskLvl.setText(Value);

        final TextView tvStTime = (TextView) findViewById(R.id.tvStTime);
        tvStTime.setText(startTime);
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
