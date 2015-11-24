package cse190.facebooklogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;

/**
 * Created by josh on 11/12/2015.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //instantiated ViewPager and set it as the content view programatically
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        // get dataset from CrimeLab
        mCrimes = CrimeLab.get(this).getCrimes();

        // get the activity's FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // Set adapter to an instance of FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            // Returns the number of items in the array list
            @Override
            public int getCount() {
                return mCrimes.size();
            }

            // Create a properly configured CrimeFragment
            @Override
            public Fragment getItem(int pos) {
                Crime crime = mCrimes.get(pos);
                return CrimeFragment.newInstance(crime.getId());
            }
        });
    }
}
