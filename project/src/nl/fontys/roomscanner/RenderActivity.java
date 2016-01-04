package nl.fontys.roomscanner;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class RenderActivity extends Activity {

		private GLSurfaceView mGLView;
		private ArrayList<String> data;
		private boolean occupied=false;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			data=getData();
			if(data.get(0).contains("free")) {
				occupied=true;
			}
			mGLView = new RoomScannerSurfaceView(this,occupied);
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
