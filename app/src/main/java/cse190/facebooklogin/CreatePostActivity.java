package cse190.facebooklogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);

        /**********************************************************************
         * Pulls the information from the previous Activity
         *
         * See note on onSuccess on using the current Access Token
        **********************************************************************/






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
                            info.put("user_id", object.getString("id"));
                            info.put("name", object.getString("name"));
                            info.put("gender", object.getString("gender"));
                            info.put("profilepic_url", object.getString("link"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, name, gender, link, birthday, picture");
        request.setParameters(parameters);
        request.executeAsync();

        //volley stuff
        /*String url = "http://httpbin.org/post";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);
                            System.out.println("Volley object: " + jsonResponse.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("site", "code");
                params.put("network", "tutsplus");
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(postRequest);*/


    //all this is in onCreate... not sure if it should be

    }

    /** Called when the user clicks the Send button */
    public void sendPost(View view) {
        Intent intent = new Intent(this, CrimeListActivity.class);

        EditText editText1 = (EditText) findViewById(R.id.time);
        String message1 = editText1.getText().toString();
        try {
            info.put("time", message1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editText2 = (EditText) findViewById(R.id.date);
        String message2 = editText2.getText().toString();
        try {
            info.put("date", message2);
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

        startActivity(intent);
    }
}