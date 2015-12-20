package nl.fontys.plazza;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;


/**
 * the Activity that displays the 3d stuff using the PlazzaSurfaceView
 *
 */
public class RenderActivity extends Activity {

	private GLSurfaceView mGLView;
	private ArrayList<String> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		data=getData();
		mGLView = new PlazzaSurfaceView(this);
        setContentView(mGLView);
	}

	/**
	 * gets the data that was send with the intent
	 * @return
	 */
    private ArrayList<String> getData() {
    	Intent intent = getIntent();
    	return intent.getStringArrayListExtra(MainActivity.TIMETABLE_DATA);
    	
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
