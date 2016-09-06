package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertDataRequest extends StringRequest {

    private static final String INSERT_REQUEST_URL = "http://202.89.41.210/insert.php";
    private Map<String, String> params;

    public InsertDataRequest(String user_id, String endTime, String rskLevel, Response.Listener<String> listener) {
        super(Request.Method.POST, INSERT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id);
       // params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("rskLevel", rskLevel + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
