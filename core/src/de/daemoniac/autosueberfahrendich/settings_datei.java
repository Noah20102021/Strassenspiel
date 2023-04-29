package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;

public class settings_datei {
    //alles was irgendwie gespeichert werden soll, sollte hier in dieser klasse drin stehen oder
    //von dieser klasse aus erreichbar sein. damit behält man gut den überblick was beim laden/speichern
    // alles berücksichtigt werden muss. das dient also in erster linie der einfachheit für uns
    public int sounds = 1;
    public void Laden(){
        //zuerst überprüfen ob wir auf den lokalen speicher zugreifen können.
        boolean isLocalAvailable = Gdx.files.isLocalStorageAvailable();
        if(isLocalAvailable){
            //laden macht nur sinn wenn wir die datei finden in der gespeichert wurde :-)
            //Gdx.files.local sucht bei computern die datei in dem verzeichnis in dem auch die spieldateien liegen
            //bei smartphones ist jeder app ein geschütztes verzeichnis zugeordnet auf das nur die app zugreifen kann
            if(Gdx.files.local("settings.yml").exists()) {
                FileHandle datei = Gdx.files.local("settings.yml");
                //mit readstring lesen wir die gesamte datei als text ein. diesen text können wir dann mit split
                //in handlichere teile aufspalten. es ist sinnvoll sich irgendein zeichen auszudenken
                //mit dem man einzelne werte voneinander trennen kann. hier habe ich ein "," genommen. man könnte aber auch
                // "/" oder ";" nehmen.
                String lesestring=datei.readString();
                String[] splitstring=lesestring.split(",");
                //wir haben jetzt eine liste an einzelnen informationen. aktuell speichern wir nur die münzanzahl
                //dh in dieser liste wird nur ein einziges element sein. und das wird lauten "münzen:xxxx"
                for(String zeile:splitstring){


                }
            }
        }
    }

    public void Speichern(){
        //zuerst überprüfen ob wir auf den lokalen speicher zugreifen können.
        boolean isLocalAvailable = Gdx.files.isLocalStorageAvailable();
        if(isLocalAvailable){
            //laden macht nur sinn wenn wir die datei finden in der gespeichert wurde :-)
            //Gdx.files.local sucht bei computern die datei in dem verzeichnis in dem auch die spieldateien liegen
            //bei smartphones ist jeder app ein geschütztes verzeichnis zugeordnet auf das nur die app zugreifen kann
            FileHandle datei=Gdx.files.local("settings.yml");
            //wir stellen zuerst einen text zusammen den wir abspeichern wollen.
            //in diesem fall wird in der datei dann stehen: "münzen:xxxx,"
            //das "," schreiben wir rein um später einzelne werte voneinander trennen zu können
            //das "\n" ist ein zeilenwechsel. dh, nach dem komma soll dann eine neue zeile beginnen
            //das dient lediglich der menschlichen lesbarkeit damit wir, solange wir noch entwickeln
            //uns die datei dann auch anschauen können und überprüfen können was wo drin steht
            datei.writeString("Sounds: " + sounds + ",\n",false);


        }
    }

}
