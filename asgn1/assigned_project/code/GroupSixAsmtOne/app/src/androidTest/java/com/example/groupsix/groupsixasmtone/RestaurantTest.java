package com.example.groupsix.groupsixasmtone;


import junit.framework.TestCase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RestaurantTest extends TestCase {
    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    /**
     * Test two restaurants to be equal
     */
    public void testEquals() {
        // Make restaurant open from 7 AM to 10 PM on Monday
        int[] opentimes = {420, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1200, 0, 0, 0, 0, 0, 0};

        //See whether two different restaurants return true
        Restaurant r = new Restaurant("", "", false, false, false,
                0, 0, opentimes, closetimes);

        Restaurant d = new Restaurant("", "", true, false, false,
                0, 0, opentimes, closetimes);

        assertFalse(r.equals(d));
    }

    /**
     * Restaurant should always be open in the below case
     */
    public void testGetOpen() {
        // Make restaurant open 24/7
        int[] opentimes = {0, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1440, 1440, 1440, 1440, 1440, 1440, 1440};

        Restaurant r = new Restaurant("", "", false, false, false,
                0, 0, opentimes, closetimes);

        assertTrue(r.isOpen());
    }

    /**
     * Restaurant should be open in the below case
     */
    public void testGetOpenAtTimeAndDay() {
        // Make restaurant open from 7 AM to 10 PM on Monday
        int[] opentimes = {420, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1200, 0, 0, 0, 0, 0, 0};

        Restaurant r = new Restaurant("", "", false, false, false,
                0, 0, opentimes, closetimes);

        // 0 is the first element in the array which corresponds to Monday's open time
        assertTrue(r.isOpen(0, 600));
    }

    /**
     * Restaurant should be open in the below case
     */
    public void testTimeToClose() {
        // Make restaurant open 24/7
        int[] opentimes = {0, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1440, 1440, 1440, 1440, 1440, 1440, 1440};

        Restaurant r = new Restaurant("", "", false, false, false,
                0, 0, opentimes, closetimes);

        // Do the same thing as the method
        Calendar now = GregorianCalendar.getInstance();
        int today = now.get(Calendar.DAY_OF_WEEK);
        int closingtime = closetimes[today];

        int t = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
        int timetoclose = closingtime - t;

        assertTrue(r.timeToClose(today, t) == timetoclose);
    }

    /**
     * Restaurant should assert true for on campus
     */
    public void testisOnCampus() {
        int[] opentimes = {0, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1440, 1440, 1440, 1440, 1440, 1440, 1440};

        Restaurant r = new Restaurant("", "", true, false, false,
                0, 0, opentimes, closetimes);

        assertTrue(r.isOnCampus());
    }

    /**
     * Restaurant should assert true for meal plan
     */
    public void testisMealPlan() {
        int[] opentimes = {0, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1440, 1440, 1440, 1440, 1440, 1440, 1440};

        Restaurant r = new Restaurant("", "", false, true, false,
                0, 0, opentimes, closetimes);

        assertTrue(r.isMealPlan());
    }

    /**
     * Restaurant should assert true for delivery
     */
    public void testisDelivers() {
        int[] opentimes = {0, 0, 0, 0, 0, 0, 0};
        int[] closetimes =  {1440, 1440, 1440, 1440, 1440, 1440, 1440};

        Restaurant r = new Restaurant("", "", false, false, true,
                0, 0, opentimes, closetimes);

        assertTrue(r.isDelivers());
    }
}
