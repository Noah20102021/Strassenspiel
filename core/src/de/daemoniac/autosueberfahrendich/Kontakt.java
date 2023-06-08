package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Kontakt implements Screen {
    Texture Hintergrund;
    starter Hauptspiel;
    long menuedelay;
    Batch Gafik;
    public BitmapFont font;
    OrthographicCamera Camera;

    public Kontakt(starter pHauptspiel) {
        Hauptspiel = pHauptspiel;
        menuedelay = TimeUtils.millis();
        Gafik=new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(6);
        Camera=new OrthographicCamera();
        Camera.setToOrtho(false,3910,2270);
        Hintergrund=new Texture("Texturen/kontaktfenster.png");
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
            debugkoords = new Vector3();
            debugkoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(debugkoords);
            font.draw(Gafik, debugkoords.toString(), 290, 260);
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
