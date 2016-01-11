package nl.fontys.roomscanner;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Element that should be displayed if the room is occupied
 */
public class ElementOccupied {

	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;

	static float doorFrameCoords[] = { -0.6f, 0.8f, 0.0f, // top left
			-0.6f, -0.6f, 0.0f, // bottom left
			0.6f, -0.6f, 0.0f, // bottom right
			0.6f, 0.8f, 0.0f }; // top right
	static float doorCoords[] = { -0.5f, 0.7f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.5f, -0.5f, 0.0f, // bottom right
			0.5f, 0.7f, 0.0f }; // top right
	static float triangleCoords[] = {
			// in counterclockwise order:
			0.0f, 0.422008459f, 0.0f, // top
			-0.3f, -0.111004243f, 0.0f, // bottom left
			0.3f, -0.111004243f, 0.0f // bottom right
	};
	float colorFrame[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
	float colorDoor[] = { 1f, 1f, 0f, 1.0f };
	float colorTriangle[] = { 1f, 0f, 0f, 1.0f };

	private final ShortBuffer drawListBuffer;
	private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw

	private final FloatBuffer vertexBuffer;
	private final FloatBuffer vertexBuffer2;
	private final FloatBuffer vertexBuffer3;

	/**
	 * Sets up the drawing object data for use in an OpenGL ES context.
	 */
	public ElementOccupied() {

		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				doorFrameCoords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(doorFrameCoords);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
				// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 2);
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);
		// 2

		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb2 = ByteBuffer.allocateDirect(
				// (# of coordinate values * 4 bytes per float)
				doorCoords.length * 4);
		bb2.order(ByteOrder.nativeOrder());
		vertexBuffer2 = bb2.asFloatBuffer();
		vertexBuffer2.put(doorCoords);
		vertexBuffer2.position(0);

		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb3 = ByteBuffer.allocateDirect(
				// (number of coordinate values * 4 bytes per float)
				triangleCoords.length * 4);
		// use the device hardware's native byte order
		bb3.order(ByteOrder.nativeOrder());

		// create a floating point buffer from the ByteBuffer
		vertexBuffer3 = bb3.asFloatBuffer();
		// add the coordinates to the FloatBuffer
		vertexBuffer3.put(triangleCoords);
		// set the buffer to read the first coordinate
		vertexBuffer3.position(0);
	}

	public void draw(GL10 gl) {

		drawSquare(gl, 1);
		drawSquare(gl, 2);

		drawTriangle(gl);

	}

	public void drawSquare(GL10 gl, int nr) {
		// Since this shape uses vertex arrays, enable them
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// draw the shape
		if (nr == 1) {
			gl.glColor4f( // set color
					colorFrame[0], colorFrame[1], colorFrame[2], colorFrame[3]);

			gl.glVertexPointer( // point to vertex data:
					COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);

		} else {
			gl.glColor4f( // set color
					colorDoor[0], colorDoor[1], colorDoor[2], colorDoor[3]);
			gl.glVertexPointer( // point to vertex data:
					COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer2);
		}

		gl.glDrawElements( // draw shape:
				GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array drawing to avoid
		// conflicts with shapes that don't use it
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	public void drawTriangle(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glColor4f( // set color:
				colorTriangle[0], colorTriangle[1], colorTriangle[2], colorTriangle[3]);
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer3);
		gl.glDrawArrays( // draw shape:
				GL10.GL_TRIANGLES, 0, triangleCoords.length / COORDS_PER_VERTEX);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
