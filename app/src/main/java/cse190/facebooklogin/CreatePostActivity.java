package cse190.facebooklogin;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import android.support.v4.view.ViewPager;
import android.app.DialogFragment;

/******************************************************************************
 * Activity created/ called from a successful FB Login
 *****************************************************************************/
public class CreatePostActivity extends Activity {
    /**************************************************************************
     * Constant String for Log Messages
     *************************************************************************/
    private static final String TAG = "CreatePostActivity";


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

    }

    /** Called when the user clicks the Send button */
    public void toDateScreen(View view) {

        EditText editTextp = (EditText) findViewById(R.id.postName);
        String messagep = editTextp.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.location);
        String message3 = editText3.getText().toString();
        EditText editText4 = (EditText) findViewById(R.id.description);
        String message4 = editText4.getText().toString();
        Context context = getApplicationContext();

        if(messagep.equals(""))
        {
            CharSequence text = "Post Name field missing";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if(message3.equals(""))
        {

            CharSequence text = "location field missing";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if (!editTextp.equals(null)){
            Intent intent = new Intent(this, setDateActivity.class);
            intent.putExtra("postName", messagep);
            intent.putExtra("location", message3);
            intent.putExtra("description", message4);
            startActivity(intent);
        }
    }
}