package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import de.daemoniac.autosueberfahrendich.*;

import javax.swing.*;
import java.io.File;
import java.util.Date;

public class Menue implements Screen {
    SpriteBatch batch;
    SpriteBatch HintergrundBatch;
    public BitmapFont font;

    Texture Ende;
    OrthographicCamera Camera;
    Texture Hintergrund;
    starter Hauptspiel;
    public BitmapFont anzeige;
    public BitmapFont anzeige2;
    long menuedelay;
    public String f;
    Rectangle Playknopf;
    Rectangle Lebenknopf;
    Rectangle EinstellungenKnopf;
    Rectangle Kontaktknopf;

    public Menue(starter pHauptspiel){

        menuedelay = TimeUtils.millis();
        Hauptspiel=pHauptspiel;
        Camera=new OrthographicCamera();
        Camera.setToOrtho(false,3910,2270);
        Hintergrund=new Texture("Texturen/Hupt-Menü.png");
        HintergrundBatch=new SpriteBatch();

        Playknopf = new Rectangle();
        Playknopf.x = 1506;
        Playknopf.width = 2449-1506;
        Playknopf.y = 0;
        Lebenknopf = new Rectangle();
        Lebenknopf.x = 3099;
        Lebenknopf.width = 3270-3099;
        Lebenknopf.y = 1984;
        Lebenknopf.height = 2177-1984;
        EinstellungenKnopf=new Rectangle();
        EinstellungenKnopf.x=3217;
        EinstellungenKnopf.y=1611;
        EinstellungenKnopf.width=3464-3217;
        EinstellungenKnopf.height=1861-1611;
        Kontaktknopf = new Rectangle();
        Kontaktknopf.x = 2936;
        Kontaktknopf.width = 3138-2936;
        Kontaktknopf.y = 1626;
        Kontaktknopf.height = 1861-1626;
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
        HintergrundBatch.setProjectionMatrix(Camera.combined);
        HintergrundBatch.begin();
        HintergrundBatch.draw(Hintergrund, 0, 0, Hintergrund.getWidth(), Hintergrund.getHeight());
        String Münzen;
        String Leben;
        String RGB;

        Vector3 debugkoords = null;
        if (Gdx.input.isTouched()) {
            debugkoords = new Vector3();
            debugkoords.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Camera.unproject(debugkoords);
            //anzeige.draw(HintergrundBatch, debugkoords.toString(), 290, 260);
        }
        Münzen = String.format("%,d", Hauptspiel.spielstand.muenzen);
        Leben = String.format("%,d", Hauptspiel.spielstand.leben);
        RGB = String.format("%,d", Hauptspiel.spielstand.RGBmuenzen);


        anzeige.draw(HintergrundBatch, Münzen, 290, 440);
        anzeige.draw(HintergrundBatch, RGB, 290, 300);
        anzeige2.draw(HintergrundBatch, Leben, 3650, 2150);
        HintergrundBatch.end();
        if (TimeUtils.millis() - menuedelay > 500) {
            if (Gdx.input.isTouched()) {
                Vector3 KNOPFWURDEBERÜRT = new Vector3();
                KNOPFWURDEBERÜRT.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                Camera.unproject(KNOPFWURDEBERÜRT);
                if (KNOPFWURDEBERÜRT.x > Playknopf.x && KNOPFWURDEBERÜRT.x < Playknopf.x+Playknopf.width) {//Der Play-Knopf
                    if (Hauptspiel.spielstand.leben > 0) {
                        switch (Hauptspiel.spielstand.level) {
                            case 1:
                                Hauptspiel.setScreen(new Level1(Hauptspiel));
                                Sounds.klick(Hauptspiel.settings_datei);
                                break;
                            case 2:
                                Hauptspiel.setScreen(new Level2(Hauptspiel));
                                Sounds.klick(Hauptspiel.settings_datei);
                                break;
                            case 3:
                                Hauptspiel.setScreen(new Level3(Hauptspiel));
                                Sounds.klick(Hauptspiel.settings_datei);
                                break;
                            case 4:
                                Hauptspiel.setScreen(new Level4(Hauptspiel));
                                Sounds.klick(Hauptspiel.settings_datei);
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

                if ((KNOPFWURDEBERÜRT.x > Lebenknopf.x) && (KNOPFWURDEBERÜRT.x < Lebenknopf.x+Lebenknopf.width )&& (KNOPFWURDEBERÜRT.y > Lebenknopf.y) && (KNOPFWURDEBERÜRT.y < Lebenknopf.y+Lebenknopf.height)){
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
                if ((KNOPFWURDEBERÜRT.x > EinstellungenKnopf.x) && (KNOPFWURDEBERÜRT.x < EinstellungenKnopf.x+EinstellungenKnopf.width )&& (KNOPFWURDEBERÜRT.y > EinstellungenKnopf.y) && (KNOPFWURDEBERÜRT.y < EinstellungenKnopf.y+EinstellungenKnopf.height)){
                    Hauptspiel.setScreen(new Einstellungen(Hauptspiel));
                }
                if ((KNOPFWURDEBERÜRT.x > Kontaktknopf.x) && (KNOPFWURDEBERÜRT.x < Kontaktknopf.x+Kontaktknopf.width )&& (KNOPFWURDEBERÜRT.y > Kontaktknopf.y) && (KNOPFWURDEBERÜRT.y < Kontaktknopf.y+Kontaktknopf.height)){
                    Hauptspiel.setScreen(new Kontakt(Hauptspiel));
                }
            }//Touch bereich ende
        }
        //Überprüfung ob neue Leben dazugewonnen werden
        if(Hauptspiel.spielstand.leben<5){
            long interval = new Date().getTime() - Hauptspiel.spielstand.lebenszeit.getTime();
            if(interval>150*60*1000){
                Hauptspiel.spielstand.lebenszeit=new Date();
                Hauptspiel.spielstand.leben+=5;
                if(Hauptspiel.spielstand.leben>5){
                    Hauptspiel.spielstand.leben=5;
                }
                Hauptspiel.spielstand.Speichern();
            }else if(interval>120*60*1000){
                Hauptspiel.spielstand.lebenszeit=new Date();
                Hauptspiel.spielstand.leben+=4;
                if(Hauptspiel.spielstand.leben>5){
                    Hauptspiel.spielstand.leben=5;
                }
                Hauptspiel.spielstand.Speichern();
            }else if(interval>90*60*1000){
                Hauptspiel.spielstand.lebenszeit=new Date();
                Hauptspiel.spielstand.leben+=3;
                if(Hauptspiel.spielstand.leben>5){
                    Hauptspiel.spielstand.leben=5;
                }
                Hauptspiel.spielstand.Speichern();
            }else if(interval>60*60*1000){
                Hauptspiel.spielstand.lebenszeit=new Date();
                Hauptspiel.spielstand.leben+=2;
                if(Hauptspiel.spielstand.leben>5){
                    Hauptspiel.spielstand.leben=5;
                }
                Hauptspiel.spielstand.Speichern();
            }else if(interval>30*60*1000){
                Hauptspiel.spielstand.lebenszeit=new Date();
                Hauptspiel.spielstand.leben+=1;
                if(Hauptspiel.spielstand.leben>5){
                    Hauptspiel.spielstand.leben=5;
                }
                Hauptspiel.spielstand.Speichern();
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


