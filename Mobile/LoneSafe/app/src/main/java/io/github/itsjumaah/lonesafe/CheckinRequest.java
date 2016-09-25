package io.github.itsjumaah.lonesafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashneelkumar on 11/09/16.
 */
public class CheckinRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://202.89.41.210/scripts/checkin.php";
    private Map<String, String> params;

    public CheckinRequest (String job_num, String checkin1, String checkin2, String checkin3,String checkin4,String checkin5,String checkin6,
                           String checkin7,String checkin8, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("job_num", job_num);
        params.put("checkin1", checkin1);
        params.put("checkin2", checkin2);
        params.put("checkin3", checkin3);
        params.put("checkin4", checkin4);
        params.put("checkin5", checkin5);
        params.put("checkin6", checkin6);
        params.put("checkin7", checkin7);
        params.put("checkin8", checkin8);

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }



}
