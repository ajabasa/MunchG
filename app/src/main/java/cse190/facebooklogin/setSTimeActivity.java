package cse190.facebooklogin;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class setSTimeActivity extends Activity {
    private int myHour;
    private int myMin;

    private int year;
    private int month;
    private int day;
    private Bundle extras;
    private String postName;
    private String location;
    private String description;

    private static final String TAG = "setSTimeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        TimePicker tp = (TimePicker)
                findViewById(R.id.timePicker1);
        tp.setIs24HourView(true);
        tp.setCurrentMinute(10);
        tp.setCurrentHour(13);
        tp.setOnTimeChangedListener(OnTimeChanged);

        extras = getIntent().getExtras();
        postName = extras.getString("postName");
        location = extras.getString("location");
        description = extras.getString("description");
        year = extras.getInt("year");
        month = extras.getInt("month");
        day = extras.getInt("day");
    }

    public void nextScreenEndTime(View view){
        Intent intent = new Intent(this, setETimeActivity.class);
        //add extras
        //Log.d( "setHour1: ", String.valueOf(myHour));
        //Log.d( "setMin1: ", String.valueOf(myMin));
        intent.putExtra("postName", postName);
        intent.putExtra("location", location);
        intent.putExtra("description", description);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);

        intent.putExtra("hourS", myHour);
        intent.putExtra("minuteS", myMin);


        extras = getIntent().getExtras();
        Log.e(TAG, "Location = " + extras.get("location"));
        extras = getIntent().getExtras();
        Log.e(TAG, "Postname = " + extras.get("postName"));
        extras = getIntent().getExtras();
        Log.e(TAG, "year = " + extras.get("year"));
        startActivity(intent);
        finish();
    }

    TimePicker.OnTimeChangedListener OnTimeChanged =
            new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(
                        TimePicker view,
                        int hourOfDay,
                        int minute) {
                    Log.d( "setHour1T: ", Integer.toString(hourOfDay));
                    Log.d( "setMin1T: ", Integer.toString(minute));
                    myHour = hourOfDay;
                    myMin = minute;

                }
            };
}



