package restaurant_class;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class restaurant_class {
	
	private final String name = "";
	private final String typeoffood = "";
	private final Boolean oncampus = false;
	private final Boolean mealmoney = false;
	private final Boolean delivers = false;
	private final float latitude = 0;
	private final float longitude = 0;	
	
	Date date = new Date();
	Calendar now = Calendar.getInstance();
<<<<<<< HEAD
	private final int[] opentimes = new int [7]; // First element is Monday open time
	// Last element is Monday close time
=======
	private final int[] opentimes = new int [7];
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
	private final int[] closingtimes = new int [7];

	public boolean isOpen()
	{
		// Checks to see if restaurant is open now
		int today = now.get(Calendar.DAY_OF_WEEK);
		int openingtime = opentimes[today];
		int closingtime = closingtimes[today];
		
<<<<<<< HEAD
		if (openingtime >= 0)
		{
=======
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
		int from = openingtime;
	    int to = closingtime;
	    Date date = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
	    boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
		
		return isBetween;
<<<<<<< HEAD
		}
		else
			return false;
=======
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
	}
	
	public boolean isOpen(int day)
	{
		// Checks to see if restaurant is open at specified day
<<<<<<< HEAD
				
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
=======
				int openingtime = opentimes[day];
				int closingtime = closingtimes[day];
				
				int from = openingtime;
			    int to = closingtime;
			    Date date = new Date();
			    Calendar c = Calendar.getInstance();
			    c.setTime(date);
			    int t = c.get(Calendar.HOUR_OF_DAY) * 100 + c.get(Calendar.MINUTE);
			    boolean isBetween = to > from && t >= from && t <= to || to < from && (t >= from || t <= to);
				
				return isBetween;
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
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
<<<<<<< HEAD
		return oncampus;
=======
		return true;
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
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
<<<<<<< HEAD
			return -1;
=======
			return 0;
>>>>>>> d3dc807ecf407bcd5d4fd93173e92c47985fb76b
	}
}