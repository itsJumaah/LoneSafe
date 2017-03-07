package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 8/03/17.
 */
public class CoverageRequest extends StringRequest{


    private static final String LOGIN_REQUEST_URL = "http://202.89.41.210/scripts/setCoverage.php";
    private Map<String, String> params;

    public CoverageRequest (String job_num, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        // params.put("bool", checkin1);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }



}
