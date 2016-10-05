package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    String password;
    ProgressBar loadProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference();

        //--- action bar icon

        ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.setIcon(R.mipmap.ic_launcher);
       // actionBar.setTitle(" LoneSafe");
        actionBar.hide();

        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        final TextView tnc = (TextView) findViewById(R.id.tvTnc);

        assert tnc != null;
        tnc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Terms.class);
                Login.this.startActivity(intent);

            }
        });

        assert btnLogin != null;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                loadProgressBar.setVisibility(View.VISIBLE);

                final String username = etUser.getText().toString();
                final String password = etPassword.getText().toString();

                // Hardcoded user and password incase server doesn't work
                //TODO DELETE!!--------
                if (username.equals("johndoe") && password.equals("pass")) {

                    String name = ("John");

                    Intent intent = new Intent(Login.this, Settings.class);
                    intent.putExtra("username", username);
                    intent.putExtra("name", name);
                    Login.this.startActivity(intent);


                }
                else { provokeDatabase(); }

            }



        });


    }

    // --- Info button

    public void showAlert (View view){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("This application is designed for specific users, please see your manager if need a login")
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Information")
                .create();
        myAlert.show();


    }

    public void provokeDatabase() {

        if(!NetworkChangeReceiver.isNetworkStatusAvialable(context)){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("PLEASE ENSURE YOUR DATA IS TURNED ON")
                    .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                            loadProgressBar.setVisibility(View.INVISIBLE);
                            //dialog.dismiss();
                            onRestart();
                        }
                    })
                    .setTitle("NO INTERNET CONNECTION")
                    .create();
            alert.show();
        }


        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final String username = etUser.getText().toString();
      //  final String password = etPassword.getText().toString();


        try{
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = etPassword.getText().toString();
            System.out.println("etPassword : " + text);


            md.update(text.getBytes("UTF-8"));
            byte[] digest = md.digest();

            password = String.format("%0" + (digest.length * 2) + 'x', new BigInteger(1, digest));

            System.out.println("--------> ## HASH PASS:" + password);
        } catch (NoSuchAlgorithmException x) {
            System.err.println("SHA-256 is not a valid message digest algorithm");
        } catch (IOException e){
            System.err.println("unsupported Encoding Exception");

        }



        //TODO Remove Firebase
        String token = FirebaseInstanceId.getInstance().getToken();
        //Get user data from DB

        // }

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.i("tagconvertstr", "["+response+"]");

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                   // String password1 = jsonResponse.getString("password");


                    if (success) {
                        Log.i("JSON: ", "Response true");

                        //  savePreferences();
                        String name = jsonResponse.getString("firstname");
                       // String pass = jsonResponse.getString("password");
                        String user = jsonResponse.getString("username");
                        int user_id = jsonResponse.getInt("id");
                        String userId = String.valueOf(user_id);

                        sharedPreference.saveName(context, name);
                        sharedPreference.saveUser(context, user);
                        sharedPreference.savePass(context, password);
                        sharedPreference.saveUserID(context, userId);

                        loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                        loadProgressBar.setVisibility(View.INVISIBLE);

                        Intent intent = new Intent(Login.this, Settings.class);
                        Login.this.startActivity(intent);
                        finish();

                    } else {

                        Log.i("JSON: ", "Response false");
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                        loadProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
                        loadProgressBar.setVisibility(View.INVISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        System.out.println("--------> ## HASH PASS BEFORE REquest: " + password);

        //TODO MOVE to function?
        String secret =  "(G1)MEGA#X!A1**";
        try{
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = secret;
            System.out.println("etPassword : " + text);


            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();

            secret = String.format("%0" + (digest.length * 2) + 'x', new BigInteger(1, digest));

            //System.out.println("--------> ## HASH PASS:" + password);
        } catch (NoSuchAlgorithmException x) {
            System.err.println("SHA-256 is not a valid message digest algorithm");
        } catch (IOException e){
            System.err.println("unsupported Encoding Exception");

        }

        LoginRequest loginRequest = new LoginRequest(username, password, secret, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);

    }
}
