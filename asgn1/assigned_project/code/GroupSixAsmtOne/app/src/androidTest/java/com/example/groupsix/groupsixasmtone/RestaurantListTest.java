package com.example.groupsix.groupsixasmtone;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by clarkperkins on 9/20/14.
 *
 */
public class RestaurantListTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private RestaurantList restaurantList;

    private static boolean arrayContains(Object[] arr, Object obj) {
        for (Object o : arr) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public RestaurantListTest() {
        super(MainActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MainActivity activity = getActivity();
        restaurantList = RestaurantList.getInstance(activity.getResources());
    }

    public void testGetInstance() {
        RestaurantList rl = RestaurantList.getInstance();

        assertSame(restaurantList, rl);
    }

    public void testJSONLoad() {

        assertEquals(restaurantList.size(), 12);

        for (Object o : restaurantList) {
            assertTrue(o instanceof Restaurant);

            Restaurant r = (Restaurant) o;

            assertFalse(r.getName().equals(""));
            assertFalse(r.getFoodType().equals(""));

            assertEquals(r.getOpenTimes().length, 7);
            assertEquals(r.getClosingTimes().length, 7);


            // Test to make sure everything was actually loaded
            int numZeros = 0;
            int idx = 0;
            for (int open : r.getOpenTimes()) {
                if (open == 0) {
                    ++numZeros;
                }
                if (open == 420) {
                    assertEquals(r.getFormattedOpenTimeString(idx), "7:00 AM");
                }
                ++idx;
            }

            assertFalse(numZeros == 7);

            numZeros = 0;
            idx = 0;
            for (int close : r.getClosingTimes()) {
                if (close == 0) {
                    ++numZeros;
                }
                if (close == 900) {
                    assertEquals(r.getFormattedClosedTimeString(idx), "3:00 PM");
                }
                ++idx;
            }

            assertFalse(numZeros == 7);
        }
    }

    public void testGetOpen() {
        RestaurantList open = restaurantList.getOpen();

        for (Restaurant r : open) {
            assertTrue(r.isOpen());

            // make sure we get a copy back and not the original object
            int restIndex = restaurantList.indexOf(r);
            assertNotSame(r, restaurantList.get(restIndex));
        }
    }

    public void testGetMealPlan() {
        RestaurantList mealPlan = restaurantList.getMealPlan();

        for (Restaurant r : mealPlan) {
            assertTrue(r.isMealPlan());

            // make sure we get a copy back and not the original object
            int restIndex = restaurantList.indexOf(r);
            assertNotSame(r, restaurantList.get(restIndex));
        }
    }

    public void foodTypeTestHelper(String foodType) {
        RestaurantList foodTypeList = restaurantList.getByFoodType(foodType);

        for (Restaurant r : foodTypeList) {
            String[] types = r.getFoodType().split(",");

            assertTrue(arrayContains(types, foodType));

            // make sure we get a copy back and not the original object
            int restIndex = restaurantList.indexOf(r);
            assertNotSame(r, restaurantList.get(restIndex));
        }
    }

    public void testGetByFoodType() {
        String[] typesToTest = {"American", "Asian", "Subs", "Pizza"};

        for (String type : typesToTest) {
            foodTypeTestHelper(type);
        }
    }

    public void testGetToN() {
        RestaurantList ton = restaurantList.getToN();

        for (Restaurant r : ton) {
            assertFalse(r.isOnCampus());

            // make sure we get a copy back and not the original object
            int restIndex = restaurantList.indexOf(r);
            assertNotSame(r, restaurantList.get(restIndex));
        }
    }

    public void testSortByDistance() {
        double lat = 73.1282;
        double lon = 35.12441;
        restaurantList.sortByDistance(lat, lon);

        double prev = 1001;

        for (Restaurant r : restaurantList) {
            if (prev > 1000) {
                prev = r.getDistanceFrom(lat, lon);
            } else {
                assertTrue(prev <= r.getDistanceFrom(lat, lon));
            }
        }
    }

    public void testSortByTime() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        int totalMinutes = hours * 60 + minutes;

        restaurantList.sortByTime(day, hours, minutes);

        int prev = 10000;

        for (Restaurant r : restaurantList) {
            if (prev == 10000) {
                prev = r.getClosingTimes()[day] - totalMinutes;
            } else {
                assertTrue(prev <= (r.getClosingTimes()[day] - totalMinutes));
            }
        }
    }
}
