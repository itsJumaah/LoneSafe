package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 24/09/16.
 */
public class UpdateOnjobRequest extends StringRequest {

    private static final String ONJOB_REQUEST = "http://202.89.41.210/scripts/updateOnjob.php";
    private Map<String, String> params;

    public UpdateOnjobRequest(String username, String onjob, Response.Listener<String> listener) {
        super(Request.Method.POST, ONJOB_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("onjob", onjob);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
