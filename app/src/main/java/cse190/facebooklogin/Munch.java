package cse190.facebooklogin;

import java.util.Date;
import java.util.UUID;
import java.sql.Time;

/**
 * Created by josh on 10/29/2015.
 */
public class Munch {
    private UUID mId;
    private String mFullName;
    private String mPostName;
    private Date mDate;
    private Time mStartTime;
    private Time mEndTime;
    private String mLocation;
    private String mDescription;
    private boolean mSolved;

    public Munch() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date(); // automatically sets date to current date
    }

    @Override
    public String toString() {
        return mFullName; // returns title of the crime
    }

    public String getFullName() { return mFullName; }

    public void setFullName(String name){ this.mFullName = name;}

    public String getPostName() { return mPostName; }

    public void setPostName(String name){ this.mPostName = name;}

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public Time getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Time stime) {
        this.mStartTime = stime;
    }

    public Time getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Time etime) {
        this.mEndTime = etime;
    }

    public String getLocation() { return mLocation; }

    public void setLocation(String loc){ this.mLocation = loc;}

    public String getDescription() { return mDescription; }

    public void setDescription(String des){ this.mDescription = des;}

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        this.mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

}

