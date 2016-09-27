package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 25/09/16.
 */
public class SosRequest extends StringRequest {

    private static final String SOS_REQUEST = "http://202.89.41.210/scripts/UpdateSos.php";
    private Map<String, String> params;

    public SosRequest(String job_num, String needsos, Response.Listener<String> listener) {
        super(Request.Method.POST, SOS_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("needsos", needsos);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
