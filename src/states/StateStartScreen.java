package states;

import gui.Gui;
import gui.GuiButton;

import org.newdawn.slick.Color;

import visuals.Textures;

public class StateStartScreen {

	static GuiButton StartButton;

	public static void onSetup() {
		// List of Text
		Gui.addNewTextBox(new Gui.text("Start Game", 50, 50, 2, "startScreen", "start", Color.white));

		// List of buttons
		StartButton = Gui.addNewButton(new Gui.button(60, 60, 70, 70, "startScreen", "Start", Textures.shadow,
				Textures.textBackground, Color.white));
	}

	public static void onUpdate() {
		if (StartButton.isPressed()) {
			StateManager.changeState("game");
		}
	}
}
