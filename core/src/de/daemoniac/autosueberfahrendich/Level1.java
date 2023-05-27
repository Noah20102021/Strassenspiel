package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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

public class Level1 extends LevelMaster implements Screen {
    long letztesAutoGeneriertu;
    int autoverzoegerungu;
    int autogeschwindigkeit=300;
    public Level1(starter pHauptspiel) {
        Hauptspiel=pHauptspiel;
        autoverzoegerungu=2;

        Hauptspiel=pHauptspiel;
        hintergrund = new Texture("Texturen/strase1.jpg");
        initialisierung();
        letztesAutoGeneriertu = TimeUtils.millis() -4000;
        figur.x = (hintergrund.getWidth() / 2) + 20;
        figur.y = -50;
        figurgeschwindigkeit=200;
        münzgewinn=1000;

        gechafftbildname="Texturen/LVL.1 Geschafft";
        gescheitertbildname="Texturen/Gescheitert.png";
    }

    protected void generiereFahrzeuge(){
        //jetzt, da die graphikkarte informiert wurde was dargestellt werden soll,
        //können wir damit beginnen alles für die nächste darstellungsrunde zu berechnen. dh wir
        //ermitteln ob weitere autos generiert werden, gehen alle bestehenden autos durch und
        //bewegen sie ein stückchen weiter, entfernen autos die schon aus dem bild rausgefahren sind
        // und machen die kollisionsüberprüfung ob die figur ein auto berührt
        if ((TimeUtils.millis() - letztesAutoGeneriertu) / 1000 > autoverzoegerungu) {
            letztesAutoGeneriertu = TimeUtils.millis();
            fahrzeugliste.add(new fahrzeug(0,1080/2 - 110, "_lr",autogeschwindigkeit));
            autoverzoegerungu = MathUtils.random(1, 3);
        }
    }

    @Override
    public void render(float delta) {
        MasterRender();
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

    //dispose wird aufgerufen wenn diese klasse nicht mehr benötigt wird. aktuell passiert das nur wenn das spiel
    //beendet wird. damit der speicher des computers nicht mit altem datenmüll vollläuft sagen wir hier was ebenfalls
    //alles beseitigt werden soll. vor allen dingen große und komplexe objekte sollten hier drin stehen
    @Override
    public void dispose() {
        batch.dispose();
        hintergrund.dispose();
    }
}
