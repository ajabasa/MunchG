package cse190.facebooklogin;

import android.app.Activity;
import android.os.Bundle;

/******************************************************************************
 * Activity created/ called from a successful FB Login
 *****************************************************************************/
public class SuccessfulLoginActivity extends Activity {
    /**************************************************************************
     * Constant String for Log Messages
     *************************************************************************/
    private static final String TAG = "SuccessfulLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_login);

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
        /*

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e(TAG, "user_me request = " + object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, name, gender, link, birthday, picture");
        request.setParameters(parameters);
        request.executeAsync();

        */
    }
}
