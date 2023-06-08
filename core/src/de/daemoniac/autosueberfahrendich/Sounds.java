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

    public static void klick(settings_datei settings) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        if(settings.sounds==1) {

            String thePath = "Sounds/Klick.wav";
            if(singletonMe == null) {
                Gdx.app.debug("Sound", "erstelle klasse für soundspeicher");
                singletonMe = new Sounds();
            }
            if(singletonMe.klick==null){
                Gdx.app.debug("Sound", "lade klicksound in speicher");
                singletonMe.klick = Gdx.audio.newSound(Gdx.files.internal(thePath));
            }
            singletonMe.klick.play();

            Gdx.app.debug("Sound", "abgespielt");
        }

    }

}