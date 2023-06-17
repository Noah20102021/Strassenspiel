package de.daemoniac.autosueberfahrendich;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.SourceDataLine;

public class Sounds {
    private static Sounds singletonMe;
    //defining the byte buffer
    private static final int BUFFER_SIZE = 4096;
    protected Sound klick;
    protected Sound strassenmusik;
    protected boolean strassenmusikSpielt;
    public static void initialisiereSounds(){
        String thePath = "Sounds/";
        if(singletonMe == null) {
            Gdx.app.debug("Sound-Iniitialisieren", "erstelle klasse für soundspeicher");
            singletonMe = new Sounds();
        }
        if(singletonMe.klick==null){
            Gdx.app.debug("Sound-Iniitialisieren", "lade klicksound in speicher");
            singletonMe.klick = Gdx.audio.newSound(Gdx.files.internal(thePath + "Klick.wav"));
        }
        if(singletonMe.strassenmusik==null){
            Gdx.app.debug("Sound-Iniitialisieren", "lade strassenmusik in speicher");
            singletonMe.strassenmusik = Gdx.audio.newSound(Gdx.files.internal(thePath + "Straßen-Geräusche.mp3"));
        }
    }
    public static void strassenmusikStart(settings_datei settings) {
        if(settings.sounds==1) {
            if(singletonMe==null){
                initialisiereSounds();
            }
            if(singletonMe!=null && singletonMe.strassenmusik!=null) {
                singletonMe.strassenmusik.loop();
                singletonMe.strassenmusikSpielt=true;
            }
        }
    }
    public static void strassenmusikStop(settings_datei settings) {
        if(settings.sounds==1) {
            if(singletonMe==null){
                initialisiereSounds();
            }
            if(singletonMe!=null && singletonMe.strassenmusik!=null && singletonMe.strassenmusikSpielt==true) {
                singletonMe.strassenmusikSpielt=false;
                singletonMe.strassenmusik.stop();
            }
        }
    }
    public static void klick(settings_datei settings) {
        if(settings.sounds==1) {
            if(singletonMe==null){
                initialisiereSounds();
            }
            if(singletonMe!=null && singletonMe.strassenmusik!=null) {
                singletonMe.klick.play();
            }
        }
    }

}