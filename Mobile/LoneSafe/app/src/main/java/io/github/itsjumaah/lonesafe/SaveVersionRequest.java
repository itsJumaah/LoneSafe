package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 17/09/17.
 */

public class SaveVersionRequest extends StringRequest {

    private static final String ONJOB_REQUEST = "http://202.89.41.210/scripts/androidversion.php";
    private Map<String, String> params;

    public SaveVersionRequest(String username, String version, Response.Listener<String> listener) {
        super(Request.Method.POST, ONJOB_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("version", version);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
