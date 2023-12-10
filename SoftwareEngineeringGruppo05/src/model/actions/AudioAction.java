package model.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Paolo
 */
public class AudioAction implements Action {
    
    private String pathName; // path dell'audio
    
    public AudioAction(String pathName) {
        this.pathName = pathName;
    }
    
    @Override
    public void execute() {
        File file = new File(pathName); // Crea l'oggetto File a partire dal path
        Clip clip;
        try{
            clip = AudioSystem.getClip(); // Ottiene un Clip dalla Java Sound API
            InputStream is = new FileInputStream(file); // Crea un InputStream dal file audio
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(file); // Ottiene il formato e la lunghezza del file audio
            AudioInputStream ais = new AudioInputStream(is, aff.getFormat(), aff.getByteLength()); // Crea un AudioInputStream dal file audio
            clip.open(ais); // Apre il Clip con l'AudioInputStream
        }catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            throw new RuntimeException();
        }
        clip.start(); // Fa partire l'audio
    }
    
    @Override
    public String toString(){
        return "Audio";
    }
    
    @Override
    public void add(Action a){
        throw new UnsupportedOperationException("Cannot add an action to AudioAction.");
    }
    
    @Override
    public void remove(Action a){
        throw new UnsupportedOperationException("Cannot remove an action from AudioAction.");
    }
   
}