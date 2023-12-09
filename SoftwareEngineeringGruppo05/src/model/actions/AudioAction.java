package model.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Clip clip;
    
    /* Costruttore che accetta il percorso del file audio come stringa */
    public AudioAction(String pathName) {
        this(new File(pathName));
    }
    
    /* Costruttore che accetta un oggetto File rappresentante il file audio */
    public AudioAction(File file) {
        try{
            clip = AudioSystem.getClip(); // Ottiene un Clip dalla Java Sound API
            InputStream is = new FileInputStream(file); // Crea un InputStream dal file audio
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(file); // Ottiene il formato e la lunghezza del file audio
            AudioInputStream ais = new AudioInputStream(is, aff.getFormat(), aff.getByteLength()); // Crea un AudioInputStream dal file audio
            clip.open(ais); // Apre il Clip con l'AudioInputStream
        }catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(AudioAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void execute() {
        clip.start();
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