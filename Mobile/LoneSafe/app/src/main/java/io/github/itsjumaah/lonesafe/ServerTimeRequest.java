package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by ashneelkumar on 27/07/17.
 */

public class ServerTimeRequest extends StringRequest {

    private static final String TIME_REQUEST_URL = "http://202.89.41.210/scripts/currentTimeJSON.php";
    //private Map<String, String> params;

    public ServerTimeRequest  (Response.Listener<String> listener) {
        super(Request.Method.POST, TIME_REQUEST_URL, listener, null );
    }
}
