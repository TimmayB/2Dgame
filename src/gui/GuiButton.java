package gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class GuiButton {
	private double x, y;
	private int w, h;
	private String state, name;
	private Texture normalTex;
	private Texture hoverOverTex;
	private Color color;
	private boolean isMouseOver;
	private boolean isPressed;

	public GuiButton(double x, double y, int w, int h, String state, String name, Texture normalTex,
			Texture hoverOverTex, Color color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.state = state;
		this.name = name;
		this.normalTex = normalTex;
		this.hoverOverTex = hoverOverTex;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public boolean isMouseOver() {
		return isMouseOver;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state == "start" || state == "game" || state == "win" || state == "lose") {
			// Must update if states are added!
			this.state = state;
		} else {
			System.out.println("Invalid state");
		}
	}

	public void setNormalTexture(Texture tex) {
		this.normalTex = tex;
	}

	public void setHoverOverTexture(Texture tex) {
		this.hoverOverTex = tex;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void onUpdate() {
		int mX = Mouse.getX();
		int mY = Display.getHeight() - Mouse.getY();

		if (mX >= x && mX <= x + w && mY >= y && mY <= y + h) {
			isMouseOver = true;
			while (Mouse.next()) {
				if (Mouse.getEventButtonState()) {
					if (Mouse.isButtonDown(0)) {
						isPressed = true;
					} else {
						isPressed = false;
					}
				}
			}
		} else {
			isMouseOver = false;
			isPressed = false;
		}
		draw();
	}

	public void draw() {
		color.bind();
		if (isMouseOver) {
			hoverOverTex.bind();
		} else {
			normalTex.bind();
		}
		glBegin(GL_QUADS);
		glTexCoord2d(0, 1);
		glVertex2d(x, y);
		glTexCoord2d(1, 1);
		glVertex2d(x + w, y);
		glTexCoord2d(1, 0);
		glVertex2d(x + w, y + h);
		glTexCoord2d(0, 0);
		glVertex2d(x, y + h);
		glEnd();
		Color.white.bind();
	}
}
