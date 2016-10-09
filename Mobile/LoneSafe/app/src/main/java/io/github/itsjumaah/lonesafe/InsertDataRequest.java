package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertDataRequest extends StringRequest {

    private static final String INSERT_REQUEST_URL = "http://202.89.41.210/scripts/insert.php";
    private Map<String, String> params;

    public InsertDataRequest(String username, String starttime, String endtime,String workinghours, String risklevel, Response.Listener<String> listener) {
        super(Request.Method.POST, INSERT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("starttime",starttime);
        params.put("endtime",endtime);
        params.put("workinghours",workinghours);
        params.put("risklevel", risklevel);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
