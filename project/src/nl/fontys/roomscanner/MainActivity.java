package nl.fontys.roomscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	// Content of the scanned code
	private final static String SCAN_R = "SCAN_RESULT";
	// Format of the scanned code
	// private final static String SCAN_R_F = "SCAN_RESULT_FORMAT";
	// QR Scanner to scan for us, the default scanner in the Vuzix
	private final static String SCANNER_APP = "com.google.zxing.client.android.SCAN";

	// Name for the extra data for next intent
	public final static String TIMETABLE_DATA = "timetableData";

	/**
	 * what happens when the button is clicked
	 * 
	 */
	private class HandleClick implements OnClickListener {
		public void onClick(View arg0) {
			scan();
		}
	}

	/*
	 * /** process scan results
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == RESULT_OK) {
			String scanResult = intent.getStringExtra(SCAN_R);
			showTimeTableData(scanResult);
		} else if (resultCode == RESULT_CANCELED) {
			scan();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		HandleClick hc = new HandleClick();
		findViewById(R.id.butQR).setOnClickListener(hc);
		scan();
	}

	/**
	 * starts the internal App 'Scanner'
	 */
	public void scan() {
		Intent intent = new Intent(SCANNER_APP);
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	/**
	 * start a new activity with that displays the timetable data
	 * 
	 * @param scanResult
	 *            room number, result of QRcode scan
	 */
	private void showTimeTableData(String scanResult) {
		TimeTableReader ttReader = new TimeTableReader();
		ArrayList<String> data = ttReader.readTimeTableDataForRoom(scanResult);

		if (!data.isEmpty()) {
			startRenderActivity(data);
		} else {
			scan();
		}
	}

	/**
	 * start new activity
	 * 
	 * @param data
	 *            from timetable reader
	 */
	private void startRenderActivity(ArrayList<String> data) {
		Intent intent = new Intent(this, RenderActivity.class);
		intent.putStringArrayListExtra(TIMETABLE_DATA, data);
		startActivity(intent);
	}
}
