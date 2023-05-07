package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class fahrzeug {
    //jedes objekt das wir über libgdx darstellen wollen besteht aus 2 elementen.
    //das eine ist die texture, also das bild das dargestellt werden soll. das rectangle
    // selbst ist nicht sichtbar, sondern beschreibt die position des objektes. mit hilfe des rectangles
    //können wir berechnungen anstellen wie z.b. entfernung zu anderen objekten, kollisionsüberprüfungen
    //oder einfach nur eine einfache und bequeme art und weise den ort zu verändern wo die texture
    //dargestellt werden soll
    public Texture autobild;
    public Rectangle rect;

    int fahrspur=0;
    String fahtrichtung;
    public fahrzeug(int farzeugx, int farzeugy, String farzeugrichtung){
        fahtrichtung=farzeugrichtung;
        //momentan passiert hier noch nicht viel. beim erstellen einer neuen instanz der klasse wird die texture geladen
        //man könnte hier jetzt einen zufallsgenerator einbauen und in abhängigkeit des ergebnisses unterschiedliche texturen
        //darstellen. dann würden unterschiedliche autos da lang fahren
        autobild=new Texture("pickup1" +farzeugrichtung+".png");
        //neue positionsbeschreibung erstellen
        rect = new Rectangle();
        //wir haben festgestellt, dass das auto zu groß für die fahrspur ist, auf ein drittel der autogröße geschrumpft
        //sieht das besser aus. allerdings wissen wir nicht, ob andere autobilder ebenfalls genau so groß sind.
        //hier wäre also eine größenberechnung der zur verfügung stehenden fahrspur sinnvoller um unabhängig von der
        //autobildgröße verschiedene autotexturen laden zu können
        rect.width=autobild.getWidth()/3;
        rect.height=autobild.getHeight()/3;
        //aktuell starten alle autos links am rand auf immer der gleichen höhe. wenn wir irgendwann unterschiedliche
        //fahrspuren implementieren muss anstelle der aktuellen werte die fahrspur ermittelt werden und dann
        //die startwerte der fahrspur eingetragen werden
        rect.x=farzeugx;
        rect.y=farzeugy;
    }
}
