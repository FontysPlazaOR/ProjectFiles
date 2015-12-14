package nl.fontys.plazza;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class PlazzaSurfaceView  extends GLSurfaceView  {
	private final PlazzaRenderer mRenderer;
	private Door mDoor;
    public PlazzaSurfaceView(Context context) {
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new PlazzaRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
