package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import javax.swing.*;
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
    public BitmapFont anzeige2;
long menuedelay;
public String f;
    public Menue(starter pHauptspiel){

    menuedelay = TimeUtils.millis();
    Hauptspiel=pHauptspiel;
    Camera=new OrthographicCamera();
    Camera.setToOrtho(false,3910,2270);
    HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss=new Texture("Hupt-Menü - Kopie.png");
    ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen=new SpriteBatch();
}
    @Override
    public void show() {
        batch = new SpriteBatch(6);
        font = new BitmapFont();
        font.getData().setScale(1);
        anzeige = new BitmapFont();
        anzeige.getData().setScale(6);
        anzeige.setColor(0, 0, 0, 100);
        anzeige2 = new BitmapFont();
        anzeige2.getData().setScale(10);
        anzeige2.setColor(0, 0, 0, 100);
        Hauptspiel.settings_datei.Speichern();
        String Test;
        File file = new File("/Sounds/Klick.wav");
        Test = file.getAbsolutePath();
        System.out.println(Test);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.setProjectionMatrix(Camera.combined);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.begin();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.draw(HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss,0,0,HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getWidth(),HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getHeight());
        String Münzen;
        String Leben;
        String RGB;
        Münzen = String.format("%,d", Hauptspiel.spielstand.münzen);
        Leben = String.format("%,d", Hauptspiel.spielstand.leben);
        RGB = String.format("%,d", Hauptspiel.spielstand.RGBmünzen);
        anzeige.draw(ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen, Münzen, 290, 440);
        anzeige.draw(ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen, RGB, 290, 300);
        anzeige2.draw(ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen, Leben, 3650, 2150);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.end();
        if (TimeUtils.millis()-menuedelay > 500) {
            if (Gdx.input.isTouched()) {
                Vector3 NINUNINUNINUKNOPFWURDEBERÜRT = new Vector3();
                NINUNINUNINUKNOPFWURDEBERÜRT.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                Camera.unproject(NINUNINUNINUKNOPFWURDEBERÜRT);
                if (NINUNINUNINUKNOPFWURDEBERÜRT.x > 1400 && NINUNINUNINUKNOPFWURDEBERÜRT.x < 2440) {
                    
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
                            Hauptspiel.setScreen(new Level4(Hauptspiel));
                            Sounds.klick();
                            break;
                        case 5:
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

