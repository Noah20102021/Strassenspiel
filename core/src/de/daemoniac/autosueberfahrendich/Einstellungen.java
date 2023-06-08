package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Einstellungen implements Screen {


    Texture Hintergrund;
    starter Hauptspiel;
    Batch Gafik;
    public BitmapFont font;
    public BitmapFont anzeige;
    long menuedelay;

    Rectangle Soundhaken;
    Rectangle Zurück;

    OrthographicCamera Camera;
    public Einstellungen(starter pHauptspiel) {
        menuedelay = TimeUtils.millis();
        Hauptspiel = pHauptspiel;
        Camera=new OrthographicCamera();
        Camera.setToOrtho(false,3910,2270);
        Hintergrund=new Texture("Texturen/Einstellungen.png");
        Gafik = new SpriteBatch(6);
        font = new BitmapFont();
        font.getData().setScale(1);
        anzeige = new BitmapFont();
        anzeige.getData().setScale(14);
        anzeige.setColor(0, 0, 0, 100);

        Soundhaken=new Rectangle();
        Soundhaken.x=230;
        Soundhaken.y=1976;
        Soundhaken.width=397-230;
        Soundhaken.height=2115-1976;
        Zurück=new Rectangle();
        Zurück.x=3227;
        Zurück.y=55;
        Zurück.width=3865-3227;
        Zurück.height=293-55;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        Gafik.setProjectionMatrix(Camera.combined);
        Gafik.begin();
        Gafik.draw(Hintergrund, 0, 0, Hintergrund.getWidth(), Hintergrund.getHeight());
        if (Hauptspiel.settings_datei.sounds==1) {
            anzeige.draw(Gafik,"X", Soundhaken.x, Soundhaken.y+Soundhaken.height);
        }
        Vector3 Berührungspunkt = null;
        if (Gdx.input.isTouched()) {
            Berührungspunkt = new Vector3();
            Berührungspunkt.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(Berührungspunkt);
            //anzeige.draw(Gafik, Berührungspunkt.toString(), 490, 260);
            //anzeige.draw(Gafik, anzeige.getData().getFontFile().name(), 490, 260);
            if (TimeUtils.millis() - menuedelay > 500) {
                if ((Berührungspunkt.x > Soundhaken.x) && (Berührungspunkt.x < Soundhaken.x + Soundhaken.width) && (Berührungspunkt.y > Soundhaken.y) && (Berührungspunkt.y < Soundhaken.y + Soundhaken.height)) {
                    if (Hauptspiel.settings_datei.sounds == 1) {
                        Hauptspiel.settings_datei.sounds = 0;
                    } else {
                        Hauptspiel.settings_datei.sounds = 1;
                    }
                    Sounds.klick(Hauptspiel.settings_datei);
                    menuedelay = TimeUtils.millis();
                    Hauptspiel.settings_datei.Speichern();
                }
                if ((Berührungspunkt.x > Zurück.x) && (Berührungspunkt.x < Zurück.x + Zurück.width) && (Berührungspunkt.y > Zurück.y) && (Berührungspunkt.y < Zurück.y + Zurück.height)) {
                    Hauptspiel.setScreen(new Menue(Hauptspiel));
                    Sounds.klick(Hauptspiel.settings_datei);
                    menuedelay = TimeUtils.millis();
                }
            }
        }
        Gafik.end();
    }
    @Override
    public void show() {

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
