package cse190.facebooklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by josh on 11/10/2015.
 */
public class MunchListFragment extends ListFragment {
    private ArrayList<Munch> mMunches = new ArrayList<Munch>();
    private static final String TAG = "MunchListFragment";
    JSONArray munchPosts = new JSONArray();
    private CrimeAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActivity returns the hosting activity and lets the frag handle its affairs
        getActivity().setTitle(R.string.crimes_title);
        String url = "http://52.10.49.188:3000/getmunchlist";
        Log.e(TAG, "onCreate called");


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, (String)null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONArray munchPosts = response.getJSONArray("munchlist");
                            MunchLab.get(getActivity()).reset();

                            // Parsing json object response
                            // loop through each json object
                            //String jsonResponse = "";
                            Log.d(TAG, "Munchlist response length: " + munchPosts.length() );
                            for (int i = 0; i < munchPosts.length(); i++) {
                                JSONObject munch = (JSONObject) munchPosts.get(i);
                                Log.d(TAG, "Reading munch: " + munch.toString());
                                MunchLab.get(getActivity()).addMunch(munch);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Update list adapter
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
        });
        // create instance of RequestQueue
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        queue.add(req);


        mMunches = MunchLab.get(getActivity()).getCrimes(); // get list of crimes

        adapter = new CrimeAdapter(mMunches);
        setListAdapter(adapter);
    }

    // onResume is generally the safest place to update fragment's view
    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    // Handler for clicking on a list item
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Munch c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.e(TAG, "ITEM CLICKED. Position: " + position + ", Postname: " + c.getPostName() );

        /*
        // Start CrimeActivity by creating an explicit intent naming CrimeActivity class
        Intent i = new Intent(getActivity(), CrimeActivity.class);
        */

        // Start MunchPagerActivity with this crime
        Intent i = new Intent(getActivity(), MunchPagerActivity.class);
        // tell MunchFragment what Munch to display
        i.putExtra(MunchFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }



    private class CrimeAdapter extends ArrayAdapter<Munch> {

        public CrimeAdapter( ArrayList<Munch> munches) {
            // hooks up to the dataset of Crimes, 0 because not using a pre-defined layout
            super(getActivity(), 0, munches);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_munch, null);
            }

            // Configure the view for this Munch
            Munch c = getItem(position);

            // reference each widget in the view object and configure it with Munch's data
            TextView titleTextView=
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getPostName());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate());

            //ProfilePictureView profileImageView = (ProfilePictureView)convertView.findViewById(R.id.imageView2);
            //profileImageView.setProfileId(c.getMfbId());
            /*CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());*/

            return convertView;
        }
    }
}
