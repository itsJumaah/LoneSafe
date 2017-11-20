package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 30/07/17.
 */

public class nextCheckinRequest extends StringRequest {

    private static final String NEXTCHECK_REQUEST_URL = "http://202.89.41.210/scripts/nextCheckin.php";
    private Map<String, String> params;

    public nextCheckinRequest (String job_num, String NextCheckin, Response.Listener<String> listener) {
        super(Request.Method.POST, NEXTCHECK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("NextCheckin",NextCheckin);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
