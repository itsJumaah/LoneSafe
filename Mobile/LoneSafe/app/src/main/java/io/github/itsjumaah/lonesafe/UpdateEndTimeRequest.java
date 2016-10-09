package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 9/10/16.
 */
public class UpdateEndTimeRequest extends StringRequest {

    private static final String ENDTIME_REQUEST = "http://202.89.41.210/scripts/updateEndTime.php";
    private Map<String, String> params;

    public UpdateEndTimeRequest(String job_num, String endtime, Response.Listener<String> listener) {
        super(Request.Method.POST, ENDTIME_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("endtime", endtime);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
