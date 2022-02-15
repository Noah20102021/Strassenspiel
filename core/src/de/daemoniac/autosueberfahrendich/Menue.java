package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import jdk.javadoc.internal.tool.Start;

public class Menue implements Screen {
    SpriteBatch ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen;
OrthographicCamera Camera;
Texture HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss;
starter Hauptzspiel;
    public Menue(starter pHauptspiel){

    Hauptzspiel=pHauptspiel;
    Camera=new OrthographicCamera();
    Camera.setToOrtho(false,391,227);
    HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss=new Texture("Hupt-Menü.png");
    ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen=new SpriteBatch();
}
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Camera.update();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.setProjectionMatrix(Camera.combined);
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.begin();
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.draw(HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss,0,0,HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getWidth(),HhiinntteerrggrruunnddDdeessHhaauuppttMmeennuuewss.getHeight());
        ChefDerNichtsMachenMussUndAllenSagtWasSieMachenSollen.end();
        if(Gdx.input.isTouched()){
            Vector3 NINUNINUNINUKNOPFWURDEBERÜRT=new Vector3();
            NINUNINUNINUKNOPFWURDEBERÜRT.set(Gdx.input.getX(),Gdx.input.getY(),0);
            Camera.unproject(NINUNINUNINUKNOPFWURDEBERÜRT);
            if (NINUNINUNINUKNOPFWURDEBERÜRT.x>140 && NINUNINUNINUKNOPFWURDEBERÜRT.x<180){
                Hauptzspiel.setScreen(new Level1(Hauptzspiel));
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
