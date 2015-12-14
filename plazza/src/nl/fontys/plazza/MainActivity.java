package nl.fontys.plazza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author max
 *
 *the main class to start the QR-Code Reader
 */
public class MainActivity extends Activity {
	//QR Scanner to scan for us, the default scanner in the vuzix
	private final static String SCANNER_APP="com.google.zxing.client.android.SCAN";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HandleClick hc = new HandleClick();
        findViewById(R.id.butQR).setOnClickListener(hc);
	}
	
	/**
	 * what happens when the button is clicked
	 * 
	 *
	 */
    private class HandleClick implements OnClickListener{
    	public void onClick(View arg0) {
	    Intent intent = new Intent(SCANNER_APP);         
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	    startActivityForResult(intent, 0);	
    	}
    }

    /**
     * scanning
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.tvResult);
            if (resultCode == RESULT_OK) {
            	tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
            	tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
            	tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }
}
