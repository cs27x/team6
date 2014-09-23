package com.example.groupsix.groupsixasmtone;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Restaurant {
// Need constructor and set methods

    private String name;
    private String foodType;
    private boolean onCampus;
    private boolean mealMoney;
    private boolean delivers;
    private float latitude;
    private float longitude;


    // First element is Monday open time
    private int[] openTimes;

    // Last element is Monday close time
    private int[] closingTimes;

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

    // Constructor using JSON loaded parameters
    public Restaurant(String Name,
                      String TypeOfFood,
                      Boolean OnCampus,
                      Boolean MealMoney,
                      Boolean Delivers,
                      float Latitude,
                      float Longitude,
                      int[] Opentimes,
                      int[] Closingtimes)
    {
        this.name = Name;
        this.foodType = TypeOfFood;
        this.onCampus = OnCampus;
        this.mealMoney = MealMoney;
        this.delivers = Delivers;
        this.latitude = Latitude;
        this.longitude = Longitude;
        this.openTimes = Opentimes;
        this.closingTimes = Closingtimes;
    }

    private static Calendar getCurrentCalendar() {
        Date date = new Date();
        Calendar now = GregorianCalendar.getInstance();
        now.setTime(date);
        return now;
    }

    public boolean isOpen() {
        // Checks to see if restaurant is open now
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

    public boolean isOpen(int day, int time) {
        // Checks to see if restaurant is open at specified day and time

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

    public int timeToClose() {
        Calendar now = getCurrentCalendar();

        if (isOpen()) {
            int today = now.get(Calendar.DAY_OF_WEEK);
            int closingtime = closingTimes[today];

            int to = closingtime;
            int t = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
            int timetoclose = to - t;
            return timetoclose;
        } else {
            return -1;
        }
    }

    public float getDistanceFrom(float lat, float lon) {
        return 0;
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

    public boolean isonCampus() {
        return onCampus;
    }

    public String getFoodType() {
        return foodType;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    /**
     * Returns an array of open and close times for today
     * in integer format (military time)
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

    // Setter methods

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

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setOpenTimes(int[] openTimes) {
        this.openTimes = openTimes;
    }

    public void setClosingTimes(int[] closingTimes) {
        this.closingTimes = closingTimes;
    }
}