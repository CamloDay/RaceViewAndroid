package i_neda.com.raceview.connect;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import i_neda.com.raceview.Constants;
import i_neda.com.raceview.data.RaceItem;
import i_neda.com.raceview.data.RunnerItem;

/**
 * Created by cam on 23/02/2018.
 * gets the data for a race, then processes it from json into a RaceItem item and list of RunnerItem items
 */

public class RetrieveRaceInfo extends AsyncTask<Void, Void, RaceItem> {
    private RaceInfoHandler mHandler;

    public RetrieveRaceInfo(RaceInfoHandler handler){
        mHandler = handler;
    }

    @Override
    protected RaceItem doInBackground(Void... params) {
        return getRaceInfo();
    }

    protected RaceItem getRaceInfo(){
        String raceData = GetRaceData.downloadData();
        if(raceData == null){
            return null;
        }

        return processJSONToRace(raceData);
    }

    @Override
    protected void onPostExecute(RaceItem raceItem) {
        super.onPostExecute(raceItem);

        if(raceItem != null && mHandler != null){
            mHandler.handleRaceInfo(raceItem);
        }
    }

    //converts the data from a json string into a RaceItem item
    public RaceItem processJSONToRace(String raceJSONString){
        JSONObject json;
        try {
            json = new JSONObject(raceJSONString);

            String course = json.getString(Constants.json_course);
            String time = json.getString(Constants.json_time);
            double distanceMetres = json.getDouble(Constants.json_distance);
            RaceItem raceItem = new RaceItem(course, time, distanceMetres / 201.168);//convert metres to furlongs

            JSONArray runnerJSONArray =  json.getJSONArray(Constants.json_runners);
            int runnersLength = runnerJSONArray.length();
            List<RunnerItem> runnerItemList = new ArrayList<>();
            for (int i = 0; i < runnersLength; i++) {
                JSONObject runner = runnerJSONArray.getJSONObject(i);
                int number = runner.getInt(Constants.json_number);
                String horseName = runner.getString(Constants.json_horse_name);
                String jokeyName = runner.getString(Constants.json_jokey_name);
                String form = runner.getString(Constants.json_form);
                if(form.equals("null")){
                    form = "";
                }
                Double odds = runner.getDouble(Constants.json_odds);
                runnerItemList.add(new RunnerItem(number, horseName, jokeyName, form, odds));
            }

            //sort list by odds
            runnerItemList.sort(new Comparator<RunnerItem>() {
                @Override
                public int compare(RunnerItem o1, RunnerItem o2) {
                    return Double.compare(o1.getOdds(), o2.getOdds());
                }
            });

            raceItem.setRunnerList(runnerItemList);

            return raceItem;
        }
        catch (JSONException jsone){
            Log.w(RetrieveRaceInfo.class.getName(), String.format("IOException processing data: %s", jsone.toString()), jsone);
            return null;
        }
    }

    public interface RaceInfoHandler {
        void handleRaceInfo(RaceItem raceItem);
    }
}