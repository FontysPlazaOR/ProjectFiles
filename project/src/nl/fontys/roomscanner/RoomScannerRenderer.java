package nl.fontys.roomscanner;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

/**
 * Provides drawing instructions for a GLSurfaceView object
 * 
 * @author max
 *
 */
public class RoomScannerRenderer implements GLSurfaceView.Renderer {
	private ElementFree mFree;
	private ElementOccupied mOccupiedBg;
	private boolean free = false;
	private boolean closed = true;

	public RoomScannerRenderer(boolean free) {
		this.free = free;
	}


	@Override
	public void onDrawFrame(GL10 gl) {

		// Draw background color
		gl.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		// Set GL_MODELVIEW transformation mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset the matrix to its default state

		// When using GL_MODELVIEW, you must set the view point
		GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		// Draw element
		if (free) {
			if (closed) {
				mFree.draw(gl, closed);
				closed = false;
			} else {
				mFree.draw(gl, closed);
				closed = true;
			}

		} else

		{
			mOccupiedBg.draw(gl);

		}

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Adjust the viewport based on geometry changes
		// such as screen rotations
		gl.glViewport(0, 0, width, height);

		// make adjustments for screen ratio
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
		gl.glLoadIdentity(); // reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); // apply the projection
													// matrix
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background frame color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		mFree = new ElementFree();
		mOccupiedBg = new ElementOccupied();
	}
}