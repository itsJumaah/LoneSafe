package io.github.itsjumaah.lonesafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcome);
        final TextView tvName = (TextView) findViewById(R.id.tvName);
        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        final TextView tvAge = (TextView) findViewById(R.id.tvAge);

        // Display user details
        String message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        tvUsername.setText(username);
        tvName.setText(name);
        tvAge.setText(age + "");

      //  final TextView welcomeMssg = (TextView) findViewById(R.id.tvWelcome);

      //  String mssg = "Settings Page";
    }
}
