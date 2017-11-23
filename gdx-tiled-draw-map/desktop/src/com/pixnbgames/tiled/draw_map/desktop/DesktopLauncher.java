package com.pixnbgames.tiled.draw_map.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixnbgames.tiled.draw_map.MyGdxTiledGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width  = 640;
		config.height = 360;
		new LwjglApplication(new MyGdxTiledGame(), config);
	}
}
