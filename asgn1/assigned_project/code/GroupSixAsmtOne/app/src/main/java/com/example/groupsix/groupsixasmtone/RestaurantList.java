package com.example.groupsix.groupsixasmtone;


import flexjson.JSONDeserializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by clarkperkins on 9/19/14.
 *
 */



public class RestaurantList extends ArrayList<Restaurant> {

    private final String FILENAME = "test.json";

    private static RestaurantList restaurantList;

    /**
     * Default constructor.  Loads information from the JSON file.
     */
    private RestaurantList() {
        this(true);
    }

    /**
     * Alternate constructor, can specify if you want data loaded
     * @param loadData true if loading data from source
     */
    private RestaurantList(boolean loadData) {
        super();
        if (loadData) {
            // Load the data from the data source, either JSON file or server
            loadData();
        }
    }

    /**
     * Get an empty restaurant list
     * @return the empty list
     */
    private static RestaurantList getEmptyList() {
        return new RestaurantList(false);
    }

    public static RestaurantList getInstance() {
        if (restaurantList == null) {
            restaurantList = new RestaurantList();
        }
        return restaurantList;
    }

    /**
     * Load data from a JSON file or server (we do a local JSON file)
     */
    private void loadData() {

        try {
            Scanner in = new Scanner(new FileReader(FILENAME));

            String jsonText = "";

            while (in.hasNextLine()) {
                jsonText += in.nextLine();
            }

            List<Restaurant> restaurants = new JSONDeserializer<List<Restaurant>>().use("values", Restaurant.class).deserialize(jsonText);

            for (Restaurant restaurant : restaurants) {
                add(restaurant);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get a List of Restaurant objects that are currently open
     * @return the list of Restaurant objects
     */
    public RestaurantList getOpen() {
        RestaurantList ret = getEmptyList();

        for (Restaurant r : this) {
            if (r.isOpen()) {
                // Add a copy
                ret.add(new Restaurant(r));
            }
        }

        return ret;
    }

    /**
     * Get all the Restaurants that accept the meal plan
     * @return the list of Restaurants
     */
    public RestaurantList getMealPlan() {
        RestaurantList ret = getEmptyList();

        for (Restaurant r : this) {
            if (r.isMealPlan()) {
                // Add a copy
                ret.add(new Restaurant(r));
            }
        }

        return ret;
    }

    /**
     * Get all the Restaurants that have a specific type of food
     * @param type the type of food to match
     * @return the list of matching Restaurants
     */
    public RestaurantList getByFoodType(String type) {
        RestaurantList ret = getEmptyList();

        for (Restaurant r : this) {
            if (Arrays.asList(r.getFoodType().split(",")).contains(type)) {
                // Add a copy
                ret.add(new Restaurant(r));
            }
        }

        return ret;
    }

    /**
     * Find all the Restaurants that are on the Taste of Nashville plan
     * @return the list of Restaurants
     */
    public RestaurantList getToN() {
        RestaurantList ret = getEmptyList();

        for (Restaurant r : this) {
            if (!r.isOnCampus()) {
                // Add a copy
                ret.add(new Restaurant(r));
            }
        }

        return ret;
    }

    /**
     * Sort the current list in place by distance from a given location
     * @param lat the source latitude
     * @param lon the source longitude
     */
    public void sortByDistance(float lat, float lon) {
        Collections.sort(this, new RestaurantDistanceComparator(lat, lon));
    }

    /**
     * Sort the current list in place by time until close
     */
    public void sortByTime() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        Collections.sort(this, new RestaurantTimeComparator(day, hours, minutes));
    }

    /**
     * A comparator for use in the distance sorting method
     */
    private static class RestaurantDistanceComparator implements Comparator<Restaurant> {

        private float lat;
        private float lon;

        public RestaurantDistanceComparator(float lat, float lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public int compare(Restaurant restaurant, Restaurant restaurant2) {
            float r1 = restaurant.getDistanceFrom(lat, lon);
            float r2 = restaurant2.getDistanceFrom(lat, lon);

            if (r1 < r2) {
                return -1;
            } else if (r1 > r2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * A comparator for use in the time sorting method
     */
    private static class RestaurantTimeComparator implements Comparator<Restaurant> {

        private int day;
        private int hours;
        private int minutes;

        public RestaurantTimeComparator(int day, int hours, int minutes) {
            this.day = day;
            this.hours = hours;
            this.minutes = minutes;
        }

        @Override
        public int compare(Restaurant restaurant, Restaurant restaurant2) {
            return restaurant.timeToClose() - restaurant2.timeToClose();
        }
    }
}


