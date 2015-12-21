package nl.fontys.plazza;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * a class to read the time table data
 */
public class TimeTableReader {

	/**
	 * 
	 * 
	 * gets the timetable data for the room
	 * @param room to get data for
	 * @return data for room
	 */
	public ArrayList<String> readTimeTableDataForRoom(String room) {
		
		ArrayList<String> data = new ArrayList<String>();
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
