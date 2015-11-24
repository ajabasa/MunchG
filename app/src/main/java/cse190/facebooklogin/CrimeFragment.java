package cse190.facebooklogin;

/**
 * Created by josh on 10/29/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.ucsd.joshua.crimintent.crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    // Takes care of fragment arguments after creating fragment but before attaching to activity pg.195
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get UUID from the fragment bundle's arguments
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        // Fetch Crime from Crimelab. get() requires a context so CrimeActivity is passed
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_crime, parent, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        // Set up title
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank
            }
        });



        // create reference to new button
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        // set its text to the date of crime
        //DateFormat dateFormat = android.text
        mDateButton.setText( DateFormat.getDateTimeInstance().format(mCrime.getDate()));
        // mDateButton.setText(mCrime.getDate().toString());
        // disabled for now
        mDateButton.setEnabled(false);

        // get reference to CheckBox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        // Set up check box
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        // update mSolved field of Crime
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

}
