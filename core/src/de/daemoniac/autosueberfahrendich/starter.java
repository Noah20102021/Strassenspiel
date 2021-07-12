package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.ApplicationAdapter;
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


public class starter extends ApplicationAdapter {
	//Das Zeichenobjekt um auf dem Bildschirm etwas zu Zeichnen
	SpriteBatch batch;
	//Die Kamera die auf das 2-Dimensionale Spielfeld blickt
	OrthographicCamera camera;
	//Hintergrundbild
	Texture hintergrund;
	//Die Spielfigur
	Texture figurbild;
	//Der Ort wo die Spielfigur dargestellt wird
	Rectangle figur;
	//Die Veerzögerung in Sekunden bis das erste Auto erscheint
	int autoverzoegerung=2;
	//Variablen für den Spielverlauf
	boolean wurdeEnterGedrueckt=false;
	boolean leertastewurdegedrueckt=false;
	//Die Liste aller Fahrzeuge... sollte bei Gelegenheit auch noch leer gemacht werden
	Array<fahrzeug> fahrzeugliste;
	//wie lang es her ist dass ein Auto gestartet wurde
	long letztesAutoGeneriert;
	//Schriftart zum Darstellen von Text
	public BitmapFont font;

	@Override
	public void create () {
		//Zuerst die Objekte Initialisieren die zur Darstellung des Spielfeldes benutzt werden
		batch = new SpriteBatch();
		font=new BitmapFont();
		hintergrund = new Texture("strase1.jpg");
		camera=new OrthographicCamera();
		camera.setToOrtho(false, hintergrund.getWidth(), hintergrund.getHeight());
		//Jetzt die Spielfigur initialisieren. Dafür das Bild laden und das Rechteck erstellen wo das Bild dann dargestellt werden soll
		figurbild=new Texture("figur.png");
		figur=new Rectangle();
		figur.width=50;
		figur.height=50;
		figur.x=( hintergrund.getWidth()/2)+20;
		figur.y=-50;
		//Leere Fahrzeugliste erstellen und die aktuelle Zeit des Spielstarts abspeichern damit später
		//ermittelt werden kann wieviel Zeit vergangen ist und wann das nächste Auto losfahren soll
        fahrzeugliste=new Array<>();
		letztesAutoGeneriert=TimeUtils.millis();

	}

	@Override
	public void render () {
		//Erst mal Hintergrundfarbe machen damit etwas zu sehen ist falls das Hintergrundbild nicht oder nicht richtig geladen wird.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Kamera aktualisieren
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(hintergrund, 0, 0, hintergrund.getWidth(), hintergrund.getHeight());
        for(fahrzeug auto:fahrzeugliste) {
            batch.draw(auto.autobild,  auto.rect.x,  auto.rect.y,  auto.rect.width / 3,  auto.rect.height / 3);
        }
        batch.draw(figurbild,figur.x,figur.y,figur.width,figur.height);
		//TODO polizei

		font.draw(batch,"Koordinaten: x => "  + Gdx.input.getX() + " Y => " + Gdx.input.getY(), 0,hintergrund.getHeight());
		if(!wurdeEnterGedrueckt) {
			font.draw(batch, "Zum starten drücke Enter", hintergrund.getWidth() / 2, (hintergrund.getHeight() / 2) + 100);
		}
		batch.end();

        if((TimeUtils.millis()-letztesAutoGeneriert)/1000>autoverzoegerung){
            letztesAutoGeneriert=TimeUtils.millis();
            fahrzeugliste.add(new fahrzeug());
            autoverzoegerung= MathUtils.random(1,10);
        }

        for(fahrzeug auto:fahrzeugliste){
            auto.rect.x+=300*Gdx.graphics.getDeltaTime();
            if(auto.rect.x>hintergrund.getWidth()){

            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isTouched() ){
			wurdeEnterGedrueckt=true;
		}
        if(wurdeEnterGedrueckt){

        	if(figur.y>=hintergrund.getHeight()/3.5){
				if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()){
					leertastewurdegedrueckt=true;
				}
				if(leertastewurdegedrueckt){
					figur.y += 110 * Gdx.graphics.getDeltaTime();
				}
			}
        	if(figur.y<= hintergrund.getHeight()/3.5) {
				figur.y += 75 * Gdx.graphics.getDeltaTime();
			}
		}
        		//auto bewegen

		//TODO polizei
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		hintergrund.dispose();
	}
}
