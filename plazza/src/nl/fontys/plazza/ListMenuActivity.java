package nl.fontys.plazza;

import android.app.ListActivity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * class that temp? replaces the QR-Code reader
 * @author max
 *
 */
public class ListMenuActivity extends ListActivity {
	private TextView text;
	private List<String> listValues;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		text = (TextView) findViewById(R.id.mainText);

		listValues = new ArrayList<String>();
		listValues.add("W1.104A");
		listValues.add("W1.187");
		listValues.add("W1.186");
		listValues.add("Aula");
		listValues.add("W1.081");

		// initiate the listadapter
		ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(this, 
				R.layout.row_layout, R.id.listText, listValues);
 
         // assign the list adapter
         setListAdapter(myAdapter);
         
	}
	
	// when an item of the list is clicked
	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
   
		String result = (String) getListView().getItemAtPosition(position);
		
		showTimeTableData(result);
		//text.setText("You clicked " + selectedItem + " at position " + position); 
	}
    /**
     * start a new activity with that displays the timetable data
     * @param scanResult room number, result of QRcode scan
     */
	private void showTimeTableData(String result) {
		TimeTableReader ttReader = new  TimeTableReader();
		ArrayList<String> data = ttReader.readTimeTableDataForRoom(result);
		Intent intent = new Intent(this, RenderActivity.class);
		intent.putStringArrayListExtra(MainActivity.TIMETABLE_DATA, data);
		startActivity(intent);
	}
}

