package com.example.groupsix.groupsixasmtone;


import android.content.res.Resources;
import flexjson.JSONDeserializer;

import java.io.*;
import java.util.*;

/**
 * Created by clarkperkins on 9/19/14.
 */


public class RestaurantList extends ArrayList<Restaurant> {

    private static final String FILENAME = "restaurants";

    private static RestaurantList restaurantList;

    private Resources resources;

    /**
     * Default constructor.  Loads information from the JSON file.
     */
    private RestaurantList(Resources res) {
        this(true, res);
    }

    /**
     * Alternate constructor, can specify if you want data loaded
     *
     * @param loadData true if loading data from source
     */
    private RestaurantList(boolean loadData, Resources res) {
        super();
        this.resources = res;
        if (loadData) {
            // Load the data from the data source, either JSON file or server
            loadData();
        }
    }

    /**
     * Get an empty restaurant list
     *
     * @return the empty list
     */
    private static RestaurantList getEmptyList() {
        return new RestaurantList(false, null);
    }

    /**
     * For singleton class - get the one and only instance
     *
     * @param res Resources object
     * @return the single RestaurantList
     */
    public static RestaurantList getInstance(Resources res) {
        if (restaurantList == null) {
            restaurantList = new RestaurantList(res);
        }
        return restaurantList;
    }

    /**
     * For testing
     *
     * @return the single RestaurantList
     */
    public static RestaurantList getInstance() throws InstantiationError {
        if (restaurantList == null) {
            throw new InstantiationError("You must call getInstance(Resources res) at least once before now");
        }
        return restaurantList;
    }

    /**
     * Load data from a JSON file or server (we do a local JSON file)
     */
    private void loadData() {

        try {
            //get the resource id from the file name
            int rID = resources.getIdentifier("com.example.groupsix.groupsixasmtone:raw/" + FILENAME, null, null);
            //get the file as a stream
            InputStream iS = resources.openRawResource(rID);

            //create a buffer that has the same size as the InputStream
            byte[] buffer = new byte[iS.available()];
            //read the text file as a stream, into the buffer
            iS.read(buffer);
            //create a output stream to write the buffer into
            ByteArrayOutputStream oS = new ByteArrayOutputStream();
            //write this buffer to the output stream
            oS.write(buffer);
            //Close the Input and Output streams
            oS.close();
            iS.close();

            // Convert to string
            String jsonText = oS.toString();

            List<Restaurant> restaurants = new JSONDeserializer<List<Restaurant>>().use("values", Restaurant.class).deserialize(jsonText);

            for (Restaurant restaurant : restaurants) {
                add(restaurant);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get a List of Restaurant objects that are currently open
     *
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
     *
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
     *
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
     *
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
     *
     * @param lat the source latitude
     * @param lon the source longitude
     */
    public void sortByDistance(double lat, double lon) {
        Collections.sort(this, new RestaurantDistanceComparator(lat, lon));
    }

    /**
     * Use current time to sort list
     */
    public void sortByTime() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        sortByTime(day, hours, minutes);
    }


    /**
     * Sort the current list in place by time until close
     */
    public void sortByTime(int day, int hours, int minutes) {
        Collections.sort(this, new RestaurantTimeComparator(day, hours, minutes));
    }

    /**
     * A comparator for use in the distance sorting method
     */
    private static class RestaurantDistanceComparator implements Comparator<Restaurant> {

        private double lat;
        private double lon;

        public RestaurantDistanceComparator(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        @Override
        public int compare(Restaurant restaurant, Restaurant restaurant2) {
            double r1 = restaurant.getDistanceFrom(lat, lon);
            double r2 = restaurant2.getDistanceFrom(lat, lon);

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


