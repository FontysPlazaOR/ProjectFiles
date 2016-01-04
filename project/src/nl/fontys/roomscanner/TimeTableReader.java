package nl.fontys.roomscanner;
import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

/*
 * a class to read the time table data
 */
public class TimeTableReader {

	/**
	 * 2 rooms 1 empty, 1 not
	 * 
	 * gets the timetable data for the room
	 * @param room to get data for
	 * @return data for room
	 */
	public ArrayList<String> readTimeTableDataForRoom(String room) {
		Log.d(this.getClass().getName(), "reasding time table data");

		ArrayList<String> data = new ArrayList<String>();
		if(room.equals("w1.186")) {
			data.add("free");
		}
		else {
			data.add("occupied");
		}
		//TODO impl, arraylist is easy to transfer to other intents, but it can be changed to whatever fits the timetable
	
		
		return data;
	}
	
	/**
	 * gets the day of the week,
	 * Sunday=0
	 * @return current day
	 */
	public int getCurrentDayOfWeek() {
		Calendar c = Calendar.getInstance(); 
		return c.get(Calendar.DAY_OF_WEEK);
	}
}

