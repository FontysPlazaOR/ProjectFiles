package nl.fontys.roomscanner;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

/*
 * a class to read the time table data
 * currently: only mocking the output
 */
public class TimeTableReader {

	/**
	 * gets the day of the week, Sunday=0
	 * 
	 * @return current day
	 */
	public int getCurrentDayOfWeek() {
		Calendar c = Calendar.getInstance();

		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 2 rooms 1 empty, 1 not
	 * 
	 * gets the timetable data for the room
	 * 
	 * @param room
	 *            to get data for
	 * @return data for room
	 */
	public ArrayList<String> readTimeTableDataForRoom(String room) {
		Log.d(this.getClass().getName(), "reading time table data");

		ArrayList<String> data = new ArrayList<String>();
		data.add(room);
		if (room.compareToIgnoreCase("W1-1.86") == 0) {
			data.add("free");
			data.add("");
			data.add("");
		} else {
			data.add("occupied");
			data.add("GRAP");
			data.add("JAC");
		}
		// TODO impl, arraylist is easy to transfer to other intents, but it can
		// be changed to whatever fits the timetable

		return data;
	}
}
