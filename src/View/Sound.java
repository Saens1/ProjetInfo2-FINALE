package View;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {                   //permet de lancer des sons 
    private Clip audioClip;
    public Sound(File audioFile){

        AudioInputStream audioStream;
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

        } catch (IOException e){
            e.printStackTrace();
        } catch ( LineUnavailableException e){   //Si une ligne du fichier est déjà  en lecture par une autre application.
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {  //Si les données ne sont pas compatibles.
            e.printStackTrace();
        }
    }

    public void playSound(){
        audioClip.start();
    }
    public void couperMusique(){
        audioClip.stop();
    }
    public void close(){ audioClip.close();}
    public void playLoop(){audioClip.loop(100);}

}