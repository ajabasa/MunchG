
package cse190.facebooklogin;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class setETimeActivity extends Activity {

    private static final String TAG = "setETimeActivity";

    JSONObject info = new JSONObject();
    private int myHour;
    private int myMin;
    private Bundle extras;
    private int hourS;
    private int minuteS;

    private int year;
    private int month;
    private int day;

    private String postName;
    private String location;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_end);
        TimePicker tp = (TimePicker)
                findViewById(R.id.timePicker2);
        tp.setIs24HourView(true);
        tp.setCurrentMinute(10);
        tp.setCurrentHour(13);
        tp.setOnTimeChangedListener(OnTimeChanged);
        extras = getIntent().getExtras();
        postName = extras.getString("postName");
        location = extras.getString("location");
        description = extras.getString("description");
        year = extras.getInt("year");
        month = extras.getInt("month");
        day = extras.getInt("day");
        hourS = extras.getInt("hourS");
        minuteS = extras.getInt("minuteS");
        Log.d(TAG, "postName: " + postName);
        Log.d(TAG, "location: " + location);
        Log.d(TAG, "description: " + description);
        Log.d(TAG, "year: " + year);
        Log.d(TAG, "month: " + month);
        Log.d(TAG, "day: " + day);
        Log.d(TAG, "hourS: " + hourS);
        Log.d(TAG, "minuteS: " + minuteS);
    }

    public void finishPost(View view){
        Intent intent = new Intent(this, MunchListActivity.class);
        intent.putExtra("hourE", myHour);
        intent.putExtra("minuteE", myMin);

        /*extras = getIntent().getExtras();
        Log.e(TAG, "year = " + extras.get("year"));
        extras = getIntent().getExtras();
        Log.e(TAG, "postName = " + extras.get("postName"));
        extras = getIntent().getExtras();
        Log.e(TAG, "location = " + extras.get("location"));
        extras = getIntent().getExtras();
        Log.e(TAG, "description = " + extras.get("description"));
        extras = getIntent().getExtras();
        Log.e(TAG, "month = " + extras.get("month"));
        extras = getIntent().getExtras();
        Log.e(TAG, "day = " + extras.get("day"));
        extras = getIntent().getExtras();
        Log.e(TAG, "hourS = " + extras.get("hourS"));
        extras = getIntent().getExtras();
        Log.e(TAG, "minuteS = " + extras.get("minuteS"));
        extras = getIntent().getExtras();
        Log.e(TAG, "hourE = " + extras.get("hourE"));
        extras = getIntent().getExtras();
        Log.e(TAG, "minuteE = " + extras.get("minuteE"));*/

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e(TAG, "user_me request = " + object.toString());
                        //json stuff
                        try {
                            //put fields into our Json info object
                            //info.put("user_id", object.getString("id"));
                            info.put("fullName", object.getString("name"));
                            info.put("picture", object.getString("picture"));
                            info.put("facebookId", object.getString("id"));

                            info.put("postName", postName);
                            info.put("location", location);
                            info.put("description", description);
                            info.put("year", String.valueOf(year));
                            info.put("month", String.valueOf(month));
                            info.put("day", String.valueOf(day));
                            info.put("hourS", String.valueOf(hourS));
                            info.put("minuteS", String.valueOf(minuteS));
                            info.put("hourE", String.valueOf(myHour));
                            info.put("minuteE", String.valueOf(myMin));
                            callVolley();

                            //info.put("gender", object.getString("gender"));
                            //info.put("profilepic_url", object.getString("link"));
                            String test = info.toString();
                            Log.d(TAG, "json: " + test);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, name, gender, link, birthday, picture");
        //parameters.putString("fields", "fullName");
        request.setParameters(parameters);
        request.executeAsync();

        //json

        /*try {
            info.put("postName", postName);
        } catch (JSONException e) {
            Log.e(TAG, "error with postName");
            e.printStackTrace();
        }
        try {
            info.put("location", location);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("description", description);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("year", year);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("month", month);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("day", day);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("hourS", hourS);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("minuteS", minuteS);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("hourE", myHour);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }
        try {
            info.put("minuteE", myMin);
        } catch (JSONException e) {
            Log.e(TAG, "error with json");
            e.printStackTrace();
        }*/


        startActivity(intent);
        //finish();
    }


    public void callVolley(){
        //volley stuff
        String url = "http://52.10.49.188:3000/munchlist";

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, info,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error with volley send");
                        error.printStackTrace();
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "My useragent");
                return headers;
            }
        };


        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        queue.add(postRequest);

    }

    TimePicker.OnTimeChangedListener OnTimeChanged =
            new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(
                        TimePicker view,
                        int hourOfDay,
                        int minute) {
                    Log.d( "setHour1T: ", Integer.toString(hourOfDay));
                    Log.d( "setMin1T: ", Integer.toString(minute));
                    myHour = hourOfDay;
                    myMin = minute;

                }
            };




}
   


