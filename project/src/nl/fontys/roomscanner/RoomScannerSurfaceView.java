package nl.fontys.roomscanner;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class RoomScannerSurfaceView extends GLSurfaceView {

		// data for the room
	public RoomScannerSurfaceView(Context context, boolean free, String roomNumber, String lecturer, String module) {
		super(context);

		// Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
		
		// Set the Renderer for drawing on the GLSurfaceView
        final RoomScannerRenderer mRenderer = new RoomScannerRenderer(free, roomNumber, lecturer, module);
		setRenderer(mRenderer);

		// Render the view only when there is a change in the drawing data
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
