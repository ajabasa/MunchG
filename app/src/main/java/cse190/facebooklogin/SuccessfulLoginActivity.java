package cse190.facebooklogin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

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
        Bundle extras = getIntent().getExtras();
        Log.e(TAG, "User ID = " + extras.get("UserID"));
        extras = getIntent().getExtras();
        Log.e(TAG, "Token ID = " + extras.get("TokenID"));

        /**********************************************************************
         * Some Useful Methods/ Information:
         * - Log out the user using ==> LoginManager.getInstance().logOut()
         * - You can use the AccessTokenTracker class to monitor changes to
         *   AccessTokens
         * - You can use the ProfileTracker class to track changes in the
         *   current profile
         *********************************************************************/

    }
}
