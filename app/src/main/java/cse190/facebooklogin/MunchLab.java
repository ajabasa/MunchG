package cse190.facebooklogin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by josh on 11/10/2015.
 * Singleton class
 */
public class MunchLab {
    private static final String TAG = "MunchLab";

    private ArrayList<Munch> mMunches;

    private static MunchLab sMunchLab;
    private Context mAppContext;

    private MunchLab(Context appContext) {
        mAppContext = appContext;
        mMunches = new ArrayList<Munch>();

        /* Old auto-populate
        // populate with crimes
        for( int i = 0; i < 100; i++ ) {
            Munch c = new Munch();
            c.setFullName("Name: " + i);
            c.setSolved(i%2 == 0); // every other one
            mMunches.add(c);
        } */

        //Trying to get munches from server
/*
        //volley url
        String url = "http://52.10.49.188:3000/getmunchlist";

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json object response
                            // loop through each json object
                            //String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject munch = (JSONObject) response.get(i);
                                addMunch(munch);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });*/
        // create instance of RequestQueue
        //RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        //queue.add(req);
    }


    public static MunchLab get(Context c) {
        if( sMunchLab == null ) {
            // getApplicationContext gets a long-term Context that is global to the application
            sMunchLab = new MunchLab(c.getApplicationContext());
        }
        return sMunchLab;
    }

    public ArrayList<Munch> getCrimes(){
        return mMunches;
    }

    // Adds a munch to the munchlist from a json(check chapter 16)
    public void addMunch(JSONObject json) {
        Munch c = new Munch();
        try {
            c.setLocation(json.getString("location"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            c.setPostName(json.getString("postName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            c.setDate(json.getString("munchDate"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            c.setStartTime(json.getString("startTime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            c.setEndTime(json.getString("endTime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            c.setDescription(json.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Added munch: postName = " + c.getPostName());
        mMunches.add(c);
    }


    // find a specific crime
    public Munch getCrime( UUID id) {
        for( Munch c: mMunches) {
            if( c.getId().equals(id))
                return c;
        }
        return null;
    }


}
