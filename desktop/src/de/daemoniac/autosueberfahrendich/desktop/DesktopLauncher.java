package de.daemoniac.autosueberfahrendich.desktop;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.daemoniac.autosueberfahrendich.starter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Strasenspiel");
		new Lwjgl3Application(new starter(), config);
	}
}
