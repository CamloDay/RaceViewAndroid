package i_neda.com.raceview.data;

import java.util.List;

/**
 * Created by cam on 23/02/2018.
 * item to store the race info and list of runners in the race
 */

public class RaceItem {
    private String mCourse;
    private String mTime;
    private double mDistance;//in furlongs
    private List<RunnerItem> mRunnerItemList;

    public RaceItem(String course, String time, double distance){
        this.mCourse = course;
        this.mTime = time;
        this.mDistance = distance;
    }

    public String getCourse() {
        return mCourse;
    }

    public String getTime() {
        return mTime;
    }

    public double getDistance() {
        return mDistance;
    }

    public List<RunnerItem> getRunnerList() {
        return mRunnerItemList;
    }

    public void setRunnerList(List<RunnerItem> runnerItemList) {
        this.mRunnerItemList = runnerItemList;
    }
}
