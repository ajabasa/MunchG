package cse190.facebooklogin;

import android.support.v4.app.Fragment;

/**
 * Created by josh on 11/10/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
