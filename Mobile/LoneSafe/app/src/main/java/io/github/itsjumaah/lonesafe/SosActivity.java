package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SosActivity extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    String sos = "0";
    public static boolean NEED_TO_SEND_SOS = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        sharedPreference = new SharedPreference();

        if(NEED_TO_SEND_SOS){
            saveSosToDB();
            NEED_TO_SEND_SOS = false;
            System.out.print("SOSOS" + "NEED SOS SET TO false in sosAct =======++++++++++++++++&&&&&&&&&&&&&&&&&&&&&&&&&&&&" +
                    "****************************" + NEED_TO_SEND_SOS);
            Toast.makeText(SosActivity.this, "SOS sent", Toast.LENGTH_SHORT).show();

            finish();
        }


        //  this.setFinishOnTouchOutside(false);
        //Custom size for the dialog --- Delete if not using
        //WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.height = 2000;
        //params.width = 1200;
        //getWindow().setGravity(Gravity.CENTER);
       // getWindow().setAttributes(params);


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

                if (!NetworkChangeReceiver.isNetworkStatusAvialable(context)) {
                    sos = "1";
                   // sharedPreference.saveSOS(context, sos);

                    NEED_TO_SEND_SOS = true;
                    System.out.print("SOSOS" + "NEED SOS SET TO TRUE =======++++++++++++++++&&&&&&&&&&&&&&&&&&&&&&&&&&&&" +
                            "****************************" + NEED_TO_SEND_SOS);


                    final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
                    sosAlert.setMessage("NO Network Connectivity, SOS will be sent when network is available!\n\n" +
                            "If you require immediate help please call 111");
                    sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            Intent intent = new Intent(SosActivity.this,Home.class);
                            startActivity(intent);
                        }
                    });
                    sosAlert.setIcon(R.mipmap.ic_launcher);
                    sosAlert.setCancelable(false);
                    sosAlert.setTitle("S.O.S").create().show();

                }
                else{
                    sos="1";
                    saveSosToDB();

                    final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
                    sosAlert.setMessage("SOS sent\n" +  "Someone will be notified");
                    sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            Intent intent = new Intent(SosActivity.this,Home.class);
                            startActivity(intent);
                        }
                    });
                    sosAlert.setIcon(R.mipmap.ic_launcher);
                    sosAlert.setCancelable(false);
                    sosAlert.setTitle("S.O.S").create().show();
                }

            }
        });
    }

    void saveSosToDB (){

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Log.i("JSON: ", "Response true");

                    } else {
                        Log.i("JSON: ", "Response false");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        String job_num = sharedPreference.getValue(context,"UserID");

        sos = "1";

        NEED_TO_SEND_SOS = false;

        SosRequest sosRequest = new SosRequest(job_num, sos, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SosActivity.this);
        queue.add(sosRequest);


    }
}