package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.logging.FileHandler;

public class Spielstand {
    public Integer münzen=0;

    public void Laden(){
        boolean isLocalAvailable = Gdx.files.isLocalStorageAvailable();
        if(isLocalAvailable){
            if(Gdx.files.local("speicherstand.yml").exists()) {
                FileHandle datei = Gdx.files.local("speicherstand.yml");
                String lesestring=datei.readString();
                String[] splitstring=lesestring.split(",");
                for(String zeile:splitstring){
                    if(zeile.startsWith("münzen:")){
                        münzen=Integer.parseInt(zeile.split(":")[1]);
                    }
                }
            }
        }
    }

    public void Speichern(){
        boolean isLocalAvailable = Gdx.files.isLocalStorageAvailable();
        if(isLocalAvailable){
            FileHandle datei=Gdx.files.local("speicherstand.yml");
            datei.writeString("münzen:" + münzen + ",\n",false);

        }
    }

}
