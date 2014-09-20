package com.example.groupsix.groupsixasmtone;

import java.util.Comparator;

/**
 * Created by clarkperkins on 9/19/14.
 *
 */

public class Restaurant {

    private String name;

    private double latitude;

    private double longitude;

    private int openHour;

    private int openMinute;

    private int closeHour;

    private int closeMinute;

    private String mealPlanType;

    private String foodType;

    public Restaurant() {
        super();
        name = "";
        latitude = 0;
        longitude = 0;
        openHour = 9;
        openMinute = 0;
        closeHour = 17;
        closeMinute = 0;
        mealPlanType = "Meal Plan";
    }

    public Restaurant(Restaurant other) {
        super();
        name = other.name;
        latitude = other.latitude;
        longitude = other.longitude;
        openHour = other.openHour;
        openMinute = other.openMinute;
        closeHour = other.closeHour;
        closeMinute = other.closeMinute;
        mealPlanType = other.mealPlanType;
    }

    public double getDistanceFrom(double lat, double lon) {
        return 0;
    }

    public boolean isOpen() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getOpenHour() {
        return openHour;
    }

    public void setOpenHour(int openHour) {
        this.openHour = openHour;
    }

    public int getOpenMinute() {
        return openMinute;
    }

    public void setOpenMinute(int openMinute) {
        this.openMinute = openMinute;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(int closeHour) {
        this.closeHour = closeHour;
    }

    public int getCloseMinute() {
        return closeMinute;
    }

    public void setCloseMinute(int closeMinute) {
        this.closeMinute = closeMinute;
    }

    public String getMealPlanType() {
        return mealPlanType;
    }

    public void setMealPlanType(String mealPlanType) {
        this.mealPlanType = mealPlanType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

}
