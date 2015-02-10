package inputs;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	static boolean lmbp = false;
	static boolean rmbp = false;

	public static void onUpdate() {
		lmbp = false;
		rmbp = false;
		/*
		 * / Here we basically did a check if in the current loop the left, or
		 * right mouse button is down / if it is down then the variable is set
		 * to true, else the variable is not changed and everything / keeps on
		 * rolling! / / You can see above how the first thing I did was set lmbp
		 * (Left mouse button pressed) and rmbp (right mouse button pressed) /
		 * to be automatically set to false;
		 */
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.isButtonDown(0)) {
					lmbp = true;
				}
				if (Mouse.isButtonDown(1)) {
					rmbp = true;
				}
			}
		}
		/*
		 * / while(Keyboard.next()) method lets us listen for single key
		 * presses, this is very useful as / even though you might think that
		 * you pressed the button only once, holding it in would / still tell
		 * the computer that the button is down. With the while loop, even if
		 * the key is down / it will only be registered as a single press, not
		 * the button being down. /
		 */
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_P)) {
				// Do something if the 'p' key is pressed.
			}
			if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_G)) {
				// Do something if the 'g' key is pressed.
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			// Do something if the right arrow is pressed.
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			// Do something if the left arrow is pressed.
		}
	}
}
