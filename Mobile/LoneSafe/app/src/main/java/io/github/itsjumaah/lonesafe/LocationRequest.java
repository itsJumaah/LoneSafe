package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 8/10/16.
 */
public class LocationRequest extends StringRequest {

    private static final String GPS_REQUEST = "http://202.89.41.210/scripts/location.php";
    private Map<String, String> params;

    public LocationRequest(String job_num, String lng, String lat, Response.Listener<String> listener) {
        super(Request.Method.POST, GPS_REQUEST, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("lng", lng);
        params.put("lat", lat);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
