package com.nohowdezign.hw6;

/**
 * @author Noah Howard
 * This class allows for the simplistic manipulation of hours and minutes for checking how long a car has been
 * parked somewhere and also storing when it was parked.
 */
public class Time {
	
	private int hour;							// hour (0 - 23)
	private int minute;							// minute (0 - 59)
	
	public Time ( )								// POST: empty Time object
	{
		hour = 0;
		minute = 0;
	}
	
	public Time (int h, int m)					// PRE: 0 <= h <= 23, 0 <= m <= 59
	{											// POST: Time object set with user hour and minute
		hour = h;
		minute = m;
	}
	
	public Time (String s)						// PRE: hh:mm format, "00" <= "hh" <= "23", "00" <= "mm" <= "59"
	{											// POST: Time object set from string
		String[] timeParts = s.split(":"); // Split at colon to extract parts
		hour = Integer.valueOf(timeParts[0]);
		minute = Integer.valueOf(timeParts[1]);
	}
	
	public int getHour() {						// POST: return hour
		return hour;
	}

	public void setHour(int h) {				// PRE: 0 <= h <= 23
		hour = h;								// POST: set hour
	}
	
	public int getMinute() {					// POST: return minute
		return minute;
	}
	
	public void setMinute(int m) {				// PRE: 0 <= m <= 59
		minute = m;								// POST: set minute
	}
	
	public String toString ( )					// POST: return string in hh:mm format
	{
		return String.format("%d:%02d", hour, minute);
	}
	
	public int subtract (Time other)			// POST: return number of minutes between two times
	{											// compute number of minutes from midnight to time
		int totalMinutesThis = (hour * 60) + minute;
	    int totalMinutesOther = (other.getHour() * 60) + other.getMinute();
	    return totalMinutesOther - totalMinutesThis;
	}
	
	
	
	
	
	
	

}
