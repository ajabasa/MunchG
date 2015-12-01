package cse190.facebooklogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import android.support.v4.view.ViewPager;

/******************************************************************************
 * Activity created/ called from a successful FB Login
 *****************************************************************************/
public class CreatePostActivity extends Activity {
    /**************************************************************************
     * Constant String for Log Messages
     *************************************************************************/
    private static final String TAG = "CreatePostActivity";

    JSONObject info = new JSONObject();
    /*String user_id;
    String name;
    String gender;
    String profilepic_url;*/

    //analytics
    private Tracker mTracker;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_post_create);

        /**********************************************************************
         * Pulls the information from the previous Activity
         *
         * See note on onSuccess on using the current Access Token
        **********************************************************************/

        //analytics
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        //String name = getCurrentImageTitle();
        Log.d("Setting screen name: ", "createPostActivity");
        mTracker.setScreenName("Image~" + "createPostActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());




        //COMMENTED THIS OUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



        /*Bundle extras = getIntent().getExtras();
        Log.e(TAG, "User ID = " + extras.get("UserID"));
        extras = getIntent().getExtras();
        Log.e(TAG, "Token ID = " + extras.get("TokenID"));*/

        /**********************************************************************
         * Some Useful Methods/ Information:
         * - Log out the user using ==> LoginManager.getInstance().logOut()
         * - You can use the AccessTokenTracker class to monitor changes to
         *   AccessTokens
         * - You can use the ProfileTracker class to track changes in the
         *   current profile
         *********************************************************************/

        /**********************************************************************
         * The following code below demonstrates how you can pull a JSONObject
         * from Facebook. In the following code, I ask for a request to pull
         * my own user information (newMeRequest), and ask for the fields: id,
         * first_name, last_name, etc. I then output the message into the
         * the Log.
         *********************************************************************/




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
                            //info.put("gender", object.getString("gender"));
                            //info.put("profilepic_url", object.getString("link"));

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




    }

    /** Called when the user clicks the Send button */
    public void sendPost(View view) {
        Intent intent = new Intent(this, MunchListActivity.class);

        EditText editTextp = (EditText) findViewById(R.id.postName);
        String messagep = editTextp.getText().toString();
        try {
            info.put("postName", messagep);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText1 = (EditText) findViewById(R.id.timeStart);
        String message1 = editText1.getText().toString();
        try {
            info.put("startTime", message1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText1b = (EditText) findViewById(R.id.timeEnd);
        String message1b = editText1b.getText().toString();
        try {
            info.put("endTime", message1b);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText2 = (EditText) findViewById(R.id.date);
        String message2 = editText2.getText().toString();
        try {
            info.put("munchDate", message2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText3 = (EditText) findViewById(R.id.location);
        String message3 = editText3.getText().toString();
        try {
            info.put("location", message3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText4 = (EditText) findViewById(R.id.description);
        String message4 = editText4.getText().toString();
        try {
            info.put("description", message4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String toPrint = info.toString();
        Log.e(TAG, "json: " + toPrint);

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
        };
        // create instance of RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        queue.add(postRequest);
        //Volley.newRequestQueue(getApplicationContext()).add(postRequest);



        startActivity(intent);
    }
}
