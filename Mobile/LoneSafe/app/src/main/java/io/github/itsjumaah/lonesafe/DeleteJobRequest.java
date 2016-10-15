package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 15/10/16.
 */
public class DeleteJobRequest extends StringRequest {

    private static final String DELETE_REQUEST = "http://202.89.41.210/scripts/delJob.php";
    private Map<String, String> params;

    public DeleteJobRequest(String username, Response.Listener<String> listener) {
        super(Request.Method.POST, DELETE_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
