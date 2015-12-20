package nl.fontys.plazza;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;

public class PlazzaSurfaceView  extends GLSurfaceView  {
	private final PlazzaRenderer mRenderer;
	//door to display
	private Door mDoor;
	//data for the room
	private ArrayList<String> data;
    public PlazzaSurfaceView(Context context) {
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new PlazzaRenderer();
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
