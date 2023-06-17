package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import org.w3c.dom.css.Rect;

public class Kontakt implements Screen {
    Texture Hintergrund;
    starter Hauptspiel;
    long menuedelay;
    Batch Gafik;
    public BitmapFont font;
    OrthographicCamera Camera;
    Rectangle Zurück;
    Rectangle webseitenlink;
    Rectangle Discordlink;


    public Kontakt(starter pHauptspiel) {
        Hauptspiel = pHauptspiel;
        menuedelay = TimeUtils.millis();
        Gafik=new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(6);
        Camera=new OrthographicCamera();
        Camera.setToOrtho(false,3910,2270);
        Hintergrund=new Texture("Texturen/kontaktfenster.png");

        Zurück=new Rectangle();
        Zurück.x=3205;
        Zurück.y=48;
        Zurück.width=3842-3205;
        Zurück.height=285-48;

        webseitenlink=new Rectangle();
        webseitenlink.x=1832;
        webseitenlink.y=1832;
        webseitenlink.width=2136-1832;
        webseitenlink.height=2132-1832;
        Discordlink=new Rectangle();
        Discordlink.x=1735;
        Discordlink.y=161;
        Discordlink.width=2219-1735;
        Discordlink.height=508-161;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        Gafik.setProjectionMatrix(Camera.combined);
        Gafik.begin();
        Gafik.draw(Hintergrund, 0, 0, Hintergrund.getWidth(), Hintergrund.getHeight());
        Vector3 debugkoords = null;
        if (Gdx.input.isTouched()) {
            Vector3 Berührungspunkt = null;
            Berührungspunkt = new Vector3();
            Berührungspunkt.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(Berührungspunkt);

            debugkoords = new Vector3();
            debugkoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(debugkoords);
            //font.draw(Gafik, debugkoords.toString(), 290, 260);

            if (TimeUtils.millis() - menuedelay > 500) {
                if ((Berührungspunkt.x > Zurück.x) && (Berührungspunkt.x < Zurück.x + Zurück.width) && (Berührungspunkt.y > Zurück.y) && (Berührungspunkt.y < Zurück.y + Zurück.height)) {
                    Sounds.klick(Hauptspiel.settings_datei);
                    Hauptspiel.setScreen(new Menue(Hauptspiel));
                    menuedelay = TimeUtils.millis();
                }
                if ((Berührungspunkt.x > webseitenlink.x) && (Berührungspunkt.x < webseitenlink.x + webseitenlink.width) && (Berührungspunkt.y > webseitenlink.y) && (Berührungspunkt.y < webseitenlink.y + webseitenlink.height)) {
                    Sounds.klick(Hauptspiel.settings_datei);
                    menuedelay = TimeUtils.millis();
                    Gdx.net.openURI("https://www.redstorkgames.4lima.de/");
                }
                if ((Berührungspunkt.x > Discordlink.x) && (Berührungspunkt.x < Discordlink.x + Discordlink.width) && (Berührungspunkt.y > Discordlink.y) && (Berührungspunkt.y < Discordlink.y + Discordlink.height)) {
                    Sounds.klick(Hauptspiel.settings_datei);
                    menuedelay = TimeUtils.millis();
                    Gdx.net.openURI("https://discord.gg/RdJzZMyvqz");
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
