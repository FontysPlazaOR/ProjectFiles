package nl.fontys.roomscanner;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

/**
 * Element that should be displayed if the room is occupied
 */
public class ElementOccupied {
	private final String vertexShaderCode = "attribute vec4 vPosition;" + "void main() {" + "  gl_Position = vPosition;"
			+ "}";

	private final String fragmentShaderCode = "precision mediump float;" + "uniform vec4 vColor;" + "void main() {"
			+ "  gl_FragColor = vColor;" + "}";

	private final int mProgram;

	private FloatBuffer vertexBuffer;

	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;

	static float bgTriangleCoords[] = { // in counterclockwise order:
			0.0f, 0.6f, 0.0f, // top
			-0.5f, -0.3f, 0.0f, // bottom left
			0.5f, -0.3f, 0.0f // bottom right
	};
	static float fgTriangleCoords[] = { // in counterclockwise order:
			0.0f, 0.5f, 0.0f, // top
			-0.6f, -0.4f, 0.0f, // bottom left
			0.6f, -0.4f, 0.0f // bottom right
	};

	// Set color with red, green, blue and alpha (opacity) values
	float redColor[] = { 1f, 0f, 0f, 1.0f };
	float blackColor[] = { 0f, 0f, 0f, 1.0f };
	
	private int mPositionHandle;
	private int mColorHandle;

	private final int vertexCount;
	private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per

	private boolean small;
															// vertex

	public ElementOccupied(boolean small) {
		this.small = small;
		vertexCount = this.small ? fgTriangleCoords.length : bgTriangleCoords.length / COORDS_PER_VERTEX;

		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
				// (number of coordinate values * 4 bytes per float)
				this.small ? fgTriangleCoords.length : bgTriangleCoords.length * 4);
		// use the device hardware's native byte order
		bb.order(ByteOrder.nativeOrder());

		// create a floating point buffer from the ByteBuffer
		vertexBuffer = bb.asFloatBuffer();
		// add the coordinates to the FloatBuffer
		vertexBuffer.put(small ? fgTriangleCoords : bgTriangleCoords);
		// set the buffer to read the first coordinate
		vertexBuffer.position(0);

		int vertexShader = RoomScannerRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		int fragmentShader = RoomScannerRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

		// create empty OpenGL ES Program
		mProgram = GLES20.glCreateProgram();

		// add the vertex shader to program
		GLES20.glAttachShader(mProgram, vertexShader);

		// add the fragment shader to program
		GLES20.glAttachShader(mProgram, fragmentShader);

		// creates OpenGL ES program executables
		GLES20.glLinkProgram(mProgram);
	}

	public void draw() {
		// Add program to OpenGL ES environment
		GLES20.glUseProgram(mProgram);

		// get handle to vertex shader's vPosition member
		mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);

		// Prepare the triangle coordinate data
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride,
				vertexBuffer);

		// get handle to fragment shader's vColor member
		mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

		// Set color for drawing the triangle
		GLES20.glUniform4fv(mColorHandle, 1, small ? blackColor : redColor, 0);

		// Draw the triangle
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}
