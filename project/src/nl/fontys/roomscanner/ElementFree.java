package nl.fontys.roomscanner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;
import android.opengl.GLES20;


/**
 * The element that should be displayed when the room is free
 */
public class ElementFree {


	    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;

	static float doorFrameCoords[] = { 
			-0.5f, 0.7f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.5f, -0.5f, 0.0f, // bottom right
			0.5f, 0.7f, 0.0f }; // top right
	static float doorCoords[] = { 
			-0.5f, 0.7f, 0.0f, // top left
			-0.5f, -0.5f, 0.0f, // bottom left
			0.35f, -0.7f, 0.0f, // bottom right
			0.35f, 0.5f, 0.0f }; // top right
	float color2[] = { 1f, 1f, 0f, 1.0f };
	private final ShortBuffer drawListBuffer;
	private final ShortBuffer drawListBuffer2;
	private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw
	private FloatBuffer textureBuffer;	// buffer holding the texture coordinates													// vertices
	// vertices
	private final FloatBuffer vertexBuffer;
	private final FloatBuffer vertexBuffer2;
	private float texture[] = {    		
			// Mapping coordinates for the vertices
			0.0f, 1.0f,		// top left		(V2)
			0.0f, 0.0f,		// bottom left	(V1)
			1.0f, 1.0f,		// top right	(V4)
			1.0f, 0.0f		// bottom right	(V3)
	};
	private int[] textures = new int[1];
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

        // initialize byte buffer for the draw list
        ByteBuffer dlb2 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb2.order(ByteOrder.nativeOrder());
        drawListBuffer2 = dlb2.asShortBuffer();
        drawListBuffer2.put(drawOrder);
        drawListBuffer2.position(0);
    
		
	}
	public void draw(GL10 gl, boolean closed) {
		
		// Since this shape uses vertex arrays, enable them
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// draw the shape
		gl.glColor4f( // set color
				color[0], color[1], color[2], color[3]);
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
				color2[0], color2[1], color2[2], color2[3]);
		if(closed) {
		gl.glVertexPointer( // point to vertex data:
				COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer2);
		}
		else{
			gl.glVertexPointer( // point to vertex data:
					COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);	
		}
		gl.glDrawElements( // draw shape:
				GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer2);

		// Disable vertex array drawing to avoid
		// conflicts with shapes that don't use it
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	/**
	 * Encapsulates the OpenGL ES instructions for drawing this shape.
	 *
	 * @param gl
	 *            - The OpenGL ES context in which to draw this shape.
	 */
	public void draw(GL10 gl) {
		/*
		// Since this shape uses vertex arrays, enable them
				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

				// draw the shape
				gl.glColor4f( // set color
						color[0], color[1], color[2], color[3]);
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
						color2[0], color2[1], color2[2], color2[3]);
				gl.glVertexPointer( // point to vertex data:
						COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer2);

				gl.glDrawElements( // draw shape:
						GL10.GL_TRIANGLES, drawOrder.length, GL10.GL_UNSIGNED_SHORT, drawListBuffer2);

				// Disable vertex array drawing to avoid
				// conflicts with shapes that don't use it
				gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
				*/
	
	}
}
