package io.github.itsjumaah.lonesafe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private SharedPreference sharedPreference;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference();

        //--- action bar icon

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(" LoneSafe");

        

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
                final String username = etUser.getText().toString();
                final String password = etPassword.getText().toString();

                // Hardcoded user and password incase server doesn't work
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
        myAlert.setMessage("This application is designed for specific users, please see your manager if need a login, Thanks :)")
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

        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final String username = etUser.getText().toString();
        final String password = etPassword.getText().toString();

        //token for Firebase Push notification
       // FirebaseMessaging.getInstance().subscribeToTopic("test");
        String token = FirebaseInstanceId.getInstance().getToken();
        //Get user data from DB

        // }

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // loadPreferences();
                try {


                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {

                        //  savePreferences();
                        String name = jsonResponse.getString("name");
                        String pass = jsonResponse.getString("password");
                        String user = jsonResponse.getString("username");
                        int user_id = jsonResponse.getInt("user_id");
                        String userId = String.valueOf(user_id);
                    //    int age = jsonResponse.getInt("age");

                        sharedPreference.saveName(context, name);
                        sharedPreference.saveUser(context, user);
                        sharedPreference.savePass(context, pass);
                        sharedPreference.saveUserID(context, userId);

                       // FirebaseInstanceIdService FBID = new FirebaseInstanceIDService();
                       // FBID.onTokenRefresh();

                        Intent intent = new Intent(Login.this, Settings.class);
                        Login.this.startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(username, password, token, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);


    }
}
