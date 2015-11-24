package cse190.facebooklogin;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by josh on 11/10/2015.
 * Singleton class
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();

        // populate with crimes
        for( int i = 0; i < 100; i++ ) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i%2 == 0); // every other one
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c) {
        if( sCrimeLab == null ) {
            // getApplicationContext gets a long-term Context that is global to the application
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }


    // find a specific crime
    public Crime getCrime( UUID id) {
        for( Crime c: mCrimes ) {
            if( c.getId().equals(id))
                return c;
        }
        return null;
    }
}
