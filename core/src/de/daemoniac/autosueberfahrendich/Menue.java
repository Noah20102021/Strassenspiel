package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.io.File;

public class Menue implements Screen {
    SpriteBatch batch;
    SpriteBatch ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen;
    public BitmapFont font;

    Texture Ende;
OrthographicCamera Camera;
Texture HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss;
starter Hauptspiel;
    public BitmapFont anzeige;
long menuedelay;
    public Menue(starter pHauptspiel){

    menuedelay = TimeUtils.millis();
    Hauptspiel=pHauptspiel;
    Camera=new OrthographicCamera();
    Camera.setToOrtho(false,391,227);
    HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss=new Texture("Hupt-Menü.png");
    ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen=new SpriteBatch();
}
    @Override
    public void show() {
        batch = new SpriteBatch(10);
        font = new BitmapFont();
        font.getData().setScale(1);
        anzeige = new BitmapFont();
        anzeige.getData().setScale(0.7F);
        anzeige.setColor(0, 0, 0, 100);
        Hauptspiel.settings_datei.Speichern();
        String Test;
        File file = new File("/Sounds/Klick.wav");
        Test = file.getAbsolutePath();
        System.out.println(Test);
    }

    @Override
    public void render(float delta) {
System.out.println(Hauptspiel.spielstand.münzen);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.setProjectionMatrix(Camera.combined);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.begin();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.draw(HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss,0,0,HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getWidth(),HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getHeight());
        String Münzen;
        Münzen = String.format("%,d", Hauptspiel.spielstand.münzen);
        anzeige.draw(ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen, Münzen, 29, 44);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.end();
        if (TimeUtils.millis()-menuedelay > 500) {
            if (Gdx.input.isTouched()) {
                Vector3 NINUNINUNINUKNOPFWURDEBERÜRT = new Vector3();
                NINUNINUNINUKNOPFWURDEBERÜRT.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                Camera.unproject(NINUNINUNINUKNOPFWURDEBERÜRT);
                if (NINUNINUNINUKNOPFWURDEBERÜRT.x > 140 && NINUNINUNINUKNOPFWURDEBERÜRT.x < 244) {
                    
                    switch (Hauptspiel.spielstand.level){
                        case 1:
                            Hauptspiel.setScreen(new Level1(Hauptspiel));
                            Sounds.klick();
                            break;
                        case 2:
                            Hauptspiel.setScreen(new Level2(Hauptspiel));
                            Sounds.klick();
                            break;
                        case 3:
                            Hauptspiel.setScreen(new Level3(Hauptspiel));
                            Sounds.klick();
                            break;
                        case 4:
                            Hauptspiel.spielstand.level = 1;
                            Hauptspiel.spielstand.Speichern();
                            break;
                    }
                }
            }
        }
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

