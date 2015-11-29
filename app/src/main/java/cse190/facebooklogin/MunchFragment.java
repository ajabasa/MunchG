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
import android.widget.TextView;
import android.widget.ImageView;

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
        View v = inflater.inflate( R.layout.fragment_crime, parent, false);

        //image = (ImageView) getView().findViewById(R.id.imageView1);
        //image.setImageResource(R.drawable.personicon);
        mPostNameField = (TextView)v.findViewById(R.id.munch_postName);
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

        mFullName = (TextView)v.findViewById(R.id.munch_fullName);
        // Set up postName
        mFullName.setText(mMunch.getFullName());

        // create reference to new button
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        // set its text to the date of crime
        //DateFormat dateFormat = android.text
        mDateButton.setText( DateFormat.getDateTimeInstance().format(mMunch.getDate()));
        // mDateButton.setText(mMunch.getDate().toString());
        // disabled for now
        mDateButton.setEnabled(false);

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