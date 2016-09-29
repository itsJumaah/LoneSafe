package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 27/09/16.
 */
public class UpdateJobActiveRequest extends StringRequest {

    private static final String ONJOBACTIVE_REQUEST = "http://202.89.41.210/scripts/updateOnjob.php";
    private Map<String, String> params;

    public UpdateJobActiveRequest(String job_num, String isactive, Response.Listener<String> listener) {
        super(Request.Method.POST, ONJOBACTIVE_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("isactive", isactive);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
