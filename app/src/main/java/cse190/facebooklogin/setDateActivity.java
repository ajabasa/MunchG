
        package cse190.facebooklogin;


        import java.util.Calendar;
        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.DatePicker;

        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by andrewjabasa on 11/30/15.
 */
public class setDateActivity extends Activity {
    private int myYear;
    private int myMonth;
    private int myDay;
    private Bundle extras;
    private String postName;
    private String location;
    private String description;

    private static final String TAG = "setDateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        DatePicker dp = (DatePicker)
                findViewById(R.id.datePicker);
        dp.init(2015, 11, 3, onDateChanged);
        dp.setCalendarViewShown(false);
        dp.setSpinnersShown(true);

        extras = getIntent().getExtras();
        postName = extras.getString("postName");
        location = extras.getString("location");
        description = extras.getString("description");
        //Log.e(TAG, "Postname = " + test);

        Log.e(TAG, "Postname = " + extras.get("postName"));
        extras = getIntent().getExtras();
        Log.e(TAG, "Location = " + extras.get("location"));
        extras = getIntent().getExtras();
        Log.e(TAG, "Description = " + extras.get("description"));
    }

    public void nextScreenStartTime(View view) {
        Intent intent = new Intent(this, setSTimeActivity.class);
        //add extras
        //Log.d( "setHour1: ", String.valueOf(myHour));
        //Log.d( "setMin1: ", String.valueOf(myMin));

        intent.putExtra("postName", postName);
        intent.putExtra("location", location);
        intent.putExtra("description", description);
        intent.putExtra("year", myYear);
        intent.putExtra("month", myMonth);
        intent.putExtra("day", myDay);
        startActivity(intent);
    }

    DatePicker.OnDateChangedListener onDateChanged =
            new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(
                        DatePicker view,
                        int year,
                        int monthOfYear,
                        int dayOfMonth) {
                    myYear = year;
                    myMonth = monthOfYear;
                    myDay = dayOfMonth;

                }
            };
}