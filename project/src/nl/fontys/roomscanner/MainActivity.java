package nl.fontys.roomscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

		//QR Scanner to scan for us, the default scanner in the vuzix
		private final static String SCANNER_APP="com.google.zxing.client.android.SCAN";
		//name for the extra data for next intent
		public final static String TIMETABLE_DATA="timetableData";
		
		private final static String SCAN_R_F="SCAN_RESULT_FORMAT";
		
		private final static String SCAN_R="SCAN_RESULT";
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        HandleClick hc = new HandleClick();
	        findViewById(R.id.butQR).setOnClickListener(hc);
	        scan();
		}
		
		/**
		 * what happens when the button is clicked
		 * 
		 */
	    private class HandleClick implements OnClickListener{
	    	public void onClick(View arg0) {
	    		scan();
	    	}
	    }

	    /**
	     * starts the internal App 'Scanner'
	     */
	    public void scan() {
	      	Intent intent = new Intent(SCANNER_APP);         
	        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		    startActivityForResult(intent, 0);		
	    }
	  /*  
	    /**
	     * process scan results
	     */
	    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	   	 Button butQR = (Button )findViewById(R.id.butQR);
	    	if (requestCode == 0) {
	    
	           //   TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
	     
	           // TextView tvResult=(TextView)findViewById(R.id.tvResult);
	     
	            if (resultCode == RESULT_OK) {
	       
	            //	tvStatus.setText(intent.getStringExtra(SCAN_R_F));
	            	String scanResult = intent.getStringExtra(SCAN_R);
	            	//butQR.setText(scanResult);
	            	showTimeTableData(scanResult);
	            	
	         
	            } else if (resultCode == RESULT_CANCELED) {
	           /*
	            	tvStatus.setText("Press a button to start a scan.");
	                tvResult.setText("Scan cancelled.");
	            */
	            	butQR.setText("canceled");
	            	scan();
	            }
	        }
	        
	    }

	    /**
	     * start a new activity with that displays the timetable data
	     * @param scanResult room number, result of QRcode scan
	     */
		private void showTimeTableData(String scanResult) {
			TimeTableReader ttReader = new  TimeTableReader();
			ArrayList<String> data = ttReader.readTimeTableDataForRoom(scanResult);
			if(!data.isEmpty()) {
			startRenderActivity(data);
			}
			else {
				scan();
			}
		}
		/**
		 * start new activity
		 * @param data from timetable reader
		 */
		private void startRenderActivity(ArrayList<String> data) {
			Intent intent = new Intent(this, RenderActivity.class);
			intent.putStringArrayListExtra(TIMETABLE_DATA, data);
			startActivity(intent);
		}
	}
