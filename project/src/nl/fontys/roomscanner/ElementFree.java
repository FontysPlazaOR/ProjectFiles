package nl.fontys.roomscanner;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;


/**
 * The element that should be displayed when the room is free
 */
public class ElementFree {


	private float colorFrame[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
	// number of coordinates per vertex in this array
	private static final int COORDS_PER_VERTEX = 3;

	private static float doorFrameCoords[] = {
			-0.5f, 0.7f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.5f, -0.5f, 0.0f, // bottom right
			0.5f, 0.7f, 0.0f }; // top right
	private static float doorCoords[] = {
			-0.5f, 0.7f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.35f, -0.7f, 0.0f, // bottom right
			0.35f, 0.5f, 0.0f }; // top right
	private float colorDoor[] = { 1f, 1f, 0f, 1.0f };
	private final ShortBuffer drawListBuffer;
	private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw													// vertices
	// vertices
	private final FloatBuffer vertexBuffer;
	private final FloatBuffer vertexBuffer2;
	/**
	 * Sets up the drawing object data for use in an OpenGL ES context.
	 */
	public ElementFree() {
		
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
        //2
		
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb2 = ByteBuffer.allocateDirect(
        // (# of coordinate values * 4 bytes per float)
                doorCoords.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        vertexBuffer2 = bb2.asFloatBuffer();
        vertexBuffer2.put(doorCoords);
        vertexBuffer2.position(0);

		
	}
	public void draw(GL10 gl, boolean closed) {
		
		// Since this shape uses vertex arrays, enable them
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// draw the shape
		gl.glColor4f( // set color
				colorFrame[0], colorFrame[1], colorFrame[2], colorFrame[3]);
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glDrawElements( // draw shape:
				GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array drawing to avoid
		// conflicts with shapes that don't use it
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// Since this shape uses vertex arrays, enable them
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// draw the shape
		gl.glColor4f( // set color
				colorDoor[0], colorDoor[1], colorDoor[2], colorDoor[3]);
		if(closed) { //overlayed when closed
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer2); 
		}
		else{
			gl.glVertexPointer( // point to vertex data:
					COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);	
		}
		gl.glDrawElements( // draw shape:
				GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array drawing to avoid
		// conflicts with shapes that don't use it
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
