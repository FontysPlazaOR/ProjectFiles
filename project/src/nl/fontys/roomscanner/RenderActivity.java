package nl.fontys.roomscanner;

import java.util.ArrayList;
import java.util.List;

import com.vuzix.hardware.GestureSensor;

import android.R.color;
import android.app.Activity;
import android.content.Context;
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
		return getData().get(1).contains("free");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGLView = new RoomScannerSurfaceView(this,true);
		setContentView(mGLView);
		
		List<String> data = getData();
		String content="room "+data.get(0)+" is free? "+isRoomFree();
		if(!isRoomFree()) {
			content+=" , "+data.get(2)+" teached by "+data.get(3);
		}
		
		TextView infos=new TextView(this);
		infos.setText(content);
		infos.setTextSize(24);
		infos.setTextColor(Color.WHITE);
		addContentView(infos, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

