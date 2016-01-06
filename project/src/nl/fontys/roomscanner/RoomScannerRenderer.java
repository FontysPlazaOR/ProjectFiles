package nl.fontys.roomscanner;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

/**
 * Provides drawing instructions for a GLSurfaceView object
 * 
 * @author max
 *
 */
public class RoomScannerRenderer implements GLSurfaceView.Renderer {

	private float mAngle;
	private ElementFree mFree;

	private ElementOccupied mOccupied;

	private boolean free = false;
	private String roomNumber;
	private String lecturer;
	private String module;

	public RoomScannerRenderer(boolean free, String roomNumber, String lecturer, String module) {
		this.free = free;
		this.roomNumber = roomNumber;
		this.lecturer = lecturer;
		this.module = module;
		
	}

	/**
	 * Returns the rotation angle of the triangle shape (mTriangle).
	 *
	 * @return - A float representing the rotation angle.
	 */
	public float getAngle() {
		return mAngle;
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		// Draw background color
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Set GL_MODELVIEW transformation mode
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset the matrix to its default state

		// When using GL_MODELVIEW, you must set the view point
		GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		// Draw element
		if (free) {
			mFree.draw(gl);
		} else {
			mOccupied.draw(gl);
		}

		// Create a rotation for the triangle

		// Use the following code to generate constant rotation.
		// Leave this code out when using TouchEvents.
		// long time = SystemClock.uptimeMillis() % 4000L;
		// float angle = 0.090f * ((int) time);

		gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f);

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
		mOccupied = new ElementOccupied();
	}

	/**
	 * Sets the rotation angle of the triangle shape (mTriangle).
	 */
	public void setAngle(float angle) {
		mAngle = angle;
	}
}