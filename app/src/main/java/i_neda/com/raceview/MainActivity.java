package i_neda.com.raceview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import i_neda.com.raceview.connect.RetrieveRaceInfo;
import i_neda.com.raceview.connect.RetrieveRaceThread;
import i_neda.com.raceview.data.RaceItem;
import i_neda.com.raceview.ui.RunnerListAdapter;

public class MainActivity extends AppCompatActivity implements RetrieveRaceInfo.RaceInfoHandler {

    private TextView mTvRaceCourse;
    private TextView mTvRaceTime;
    private TextView mTvTimeTillRace;
    private TextView mTvRaceDistance;
    private ListView mLvRunners;

    private RetrieveRaceThread mRetrieveRaceThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRaceCourse = (TextView) findViewById(R.id.tvRaceCourse);
        mTvRaceTime = (TextView) findViewById(R.id.tvRaceTime);
        mTvTimeTillRace = (TextView) findViewById(R.id.tvTimeTillRace);
        mTvRaceDistance = (TextView) findViewById(R.id.tvDistance);
        mLvRunners = (ListView) findViewById(R.id.lvRunners);

        TextView tvNumber = (TextView) findViewById(R.id.tvNumber);
        TextView tvHorseName = (TextView) findViewById(R.id.tvHorseName);
        TextView tvJockeyName = (TextView) findViewById(R.id.tvJockeyName);
        TextView tvForm = (TextView) findViewById(R.id.tvForm);
        TextView tvOdds = (TextView) findViewById(R.id.tvOdds);

        tvNumber.setTextSize(20f);
        tvNumber.setText(getString(R.string.column_number));
        tvHorseName.setTextSize(20f);
        tvHorseName.setText(getString(R.string.column_horse_name));
        tvJockeyName.setTextSize(20f);
        tvJockeyName.setText(getString(R.string.column_jockey_name));
        tvForm.setTextSize(20f);
        tvForm.setText(getString(R.string.column_Form));
        tvOdds.setTextSize(20f);
        tvOdds.setText(getString(R.string.column_Odds));

        //single update call
//        RetrieveRaceInfo retrieveRaceData = new RetrieveRaceInfo(this);
//        retrieveRaceData.execute();

        //start repeated update call
        mRetrieveRaceThread = new RetrieveRaceThread(this, this);
        mRetrieveRaceThread.startThread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mTvRaceCourse = null;
        mTvRaceTime = null;
        mTvTimeTillRace = null;
        mTvRaceDistance = null;
        mLvRunners = null;

        if(mRetrieveRaceThread != null){
            mRetrieveRaceThread.stopThread();
            mRetrieveRaceThread = null;
        }
    }

    //update the ui with info for a RaceItem
    @Override
    public void handleRaceInfo(RaceItem raceItem) {
        mTvRaceCourse.setText(String.format(getString(R.string.info_course), raceItem.getCourse()));

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.UK);
            Date raceDate = format.parse(raceItem.getTime());
            mTvRaceTime.setText(String.format(getString(R.string.info_race_time), raceDate.toString()));

            long minutesTillRace = (raceDate.getTime() - new Date().getTime()) / 60000L;
            mTvTimeTillRace.setText(String.format(getString(R.string.info_time_till_race), minutesTillRace));
        }
        catch (ParseException pe){
            Log.w(RetrieveRaceInfo.class.getName(), String.format("ParseException processing raceItem date: %s", raceItem.getTime() + ", " + pe.toString()), pe);
        }

        mTvRaceDistance.setText(String.format(getString(R.string.info_distance), raceItem.getDistance()));

        mLvRunners.setAdapter(new RunnerListAdapter(this, raceItem.getRunnerList()));
        mLvRunners.invalidate();
    }
}
