package nl.fontys.roomscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

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
		return getData().get(1).contains("free");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGLView = new RoomScannerSurfaceView(this, isRoomFree(), getData().get(0), getData().get(3), getData().get(2));
		setContentView(mGLView);
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
