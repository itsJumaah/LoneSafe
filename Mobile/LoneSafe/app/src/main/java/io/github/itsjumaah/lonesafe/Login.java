package io.github.itsjumaah.lonesafe;

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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //--- action bar icon

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(" LoneSafe");


        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        loadPreferences();




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

    public static final String DEFAULT = "N/A";


    public void savePreferences() {

        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", etUser.getText().toString());
        editor.putString("Password", etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "Login was saved succesfully", Toast.LENGTH_LONG).show();
    }

    public void loadPreferences() {

        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
        String Name = sharedPreferences.getString("Name",DEFAULT);
        String Password = sharedPreferences.getString("Password",DEFAULT);

        //Error Checking
        if(Name.equals(DEFAULT) || Password.equals(DEFAULT)) {
            Toast.makeText(this,"No Data Was Found",Toast.LENGTH_LONG).show();

    }
        else { Toast.makeText(this,"Data Loaded Sucessfully",Toast.LENGTH_LONG).show();
            etUser.setText(Name);
            etPassword.setText(Password);
            btnLogin.performClick();

        }

    }

    public void provokeDatabase() {

        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final String username = etUser.getText().toString();
        final String password = etPassword.getText().toString();
        //Get user data from DB

        // }

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadPreferences();
                try {


                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");



                    if (success) {
                        savePreferences();


                        String name = jsonResponse.getString("name");
                        int age = jsonResponse.getInt("age");

                        Intent intent = new Intent(Login.this, Settings.class);
                        intent.putExtra("name", name);
                        intent.putExtra("age", age);
                        intent.putExtra("username", username);
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

        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);


    }
}
