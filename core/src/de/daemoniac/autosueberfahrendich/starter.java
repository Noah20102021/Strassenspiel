package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import de.daemoniac.autosueberfahrendich.werbung.AdsController;


//dass hier ist die zentrale klasse des spieles. beim start des spieles wird, je nach betriebssystem, der entsprechende launcher
// aufgerufen (androidlauncher, desktoplauncher, usw usw). egal welcher launcher genutzt wurde um das spiel zu starten, alle pfade führen erst
//einmal zu dieser klasse. das ist also der erste plattformunabhängige teil des spieles.
public class starter extends Game {
	public Spielstand spielstand;
	private starter Hauptspiel;
	public settings_datei settings_datei;
	public AdsController adsController;

	//wenn von einem launcher aus aufgerufen wurde der die werbeplattform google unterstützt, dann wird diese funktion aufgerufen um den zugriff
	//auf die werbeplattform zu gewährleisten
	public starter(AdsController adsController) {
		this.adsController = adsController;
	}

	//wird von einem launcher aus aufgerufen der nicht die google-werbeplattform unterstützt dann wird diese funktion aufgerufen
	public starter() {
		this.adsController = null;

	}


	@Override
	public void create() {
		//Laden des Spielstandes. aktuell steht da nur drin wie viele münzen man hat, das wird aber im laufe der zeit mehr
		spielstand=new Spielstand();
		spielstand.Laden();
		settings_datei= new settings_datei();
		settings_datei.Laden();
		//setzen des ersten spielbildschirmes. das kann dann später ausgetauscht werden gegen das spielstartmenü oder evtl einem ladebildschirm o.ä.
		this.setScreen(new Menue(this));

	}
}
