package i_neda.com.raceview.data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by cam on 26/02/2018.
 * test constructor, setter and getters
 */

public class RaceItemTest {
    @Test
    public void testCreationSetterAndGetters() {
        String expectedCourse = "just outside";
        String expectedTime = "30 minutes ago";
        Double expectedDistance = 3.1;
        List<RunnerItem> expectedRunnerItemList = new ArrayList<>();
        expectedRunnerItemList.add(new RunnerItem(1, "Lycidas", "Brian Hughes", "4-2139", 2.3));
        expectedRunnerItemList.add(new RunnerItem(4, "Reivers Lad", "Craig Nichol", "213-4", 2.9));

        RaceItem raceItem = new RaceItem(expectedCourse, expectedTime, expectedDistance);
        raceItem.setRunnerList(expectedRunnerItemList);

        assertEquals(expectedCourse, raceItem.getCourse());
        assertEquals(expectedTime, raceItem.getTime());
        assertEquals(expectedDistance, raceItem.getDistance(), 0);

        assertEquals(expectedRunnerItemList, raceItem.getRunnerList());
    }
}
