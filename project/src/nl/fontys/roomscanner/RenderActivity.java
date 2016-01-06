package nl.fontys.roomscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * a class that renders the elements
 * 
 * @author max
 *
 */
public class RenderActivity extends Activity {

	private GLSurfaceView mGLView;

	/**
	 * gets the data that was send with the intent
	 * 
	 * @return
	 */
	private ArrayList<String> getData() {
		Intent intent = getIntent();
		
		return intent.getStringArrayListExtra(MainActivity.TIMETABLE_DATA);
	}

	/**
	 * is the room free?
	 * 
	 * @return
	 */
	private boolean isRoomFree() {
		if (getData().get(1).contains("free")) {
			return true;
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGLView = new RoomScannerSurfaceView(this, isRoomFree());
		setContentView(mGLView);
		TextView text = new TextView(this);
		text.setTextColor(Color.WHITE);
		String textString = getData().get(0)+" is free? "+isRoomFree();
		if(!isRoomFree()) {
			textString+=", "+getData().get(2)+" teached by "+getData().get(3);	
		}
		text.setText(textString);		
		addContentView(text, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
	}

	@Override
	protected void onPause() {
		// The following call pauses the rendering thread.
		// If your OpenGL application is memory intensive,
		// you should consider de-allocating objects that
		// consume significant memory here.
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		// The following call resumes a paused rendering thread.
		// If you de-allocated graphic objects for onPause()
		// this is a good place to re-allocate them.
		super.onResume();
		mGLView.onResume();
	}
}
