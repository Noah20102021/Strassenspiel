package de.daemoniac.autosueberfahrendich;

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

    //defining the byte buffer
    private static final int BUFFER_SIZE = 4096;
    Sound Soundobject;
    void play(String filePath) {


            Soundobject = Gdx.audio.newSound(Gdx.files.internal(filePath));
            Soundobject.play();

    }

    public static void klick() {


        String thePath =  "Sounds/Klick.wav";
        Sounds player = new Sounds();
        player.play(thePath);

    }

}