package nl.fontys.roomscanner;

import java.util.ArrayList;
import java.util.List;

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
		return getData().get(1).contains("free");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGLView = new RoomScannerSurfaceView(this, isRoomFree());
		setContentView(mGLView); // the picture
		addContentView(createInfosFromIntent(),
				new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // the
																														// text
	}

	public TextView createInfosFromIntent() {
		List<String> data = getData();
		String content = "12:20 - 13:55 |room " + data.get(0) + " is free? " + isRoomFree();
		if (!isRoomFree()) {
			content += " , " + data.get(2) + " teached by " + data.get(3);
		}
		TextView infos = new TextView(this);
		infos.setText(content);
		infos.setTextSize(18);
		infos.setTextColor(Color.WHITE);
		return infos;
	}

	@Override
	protected void onPause() {

		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {

		super.onResume();
		mGLView.onResume();
	}
}
