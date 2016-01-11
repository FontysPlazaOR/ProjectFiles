package nl.fontys.roomscanner;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class RoomScannerSurfaceView extends GLSurfaceView {

		public RoomScannerSurfaceView(Context context,boolean free) {
	        super(context);

			// Set the Renderer for drawing on the GLSurfaceView
			final RoomScannerRenderer mRenderer = new RoomScannerRenderer(free);
			setRenderer(mRenderer);
			
			// Render the view only when there is a change in the drawing data
			setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	       

	    }
	}

