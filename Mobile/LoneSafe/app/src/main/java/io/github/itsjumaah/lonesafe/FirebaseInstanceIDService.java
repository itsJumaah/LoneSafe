package io.github.itsjumaah.lonesafe;



import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by ashneelkumar on 5/08/16.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService  {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);
        //registerToken(token);
    }

    /*

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
                       // Intent intent = new Intent(FirebaseInstanceIDService.this, OLDSettings.class);
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


    }
    */
}
