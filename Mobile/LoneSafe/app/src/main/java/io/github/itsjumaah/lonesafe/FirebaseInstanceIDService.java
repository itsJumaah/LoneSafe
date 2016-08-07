package io.github.itsjumaah.lonesafe;



import android.app.Activity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by ashneelkumar on 5/08/16.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
      //  System.out.print(token);
        registerToken(token);
    }


    private void registerToken(String token) {
/*

        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("successful");

                    if (success) {
                       // Intent intent = new Intent(FirebaseInstanceIDService.this, Settings.class);
                       // FirebaseInstanceIDService.this.startActivity(intent);
                    }
                    else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        TokenRequest registerRequest = new TokenRequest(token, responseListener);
        RequestQueue queue = Volley.newRequestQueue(FirebaseInstanceIDService.this);
        queue.add(registerRequest);

*/
/*
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://202.89.41.210/token.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
}
