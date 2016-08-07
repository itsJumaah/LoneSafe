package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 5/08/16.
 */


public class TokenRequest extends StringRequest {

    private static final String TOKEN_REQUEST = "http://202.89.41.210/token.php";
    private Map<String, String> params;

    public TokenRequest(String userId, String token, Response.Listener<String> listener) {
        super(Request.Method.POST, TOKEN_REQUEST, null, null);
        params = new HashMap<>();
        params.put("userId", userId + "");
        params.put("token", token);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
