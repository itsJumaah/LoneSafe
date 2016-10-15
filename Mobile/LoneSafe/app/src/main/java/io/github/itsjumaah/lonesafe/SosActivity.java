package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

    private LocationManager locationManager;
    private LocationListener listener;
    ProgressBar loadProgressBar;
    String lng;
    String lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        sharedPreference = new SharedPreference();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                lng = String.valueOf(longitude);
                lat = String.valueOf(latitude);

                Log.d("SOSLOCATION","SOS LOCATION CHANGED!!");


                //  saveSosToDB();
              //  SaveLocationToDB();
                showSentAlert();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        //  getLocation();

        if (NEED_TO_SEND_SOS) {
            getLocation();
            //  saveSosToDB();
            //  SaveLocationToDB();
            NEED_TO_SEND_SOS = false;
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
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SosActivity.this, Home.class);
                startActivity(intent);
            }
        });

        final Button btnHelp = (Button) findViewById(R.id.btnHelp);

        assert btnHelp != null;
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!NetworkChangeReceiver.isNetworkStatusAvialable(context)) {
                    sos = "1";

                    NEED_TO_SEND_SOS = true;


                    final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
                    sosAlert.setMessage("NO Network Connectivity, SOS will be sent when network is available!\n\n" +
                            "If you require immediate help please call 111");
                    sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            Intent intent = new Intent(SosActivity.this, Home.class);
                            startActivity(intent);
                        }
                    });
                    sosAlert.setIcon(R.drawable.ic_launcher);
                    sosAlert.setCancelable(false);
                    sosAlert.setTitle("S.O.S").create().show();


                } else {
                    getLocation();
                    sos = "1";
                    // saveSosToDB();
                    //SaveLocationToDB();

                    loadProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
                    loadProgressBar.setVisibility(View.VISIBLE);

                    /*
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
                    sosAlert.setIcon(R.drawable.ic_launcher);
                    sosAlert.setCancelable(false);
                    sosAlert.setTitle("S.O.S").create().show();
                    */
                }

            }
        });
    }

    void getLocation() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        System.out.println("SOS LOCATION ACQUIRED!!");
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

    void SaveLocationToDB() {

        if (lng == null || lat == null) {
            System.out.println("SOS REQUESTING LOCATION AGAIN!!");

            getLocation();
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "[" + response + "]");
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

        String job_num = sharedPreference.getValue(context, "UserID");


        LocationRequest locationRequest = new LocationRequest(job_num, lng, lat, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SosActivity.this);
        queue.add(locationRequest);

    }


    void saveSosToDB() {

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

        String job_num = sharedPreference.getValue(context, "UserID");

        sos = "1";

        NEED_TO_SEND_SOS = false;

        SosRequest sosRequest = new SosRequest(job_num, sos, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SosActivity.this);
        queue.add(sosRequest);


    }

    public void onDestroy() {
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        locationManager.removeUpdates(listener);
    }

    void showSentAlert(){

        loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
        loadProgressBar.setVisibility(View.INVISIBLE);

        final AlertDialog.Builder sosAlert = new AlertDialog.Builder(SosActivity.this);
        sosAlert.setMessage("SOS sent\n" +  "Someone will be notified");
        sosAlert.setPositiveButton("Dismiss!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
                saveSosToDB();
                SaveLocationToDB();
                Intent intent = new Intent(SosActivity.this,Home.class);
                startActivity(intent);
            }
        });
        sosAlert.setIcon(R.drawable.ic_launcher);
        sosAlert.setCancelable(false);
        sosAlert.setTitle("S.O.S").create().show();
    }
}