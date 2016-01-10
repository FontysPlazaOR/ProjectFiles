package nl.fontys.roomscanner;

import com.vuzix.hardware.GestureSensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class RoomScannerSurfaceView extends GLSurfaceView {

	private final RoomScannerRenderer mRenderer;

	    public RoomScannerSurfaceView(Context context,boolean free) {
	        super(context);

			// Set the Renderer for drawing on the GLSurfaceView
			mRenderer = new RoomScannerRenderer(free,context);
			setRenderer(mRenderer);
			
			// Render the view only when there is a change in the drawing data
			setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	       

	    }
	}