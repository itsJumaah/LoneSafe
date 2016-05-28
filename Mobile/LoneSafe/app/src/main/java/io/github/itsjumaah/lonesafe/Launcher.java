package io.github.itsjumaah.lonesafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);



        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
