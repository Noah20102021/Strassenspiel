package de.autosueberfahrendich;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

public class starter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture hintergrund;
	Texture figurbild;
	Rectangle figur;
	int autoverzoegerung=2;
	OrthographicCamera camera;
boolean wurdeEnterGedrueckt=false;
boolean leertastewurdegedrueckt=false;
	Array<fahrzeug> fahrzeugliste;
	long letztesAutoGeneriert;

public BitmapFont font;

	@Override
	public void create () {
		font=new BitmapFont();

		batch = new SpriteBatch();
		hintergrund = new Texture("strase1.jpg");
		figurbild=new Texture("figur.png");
		figur=new Rectangle();
		figur.width=50;
		figur.height=50;
		figur.x=( hintergrund.getWidth()/2)+20;
		figur.y=-50;
        fahrzeugliste=new Array<>();

		//TODO polizei

		camera=new OrthographicCamera();
		camera.setToOrtho(false, hintergrund.getWidth(), hintergrund.getHeight());

letztesAutoGeneriert=TimeUtils.millis();

		//TODO polizei
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			wurdeEnterGedrueckt=true;
		}
        if(wurdeEnterGedrueckt){

        	if(figur.y>=hintergrund.getHeight()/3.5){
				if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
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
