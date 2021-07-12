package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class fahrzeug {
    public Texture autobild;
    public Rectangle rect;

    int fahrspur=0;

    public fahrzeug(){
        autobild=new Texture("pickuplr1.png");
        rect = new Rectangle();
        rect.width=autobild.getWidth();
        rect.height=autobild.getHeight();
        rect.x=0;
        rect.y=1080/2 - 110;
    }
}
