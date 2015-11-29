package cse190.facebooklogin;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by josh on 11/10/2015.
 * Singleton class
 */
public class MunchLab {
    private ArrayList<Munch> mMunches;

    private static MunchLab sMunchLab;
    private Context mAppContext;

    private MunchLab(Context appContext){
        mAppContext = appContext;
        mMunches = new ArrayList<Munch>();

        // populate with crimes
        for( int i = 0; i < 100; i++ ) {
            Munch c = new Munch();
            c.setFullName("Name: " + i);
            c.setSolved(i%2 == 0); // every other one
            mMunches.add(c);
        }
    }

    public static MunchLab get(Context c) {
        if( sMunchLab == null ) {
            // getApplicationContext gets a long-term Context that is global to the application
            sMunchLab = new MunchLab(c.getApplicationContext());
        }
        return sMunchLab;
    }

    public ArrayList<Munch> getCrimes(){
        return mMunches;
    }


    // find a specific crime
    public Munch getCrime( UUID id) {
        for( Munch c: mMunches) {
            if( c.getId().equals(id))
                return c;
        }
        return null;
    }


}
