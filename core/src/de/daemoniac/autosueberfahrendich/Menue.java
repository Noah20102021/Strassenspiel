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

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.setProjectionMatrix(Camera.combined);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.begin();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.draw(HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss,0,0,HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getWidth(),HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getHeight());
        String Münzen;
        String Leben;
        String RGB;

        Vector3 debugkoords = null;
        if (Gdx.input.isTouched()) {
            debugkoords = new Vector3();
            debugkoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(debugkoords);
            //anzeige.draw(ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen, debugkoords.toString(), 290, 260);
        }
        Münzen = String.format("%,d", Hauptspiel.spielstand.muenzen);
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
                if (NINUNINUNINUKNOPFWURDEBERÜRT.x > Playknopf.x && NINUNINUNINUKNOPFWURDEBERÜRT.x < Playknopf.x+Playknopf.width) {//Der Play-Knopf
                    if (Hauptspiel.spielstand.leben > 0) {
                        switch (Hauptspiel.spielstand.level) {
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
                    } else {
                        //TODO::Meldung bringen dass keine Leben vorhanden sind
                    }//end if leben testen

                }//end if play-knopf-gedrückt

                if ((NINUNINUNINUKNOPFWURDEBERÜRT.x > Lebenknopf.x) && (NINUNINUNINUKNOPFWURDEBERÜRT.x < Lebenknopf.x+Lebenknopf.width )&& (NINUNINUNINUKNOPFWURDEBERÜRT.y > Lebenknopf.y) && (NINUNINUNINUKNOPFWURDEBERÜRT.y < Lebenknopf.y+Lebenknopf.height)){
                if (Hauptspiel.spielstand.leben < 5){
                    if (Hauptspiel.spielstand.muenzen >= 5000){
                        //TODO: Abfrage erstellen ob man wirklich 5000 Münzen ausgeben möchte
                        Hauptspiel.spielstand.muenzen -= 5000;
                        Hauptspiel.spielstand.leben += 1;
                        Hauptspiel.spielstand.Speichern();
                        menuedelay = TimeUtils.millis();
                    }else{
                        //TODO:Meldung bringen dass nicht genug münzen da sind
                    }
                }else{
                    //TODO:Meldung bringen dass Leben bereits voll sind
                }
                }
            }//Touch bereich ende
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

