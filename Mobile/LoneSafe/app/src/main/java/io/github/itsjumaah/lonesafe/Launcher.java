package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this; //For shared Pref

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        sharedPreference = new SharedPreference();

        String Username = sharedPreference.getValue(context,"User");
        String Password = sharedPreference.getValue(context,"Pass");

        if(Username != null && Password != null){
           Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

    }
}
