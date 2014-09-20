package com.example.groupsix.groupsixasmtone;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Restaurant {
// Need constructor and set methods

	private String name;
	private String typeoffood;
	private boolean oncampus;
	private boolean mealmoney;
	private boolean delivers;
	private float latitude;
	private float longitude;
	
	Date date = new Date();
	Calendar now = Calendar.getInstance();
	private int[] opentimes = new int [7]; // First element is Monday open time
	// Last element is Monday close time
	private int[] closingtimes = new int [7];

    public Restaurant()
    {
        name = "";
        typeoffood = "";
        oncampus = false;
        mealmoney = false;
        delivers = false;
        latitude = 0;
        longitude = 0;
    }

	public boolean isOpen()
	{
		// Checks to see if restaurant is open now
		int today = now.get(Calendar.DAY_OF_WEEK);
		int openingtime = opentimes[today];
		int closingtime = closingtimes[today];
		
		if (openingtime >= 0)
		{
		int from = openingtime;
	    int to = closingtime;
	    Date date = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
	    boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
		
		return isBetween;
		}
		else
			return false;
	}
	
	public boolean isOpen(int day)
	{
		// Checks to see if restaurant is open at specified day
				
				int openingtime = opentimes[day];
				
				int closingtime = closingtimes[day];
				
				// Check for 0 in array
				if (openingtime >= 0)
				{
					int from = openingtime;
				    int to = closingtime;
				    Date date = new Date();
				    Calendar c = Calendar.getInstance();
				    c.setTime(date);
				    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
				    boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
					
					return isBetween;
				}
				else
					return false;
	}
	
	public int[] getTimes()
	{
		// Returns an array of open and close times for today
		// in integer format (military time)
		
		int[] times = new int [2];
		
		int today = now.get(Calendar.DAY_OF_WEEK);
		times[0] = opentimes[today];
		times[1] = closingtimes[today];
		return times;
	}
	
    // Getter methods
	
	public boolean isMealPlan()
	{
		return mealmoney;
	}
	
	public boolean hasDelivery()
	{
		return delivers;
	}
	
	public boolean isonCampus()
	{
		return oncampus;
	}
	
	public String typeOfFood()
	{
		return typeoffood;
	}
	
	public int timeToClose()
	{
		if (isOpen())
		{
			int today = now.get(Calendar.DAY_OF_WEEK);
			int closingtime = closingtimes[today];
			
		    int to = closingtime;
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
		    int timetoclose = to - t;
		    return timetoclose;
		}
		else
			return -1;
	}

    // Setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeoffood(String typeoffood) {
        this.typeoffood = typeoffood;
    }

    public void setOncampus(boolean oncampus) {
        this.oncampus = oncampus;
    }

    public void setMealmoney(boolean mealmoney) {
        this.mealmoney = mealmoney;
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

    public void setOpentimes(int[] opentimes) {
        this.opentimes = opentimes;
    }

    public void setClosingtimes(int[] closingtimes) {
        this.closingtimes = closingtimes;
    }
}