package com.example.groupsix.groupsixasmtone;

import android.location.Location;
import android.util.Log;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Restaurant implements Serializable {
// Need constructor and set methods

    private String name;
    private String foodType;
    private boolean onCampus;
    private boolean mealMoney;
    private boolean delivers;
    private double latitude;
    private double longitude;


    // First element is Monday open time
    private int[] openTimes;

    // Last element is Monday close time
    private int[] closingTimes;

    /**
     * Default constructor
     */
    public Restaurant() {
        this.name = "";
        this.foodType = "";
        this.onCampus = false;
        this.mealMoney = false;
        this.delivers = false;
        this.latitude = 0;
        this.longitude = 0;
        this.openTimes = new int[7];
        this.closingTimes = new int[7];
    }

    /**
     * Copy constructor
     *
     * @param other the other Restaurant
     */
    public Restaurant(Restaurant other) {
        this.name = other.name;
        this.foodType = other.foodType;
        this.onCampus = other.onCampus;
        this.mealMoney = other.mealMoney;
        this.delivers = other.delivers;
        this.latitude = other.latitude;
        this.longitude = other.longitude;
        this.openTimes = Arrays.copyOf(other.openTimes, other.openTimes.length);
        this.closingTimes = Arrays.copyOf(other.closingTimes, other.closingTimes.length);
    }

    /**
     * Extra constructor
     *
     * @param name         restaurant name
     * @param foodType     type of food at restaurant
     * @param onCampus     is the food on campus?
     * @param mealMoney    do they accept meal money?
     * @param delivers     do they deliver?
     * @param latitude     latitude location
     * @param longitude    longitude location
     * @param openTimes    weekly opening times
     * @param closingTimes weekly closing times
     */
    public Restaurant(String name,
                      String foodType,
                      boolean onCampus,
                      boolean mealMoney,
                      boolean delivers,
                      double latitude,
                      double longitude,
                      int[] openTimes,
                      int[] closingTimes) {
        this.name = name;
        this.foodType = foodType;
        this.onCampus = onCampus;
        this.mealMoney = mealMoney;
        this.delivers = delivers;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openTimes = openTimes;
        this.closingTimes = closingTimes;
    }

    /**
     * Get a calendar representing the current time
     *
     * @return the calendar object
     */
    private static Calendar getCurrentCalendar() {
        Date date = new Date();
        Calendar now = GregorianCalendar.getInstance();
        now.setTime(date);
        return now;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Restaurant) {
            Restaurant r = (Restaurant) o;

            return name.equals(r.name) && foodType.equals(r.foodType)
                    && onCampus == r.onCampus && mealMoney == r.mealMoney && delivers == r.delivers
                    && Math.abs(latitude - r.latitude) < .01 && Math.abs(longitude - r.longitude) < .01
                    && Arrays.equals(openTimes, r.openTimes) && Arrays.equals(closingTimes, r.closingTimes);
        } else {
            return false;
        }
    }

    /**
     * Find out whether the restaurant is open now
     *
     * @return true if open, false otherwise
     */
    public boolean isOpen() {
        Calendar now = getCurrentCalendar();

        int today = now.get(Calendar.DAY_OF_WEEK);
        int openTime = openTimes[today];
        int closeTime = closingTimes[today];

        if (openTime >= 0 && closeTime >= 0) {
            int currentTime = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
            return openTime <= currentTime && currentTime <= closeTime;
//            boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
//
//            return isBetween;
        } else {
            return false;
        }
    }

    /**
     * Find out if the restaurant is open at specified day and time
     *
     * @param day  the day of week (Monday = 0)
     * @param time the time in minutes after midnight
     * @return true if open, false otherwise
     */
    public boolean isOpen(int day, int time) {

        int openTime = openTimes[day];
        int closeTime = closingTimes[day];

        // Check for 0 in array
        return openTime >= 0 && closeTime >= 0 && openTime <= time && time <= closeTime;

//        if (openTime >= 0) {
//            int from = openTime;
//            int to = closeTime;
//            Date date = new Date();
//            Calendar c = Calendar.getInstance();
//            c.setTime(date);
//            int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
//            boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
//
//            return isBetween;
//        } else
//            return false;
    }

    /**
     * Find out how much longer the restaurant is open
     *
     * @param day the day of week (Monday = 0)
     * @param time the time in minutes after midnight
     * @return the number of minutes until close
     */
    public int timeToClose(int day, int time) {

        int closeTime = this.getClosingTime(day);

        if (closeTime == -1) {
            return -1;
        } else {
            return closeTime - time;
        }

//        if (isOpen()) {
//            int today = now.get(Calendar.DAY_OF_WEEK);
//            int closingtime = closingTimes[today];
//
//            int to = closingtime;
//            int t = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
//            int timetoclose = to - t;
//            return timetoclose;
//        } else {
//            return -1;
//        }
    }

    /**
     * Find out how far a location is from this restaurant
     *
     * @param lat the source latitude
     * @param lon the source longitude
     * @return the distance in meters
     */
    public double getDistanceFrom(double lat, double lon) {
        Location location = new Location("");
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);

        Location other = new Location("");
        other.setLatitude(lat);
        other.setLongitude(lon);

        return location.distanceTo(other);
    }

    /**
     * Returns a pretty string for the opening time
     *
     * @param day the day of week (Monday = 0)
     * @return the pretty string
     */
    public String getFormattedOpenTimeString(int day) {
        int time = this.getOpenTime(day);

        return getFormattedStringForTime(time);
    }

    /**
     * Returns a pretty string for the closing time
     *
     * @param day the day of week (Monday = 0)
     * @return the pretty string
     */
    public String getFormattedClosedTimeString(int day) {
        int time = this.getClosingTime(day);

        return getFormattedStringForTime(time);
    }

    /**
     * Does the bulk of the work for pretty printing.
     *
     * @param time the time in minutes after midnight
     * @return the pretty string
     */
    private String getFormattedStringForTime(int time) {
        if (time == -1) {
            return "closed";
        }

        int hours = time / 60;
        int minutes = time % 60;

        String ret = "";

        boolean am = true;

        // calculate the hours
        if (hours == 0 || hours == 24) {
            ret += "12";
        } else if (hours > 12) {
            ret += hours % 12;
            if ((hours / 12) % 2 == 1) {
                am = false;
            }
        } else {
            ret += hours;

            if (hours == 12) {
                am = false;
            }
        }

        ret += ":";

        // Calculate the minutes
        if (minutes < 10) {
            ret += "0";
        }

        ret += minutes + " ";

        // Add the AM / PM
        if (am) {
            ret += "AM";
        } else {
            ret += "PM";
        }

        return ret;
    }

    @Override
    public String toString() {
        return name + " / " + foodType + " / " + Arrays.toString(openTimes) + " / " + Arrays.toString(closingTimes);
    }

    /**
     * For Serializable
     *
     * @param out
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        JSONSerializer serializer = new JSONSerializer();
        serializer.include("openTimes");
        serializer.include("closingTimes");

        // Serializer.serialize returns a string, which can be serialized
        out.writeObject(serializer.serialize(this));
    }

    /**
     * For Serializable
     *
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String serialized = (String) in.readObject();

        JSONDeserializer<Restaurant> deserializer = new JSONDeserializer<Restaurant>();

        Restaurant other = deserializer.deserialize(serialized, Restaurant.class);

        this.name = other.name;
        this.foodType = other.foodType;
        this.onCampus = other.onCampus;
        this.mealMoney = other.mealMoney;
        this.delivers = other.delivers;
        this.latitude = other.latitude;
        this.longitude = other.longitude;
        this.openTimes = other.openTimes;
        this.closingTimes = other.closingTimes;
    }

    /**
     * For Serializable
     *
     * @throws ObjectStreamException
     */
    private void readObjectNoData() throws ObjectStreamException {
        this.name = "";
        this.foodType = "";
        this.onCampus = false;
        this.mealMoney = false;
        this.delivers = false;
        this.latitude = 0;
        this.longitude = 0;
        this.openTimes = new int[7];
        this.closingTimes = new int[7];
    }


    // Getter methods

    public String getName() {
        return name;
    }

    public boolean isMealPlan() {
        return mealMoney;
    }

    public boolean hasDelivery() {
        return delivers;
    }

    public String getFoodType() {
        return foodType;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean isOnCampus() {
        return onCampus;
    }

    public boolean isMealMoney() {
        return mealMoney;
    }

    public boolean isDelivers() {
        return delivers;
    }

    public int[] getOpenTimes() {
        return openTimes;
    }

    public int[] getClosingTimes() {
        return closingTimes;
    }

    public int getOpenTime(int day) {
        day = (day - 2) % 7;
        if(day < 0) {
            day += 7;
        }
        return this.openTimes[day];
    }

    public int getClosingTime(int day) {
        day = (day - 2) % 7;
        if(day < 0) {
            day += 7;
        }
        return this.closingTimes[day];
    }

    /**
     * Returns an array of open and close times for today
     * in integer format representing the number of minutes after midnight
     * i.e. 10:00 AM = 600, 1:00 AM = 60, etc
     *
     * @return
     */
    public int[] getTimes() {

        Calendar now = getCurrentCalendar();

        int[] times = new int[2];

        int today = now.get(Calendar.DAY_OF_WEEK);
        times[0] = openTimes[today];
        times[1] = closingTimes[today];
        return times;
    }


    // Setter methods (needed for json deserialization)

    public void setName(String name) {
        this.name = name;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setOnCampus(boolean onCampus) {
        this.onCampus = onCampus;
    }

    public void setMealMoney(boolean mealMoney) {
        this.mealMoney = mealMoney;
    }

    public void setDelivers(boolean delivers) {
        this.delivers = delivers;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setOpenTimes(int[] openTimes) {
        this.openTimes = openTimes;
    }

    public void setClosingTimes(int[] closingTimes) {
        this.closingTimes = closingTimes;
    }
}