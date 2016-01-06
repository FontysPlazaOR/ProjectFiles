package nl.fontys.roomscanner;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class RoomScannerSurfaceView extends GLSurfaceView {

	private final RoomScannerRenderer mRenderer;

	// data for the room
	public RoomScannerSurfaceView(Context context, boolean free, String roomNumber, String lecturer, String module) {
		super(context);

		// Set the Renderer for drawing on the GLSurfaceView
		mRenderer = new RoomScannerRenderer(free, roomNumber, lecturer, module);
		setRenderer(mRenderer);

		// Render the view only when there is a change in the drawing data
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
