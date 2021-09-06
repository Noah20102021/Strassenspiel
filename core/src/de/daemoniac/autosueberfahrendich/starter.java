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


public class starter extends Game {
	public Spielstand spielstand;
	public AdsController adsController;

	public starter(AdsController adsController) {
		this.adsController = adsController;

	}

	public starter() {
		this.adsController = null;

	}
	@Override
	public void create() {
		spielstand=new Spielstand();
		spielstand.Laden();
			this.setScreen(new  Level1(this));

	}
}
