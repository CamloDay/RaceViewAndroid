package i_neda.com.raceview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import i_neda.com.raceview.connect.RetrieveRaceInfo;
import i_neda.com.raceview.data.RaceItem;
import i_neda.com.raceview.data.RunnerItem;

import static org.junit.Assert.assertEquals;

/**
 * Created by cam on 24/02/2018.
 * confirms that RetrieveRaceInfo.processJSONToRace returns the correct result
 */

public class RetrieveRaceItemInfoTest {
    private String mExampleData =
            "{\n" +
                    "\t\"course\": \"Kelso\",\n" +
                    "\t\"time\": \"2017-02-16T13:25:00.000Z\",\n" +
                    "\t\"distance\": \"3218.7\",\n" +
                    "\t\"runners\": [{\n" +
                    "\t\t\"number\": 1,\n" +
                    "\t\t\"horse_name\": \"Lycidas\",\n" +
                    "\t\t\"jockey_name\": \"Brian Hughes\",\n" +
                    "\t\t\"form\": \"4-2139\",\n" +
                    "\t\t\"odds\": \"2.3\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 2,\n" +
                    "\t\t\"horse_name\": \"Balthazar\",\n" +
                    "\t\t\"jockey_name\": \"Adrian Lane\",\n" +
                    "\t\t\"form\": 4-2139,\n" +
                    "\t\t\"odds\": \"51.0\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 3,\n" +
                    "\t\t\"horse_name\": \"Classical Sound\",\n" +
                    "\t\t\"jockey_name\": \"Adam Nicol\",\n" +
                    "\t\t\"form\": \"8P\",\n" +
                    "\t\t\"odds\": \"201.0\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 4,\n" +
                    "\t\t\"horse_name\": \"Reivers Lad\",\n" +
                    "\t\t\"jockey_name\": \"Craig Nichol\",\n" +
                    "\t\t\"form\": \"213-4\",\n" +
                    "\t\t\"odds\": \"2.9\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 5,\n" +
                    "\t\t\"horse_name\": \"Al Reesha\",\n" +
                    "\t\t\"jockey_name\": \"Harry Skelton\",\n" +
                    "\t\t\"form\": \"651-14\",\n" +
                    "\t\t\"odds\": \"8.0\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 6,\n" +
                    "\t\t\"horse_name\": \"Paper Roses\",\n" +
                    "\t\t\"jockey_name\": \"Callum Whillans\",\n" +
                    "\t\t\"form\": \"32-3U7\",\n" +
                    "\t\t\"odds\": \"15.0\"\n" +
                    "\t}, {\n" +
                    "\t\t\"number\": 7,\n" +
                    "\t\t\"horse_name\": \"Wynford\",\n" +
                    "\t\t\"jockey_name\": \"Jamie Bargary\",\n" +
                    "\t\t\"form\": \"P\",\n" +
                    "\t\t\"odds\": \"29.0\"\n" +
                    "\t}]\n" +
                    "}\n";

    @Test
    public void processJSONExample(){
        RaceItem expectedRaceItem = new RaceItem("Kelso", "2017-02-16T13:25:00.000Z", 3218.7 / 201.168);
        List<RunnerItem> expectedRunnerItemList = new ArrayList<>();
        expectedRunnerItemList.add(new RunnerItem(1, "Lycidas", "Brian Hughes", "4-2139", 2.3));
        expectedRunnerItemList.add(new RunnerItem(4, "Reivers Lad", "Craig Nichol", "213-4", 2.9));
        expectedRunnerItemList.add(new RunnerItem(5, "Al Reesha", "Harry Skelton", "651-14", 8.0));
        expectedRunnerItemList.add(new RunnerItem(6, "Paper Roses", "Callum Whillans", "32-3U7", 15.0));
        expectedRunnerItemList.add(new RunnerItem(7, "Wynford", "Jamie Bargary", "P", 29.0));
        expectedRunnerItemList.add(new RunnerItem(2, "Balthazar", "Adrian Lane", "4-2139", 51.0));
        expectedRunnerItemList.add(new RunnerItem(3, "Classical Sound", "Adam Nicol", "8P", 201.0));
        expectedRaceItem.setRunnerList(expectedRunnerItemList);

        RetrieveRaceInfo retrieveRaceInfo = new RetrieveRaceInfo(null);//handler not required for this test
        RaceItem actualRaceItem = retrieveRaceInfo.processJSONToRace(mExampleData);

        assertEquals(expectedRaceItem.getCourse(), actualRaceItem.getCourse());
        assertEquals(expectedRaceItem.getTime(), actualRaceItem.getTime());
        assertEquals(expectedRaceItem.getDistance(), actualRaceItem.getDistance(), 0);

        List<RunnerItem> actualRunnersList = actualRaceItem.getRunnerList();
        int runnerListSize = expectedRunnerItemList.size();
        assertEquals(runnerListSize, actualRunnersList.size());

        for(int i = 0; i < runnerListSize; i ++){
            RunnerItem expectedRunnerItem = expectedRunnerItemList.get(i);
            RunnerItem actualRunnerItem = actualRunnersList.get(i);

            assertEquals(expectedRunnerItem.getNumber(), actualRunnerItem.getNumber());
            assertEquals(expectedRunnerItem.getHorseName(), actualRunnerItem.getHorseName());
            assertEquals(expectedRunnerItem.getJockeyName(), actualRunnerItem.getJockeyName());
            assertEquals(expectedRunnerItem.getForm(), actualRunnerItem.getForm());
            assertEquals(expectedRunnerItem.getOdds(), actualRunnerItem.getOdds());
        }
    }
}
