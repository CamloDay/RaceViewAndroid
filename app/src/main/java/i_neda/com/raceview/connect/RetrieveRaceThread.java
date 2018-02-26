package i_neda.com.raceview.connect;

import android.app.Activity;

import i_neda.com.raceview.data.RaceItem;

/**
 * Created by cam on 23/02/2018.
 * background thread to handle getting the race updates every mRetryDelay
 */

public class RetrieveRaceThread extends Thread {
    private long mRetryDelay = 60000;

    private Activity mActivity;
    private RetrieveRaceInfo.RaceInfoHandler mHandler;
    private boolean mRunning;

    public RetrieveRaceThread(Activity activity, RetrieveRaceInfo.RaceInfoHandler handler){
        mActivity = activity;
        mHandler = handler;
    }

    public void startThread(){
        this.mRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        RetrieveRaceInfo retrieveRaceInfo = new RetrieveRaceInfo(mHandler);
        do {
            final RaceItem raceItem = retrieveRaceInfo.getRaceInfo();
            if(!mRunning){
                return;
            }
            if(raceItem != null) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.handleRaceInfo(raceItem);
                    }
                });
            }

            try {
                Thread.sleep(mRetryDelay);
            }
            catch (InterruptedException ie){

            }
        } while (mRunning);
    }

    public void stopThread(){
        mRunning = false;
    }
}
