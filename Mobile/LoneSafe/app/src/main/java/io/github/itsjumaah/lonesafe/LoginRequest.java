package io.github.itsjumaah.lonesafe;

/**
 * Created by ashneelkumar on 24/05/16.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://202.89.41.210/scripts/login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        //params.put("token", token);

        //String token,

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
