package cse190.facebooklogin;

/**
 * Created by josh on 10/29/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;

import junit.framework.Test;

import java.text.DateFormat;
import java.util.UUID;

public class MunchFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.ucsd.joshua.crimintent.crime_id";

    private Munch mMunch;
    private TextView mPostNameField;
    private TextView mFullName;
    private TextView mStartTime;
    private TextView mEndTime;
    private TextView mDate;
    private TextView mLocation;
    private TextView mDescription;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private ImageView image;

    // Takes care of fragment arguments after creating fragment but before attaching to activity pg.195
    public static MunchFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);

        MunchFragment fragment = new MunchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get UUID from the fragment bundle's arguments
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        // Fetch Munch from Crimelab. get() requires a context so CrimeActivity is passed
        mMunch = MunchLab.get(getActivity()).getCrime(crimeId);

    }



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_munch, parent, false);

        //image = (ImageView) getView().findViewById(R.id.imageView1);
        //image.setImageResource(R.drawable.personicon);

        Log.d("MunchFragment", "MunchFragment Munch Name = " + mMunch.getPostName());
        mPostNameField = (TextView)v.findViewById(R.id.munch_PostName);
        // Set up postName
        mPostNameField.setText(mMunch.getPostName());
        /*mPostNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMunch.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank
            }
        });*/

        // Set up poster name
        mFullName = (TextView)v.findViewById(R.id.munch_fullName);
        mFullName.setText(mMunch.getFullName());

        // Set start time
        mStartTime = (TextView)v.findViewById(R.id.munch_startTime);
        mStartTime.setText(mMunch.getStartTime());

        // Set end time
        mEndTime = (TextView)v.findViewById(R.id.munch_endTime);
        if( mMunch.getEndTime() != null) {
            mEndTime.setText(mMunch.getEndTime());
        } else
        {
            mEndTime.setText("no end time");
        }

        mLocation = (TextView)v.findViewById(R.id.munch_location);
        mLocation.setText(mMunch.getLocation());

        mDescription = (TextView) v.findViewById(R.id.munch_description);
        if(mMunch.getDescription() != null) {
            mDescription.setText(mMunch.getDescription());
        } else {
            mDescription.setText("None");
        }

        // create reference to new button
        //mDateButton = (Button)v.findViewById(R.id.crime_date);
        // set its text to the date of crime
        //DateFormat dateFormat = android.text
        // mDateButton.setText( "Posted on: " + mMunch.getDate());
        // mDateButton.setText(mMunch.getDate().toString());
        // disabled for now
        //mDateButton.setEnabled(false);
       // mDateButton.getBackground().setColorFilter(0xA4D3EE);

        // get reference to CheckBox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        // Set up check box
        mSolvedCheckBox.setChecked(mMunch.isSolved());
        // update mSolved field of Munch
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the crime's solved property
                mMunch.setSolved(isChecked);
            }
        });

        return v;
    }

}
