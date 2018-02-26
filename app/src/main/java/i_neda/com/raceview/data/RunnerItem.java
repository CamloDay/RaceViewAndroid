package i_neda.com.raceview.data;

/**
 * Created by cam on 23/02/2018.
 * item to store the data for a runner in a race
 */

public class RunnerItem {
    private int mNumber;
    private String mHorseName;
    private String mJockeyName;
    private String mForm;
    private Double mOdds;

    public RunnerItem(int number, String horseName, String jockeyName, String form, Double odds){
        this.mNumber = number;
        this.mHorseName = horseName;
        this.mJockeyName = jockeyName;
        this.mForm = form;
        this.mOdds = odds;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getHorseName() {
        return mHorseName;
    }

    public String getJockeyName() {
        return mJockeyName;
    }

    public String getForm() {
        return mForm;
    }

    public Double getOdds() {
        return mOdds;
    }
}
