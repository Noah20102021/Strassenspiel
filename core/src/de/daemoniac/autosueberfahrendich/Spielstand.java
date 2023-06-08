package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.logging.FileHandler;

public class Spielstand {
    //alles was irgendwie gespeichert werden soll, sollte hier in dieser klasse drin stehen oder
    //von dieser klasse aus erreichbar sein. damit behält man gut den überblick was beim laden/speichern
    // alles berücksichtigt werden muss. das dient also in erster linie der einfachheit für uns
    public Integer muenzen=1;
    public Integer level=1;
    public Integer RGBmuenzen = 0;
    public Integer leben=5;
    public void Laden(){
        //zuerst überprüfen ob wir auf den lokalen speicher zugreifen können.
        boolean isLocalAvailable = Gdx.files.isLocalStorageAvailable();
        if(isLocalAvailable){
            //laden macht nur sinn wenn wir die datei finden in der gespeichert wurde :-)
            //Gdx.files.local sucht bei computern die datei in dem verzeichnis in dem auch die spieldateien liegen
            //bei smartphones ist jeder app ein geschütztes verzeichnis zugeordnet auf das nur die app zugreifen kann
            if(Gdx.files.local("speicherstand.yml").exists()) {
                FileHandle datei = Gdx.files.local("speicherstand.yml");
                //mit readstring lesen wir die gesamte datei als text ein. diesen text können wir dann mit split
                //in handlichere teile aufspalten. es ist sinnvoll sich irgendein zeichen auszudenken
                //mit dem man einzelne werte voneinander trennen kann. hier habe ich ein "," genommen. man könnte aber auch
                // "/" oder ";" nehmen.
                String lesestring=datei.readString();;
                String[] splitstring = lesestring.split(",");
                //wir haben jetzt eine liste an einzelnen informationen. aktuell speichern wir nur die münzanzahl
                //dh in dieser liste wird nur ein einziges element sein. und das wird lauten "münzen:xxxx"
                for(String zeile:splitstring){
                    if(zeile.contains(":")) {
                        String[] eintrag = zeile.split(":");
                        switch (eintrag[0]) {
                            case "muenzen":
                                muenzen = Integer.parseInt(eintrag[1]);
                                break;
                            case "level":
                                level = Integer.parseInt(eintrag[1]);
                                break;
                            case "leben":
                                leben = Integer.parseInt(eintrag[1]);
                                break;
                            case "RGB":
                                RGBmuenzen = Integer.parseInt(eintrag[1]);
                                break;
                            case "Lebenszeit":
                                try {
                                    lebenszeit = new SimpleDateFormat("yyyy-MM-dd hh/mm/ss").parse((eintrag[1]));
                                } catch (ParseException e) {

                                }
                                break;
                        }//end switch
                    }//ende if contains :
                }//ende forschleife über alle einträge


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
            FileHandle datei=Gdx.files.local("speicherstand.yml");
            //wir stellen zuerst einen text zusammen den wir abspeichern wollen.
            //in diesem fall wird in der datei dann stehen: "münzen:xxxx,"
            //das "," schreiben wir rein um später einzelne werte voneinander trennen zu können
            //das "\n" ist ein zeilenwechsel. dh, nach dem komma soll dann eine neue zeile beginnen
            //das dient lediglich der menschlichen lesbarkeit damit wir, solange wir noch entwickeln
            //uns die datei dann auch anschauen können und überprüfen können was wo drin steht
            datei.writeString("muenzen:" + muenzen + "," + "level:" + level + "," + "leben:" + leben + "," + "RGB:" + RGBmuenzen + ",",false);
           // datei.writeString("level:" + level + ",\n",false);

        }
    }

}
