package restaurant_class;

import java.sql.Time;
import java.util.Date;

public class restaurant_class {
	
	private final String name;
	private final Boolean oncampus;
	private final Boolean mealmoney;
	private final float latitude;
	private final float longitude;	
	
	private final Time[] opentimes;
	private final Time[] closingtimes;

	public boolean isOpen()
	{
		return true;
	}
	
	public boolean isMealPlan()
	{
		return true;
	}
	
	public boolean hasDelivery()
	{
		return true;
	}
	
	public boolean isonCampus()
	{
		return true;
	}
	
	public String typeOfFood()
	{
		return "Chinese";
	}
	
	public String getStartingHour(int day)
	{
		String hour = "8 AM";
		return hour;
	}
	
	public String getClosingHour(int day)
	{
		String hour = "6 PM";
		return hour;
	}
	
	public Date timeToClose()
	{
		Date hours = null;
		return hours;
	}
}