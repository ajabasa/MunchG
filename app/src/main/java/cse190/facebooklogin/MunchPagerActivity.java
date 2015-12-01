package cse190.facebooklogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by josh on 11/12/2015.
 */
public class MunchPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Munch> mMunches;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //instantiated ViewPager and set it as the content view programatically
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        // get dataset from MunchLab
        mMunches = MunchLab.get(this).getCrimes();

        // get the activity's FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // Set adapter to an instance of FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            // Returns the number of items in the array list
            @Override
            public int getCount() {
                return mMunches.size();
            }

            // Create a properly configured MunchFragment
            @Override
            public Fragment getItem(int pos) {
                Munch munch = mMunches.get(pos);
                Log.d("MunchPagerActivity", "MunchFragment created " + munch.getId() );
                return MunchFragment.newInstance(munch.getId());
            }
        });

        UUID munchId = (UUID)getIntent().getSerializableExtra(MunchFragment.EXTRA_CRIME_ID);
        for(int i = 0; i < mMunches.size(); i++) {
            if(mMunches.get(i).getId().equals(munchId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
