package cse190.facebooklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josh on 11/10/2015.
 */
public class MunchListFragment extends ListFragment {
    private ArrayList<Munch> mMunches;
    private static final String TAG = "MunchListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActivity returns the hosting activity and lets the frag handle its affairs
        getActivity().setTitle(R.string.crimes_title);
        mMunches = MunchLab.get(getActivity()).getCrimes(); // get list of crimes

        CrimeAdapter adapter = new CrimeAdapter(mMunches);
        setListAdapter(adapter);
    }

    // onResume is generally the safest place to update fragment's view
    @Override
    public void onResume() {
        super.onResume();;
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    // Handler for clicking on a list item
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Munch c = ((CrimeAdapter)getListAdapter()).getItem(position);

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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Munch
            Munch c = getItem(position);

            // reference each widget in the view object and configure it with Munch's data
            TextView titleTextView=
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getFullName());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }
}
