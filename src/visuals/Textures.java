package visuals;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/* To add and use textures, add the 2 lines "static Texture <name>;" then add the second line in the try statement.  Just like shown. */
public class Textures {
	public static Texture none, shadow, textBackground;

	// When adding more textures follow this template above.

	public static void loadTextures() {
		try {
			none = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
			textBackground = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("res/textBackground.png"), true);
			shadow = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/shadow.png"), true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
