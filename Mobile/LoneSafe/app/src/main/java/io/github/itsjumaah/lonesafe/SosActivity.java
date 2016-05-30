package io.github.itsjumaah.lonesafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        this.setFinishOnTouchOutside(false);
        //Custom size for the dialog --- Delete if not using
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = 2000;
        params.width = 1200;
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setAttributes(params);

        final Button btnCancel = (Button) findViewById(R.id.btnCancel);

        assert btnCancel != null;
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SosActivity.this,Home.class);
                startActivity(intent);
            }
        });

        final Button btnHelp = (Button) findViewById(R.id.btnHelp);

        assert btnHelp != null;
        btnHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
                sosAlert.setMessage("Someone will be notified!");
                sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                sosAlert.setTitle("S.O.S").create().show();
            }
        });
    }
}