package launcher;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import gui.Fonts;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import states.StateManager;
import visuals.Textures;

public class MainLauncher {

	// Game window width and height by pixels
	public static final int WINDOW_SIZE_WIDTH = 720;
	public static final int WINDOW_SIZE_HEIGHT = 450;

	public static boolean endGame = false;
	private static long lastFrame;

	public static void launchGame() {
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT));
			Display.setTitle("Super Awesome 2D Game! (That isn't finished)");
			Display.create();
			Display.setResizable(false);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F); // Sets Background Color
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glViewport(0, 0, WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		Fonts.setUpTextures();
		Textures.loadTextures();

		// Start of the game screen
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			StateManager.onUpdate(getDelta());
			Display.sync(60);
			Display.update();
		}
		endGame = true;
		Display.destroy();
		System.exit(0);
	}

	// This stuff is just so the game loads more smoothly on different machines.
	// Just think of delta as frames per second.
	private static int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static void main(String[] args) {
		launchGame();
	}
}
