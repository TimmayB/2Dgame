package gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import states.StateManager;

public class Gui {

	public static ArrayList<GuiText> textArray = new ArrayList<>();
	public static ArrayList<GuiButton> buttonArray = new ArrayList<>();

	public static class text extends GuiText {
		public text(String text, double x, double y, double size, String state, String name, Color textColor) {
			super(text, x, y, size, state, name, textColor);
		}
	}

	public static class button extends GuiButton {
		/**
		 * @param x
		 *            the X-coordinate of the button.
		 * @param y
		 *            the Y-coordinate of the button.
		 * @param width
		 *            the width of the button.
		 * @param height
		 *            the height of the button.
		 * @param state
		 *            the state the button will appear in.
		 * @param name
		 *            This is the name the button will be referenced by.
		 * @param normalTex
		 *            the texture that will be displayed on the button.
		 * @param hoverOverTex
		 *            the texture that will be displayed when the mouse is
		 *            hovering over the button.
		 * @param color
		 *            the color the button will be.
		 * */
		public button(double x, double y, int width, int height, String state, String name, Texture normalTex,
				Texture hoverOverTex, Color color) {
			super(x, y, width, height, state, name, normalTex, hoverOverTex, color);
		}
	}

	public static text addNewTextBox(text t) {
		textArray.add(t);
		return t;
	}

	public static button addNewButton(button b) {
		buttonArray.add(b);
		return b;
	}

	public static void onUpdate() {
		for (GuiText text : textArray) {
			if (text.getState().toLowerCase().equals(StateManager.currentState().toLowerCase())) {
				text.drawBoxWithText();
			}
		}

		for (GuiButton button : buttonArray) {
			if (button.getState().toLowerCase().equals(StateManager.currentState().toLowerCase())) {
				button.onUpdate();
			}
		}
	}

	public static GuiText getTextByName(String name) {
		for (GuiText text : textArray) {
			if (text.getName().toLowerCase().equals(name.toLowerCase()))
				return text;
		}
		return null;
	}

	public static GuiButton getButtonByName(String name) {
		for (GuiButton button : buttonArray) {
			if (button.getName().toLowerCase().equals(name.toLowerCase()))
				return button;
		}
		return null;
	}
}
