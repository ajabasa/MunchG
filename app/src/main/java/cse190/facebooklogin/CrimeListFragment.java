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
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActivity returns the hosting activity and lets the frag handle its affairs
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes(); // get list of crimes

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
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
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);

        /*
        // Start CrimeActivity by creating an explicit intent naming CrimeActivity class
        Intent i = new Intent(getActivity(), CrimeActivity.class);
        */

        // Start CrimePagerActivity with this crime
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        // tell CrimeFragment what Crime to display
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter( ArrayList<Crime> crimes) {
            // hooks up to the dataset of Crimes, 0 because not using a pre-defined layout
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Crime
            Crime c = getItem(position);

            // reference each widget in the view object and configure it with Crime's data
            TextView titleTextView=
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
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
