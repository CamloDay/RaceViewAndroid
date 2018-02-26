package i_neda.com.raceview.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by cam on 26/02/2018.
 * test constructor and getters
 */

public class RunnerItemTest {
    @Test
    public void testCreationAndGetters() {
        int expectedNumber = 1;
        String expectedHorseName = "horsey";
        String expectedJockeyName = "Fred";
        String expectedForm = "3";
        Double expectedOdds = 3.0;

        RunnerItem runnerItem = new RunnerItem(expectedNumber, expectedHorseName, expectedJockeyName, expectedForm, expectedOdds);

        assertEquals(expectedNumber, runnerItem.getNumber());
        assertEquals(expectedHorseName, runnerItem.getHorseName());
        assertEquals(expectedJockeyName, runnerItem.getJockeyName());
        assertEquals(expectedForm, runnerItem.getForm());
        assertEquals(expectedOdds, runnerItem.getOdds());
    }
}
