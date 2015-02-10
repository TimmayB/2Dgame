package states;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;
import gui.Gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import visuals.Textures;

public class StateManager {

	private static String State = "startScreen";
	private static String changeToState = "";

	static boolean hasFinishedChanging = false;
	static boolean isHalfWayChanging = false;
	static boolean hasStartedChanging = false;

	static boolean isStartScreenSetUp = false;
	static boolean isGameSetUp = false;
	static boolean isWinSetUp = false;
	static boolean isLoseSetUp = false;

	static float opacity = 0F;

	public static void onUpdate(int delta) {
		Gui.onUpdate();
		if (State.equals("startScreen")) {
			if (!isStartScreenSetUp) {
				StateStartScreen.onSetup();
				isStartScreenSetUp = true;
			}
			StateStartScreen.onUpdate();
		} else if (State.equals("game")) {
			if (!isGameSetUp) {
				StateGame.onSetup();
				isGameSetUp = true;
			}
			StateGame.onUpdate(delta);
		} else if (State.equals("win")) {
			if (!isWinSetUp) {
				isWinSetUp = true;
			}

		} else if (State.endsWith("lose")) {
			if (!isLoseSetUp) {
				isLoseSetUp = true;
			}
		} else {
			System.out.println("Invalid state!");
		}
		updateChange();
	}

	/**
	 * Changes the state of the game to the given (pre-written) state. Takes an
	 * input of a valid state. If the input is not recognized, the state is not
	 * changed.
	 */
	public static void changeState(String state) {
		if (!State.equals(state)) {
			changeToState = state;
			hasStartedChanging = true;
			hasFinishedChanging = false;
		}
	}

	private static void updateChange() {
		if (hasStartedChanging) {
			if (opacity >= 1) {
				isHalfWayChanging = true;
				State = changeToState;
			}
			if (opacity <= 0 && isHalfWayChanging) {
				hasStartedChanging = false;
				isHalfWayChanging = false;
				hasFinishedChanging = true;
			}
			if (hasStartedChanging && !isHalfWayChanging) {
				Color.white.bind();
				opacity += 0.01F;
				Textures.none.bind();
				glColor4f(0, 0, 0, opacity);

			}
			if (isHalfWayChanging) {
				opacity -= 0.01F;
				Textures.none.bind();
				glColor4f(0, 0, 0, opacity);
			}

			glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2i(0, 0); // 1
			glTexCoord2d(1, 0);
			glVertex2i(Display.getWidth(), 0); // 2
			glTexCoord2d(1, 1);
			glVertex2i(Display.getWidth(), Display.getHeight()); // 3
			glTexCoord2d(0, 1);
			glVertex2i(0, Display.getHeight()); // 4
			glEnd();
			glColor4f(0, 0, 0, 1F);
		}
	}

	/** Returns the current state of the game */
	public static String currentState() {
		return State;
	}
}
