package triggers.fileTrigger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTrigger extends Observable implements Trigger{
    
    private String directoryPath; //cartella del file che fa scattare il trigger
    private String filePath; //file che fa scattare il trigger
    private boolean verified;
    
    public FileTrigger(String directoryPath, String filePath){
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }
    
    @Override
    public boolean isVerified() {
        return this.verified;
    }

    @Override
    public boolean isRepeated() {
        return false;
    }

    @Override
    public void checkTrigger() {
        
        Path sourcePath = Paths.get(directoryPath +  "/" + filePath);
        
        verified = false;
        setChanged();
            
        while (!verified) { //controlla finchè non è verificato
            if (Files.exists(sourcePath)) { //se il file esiste
                verified = true;
                setChanged();
                notifyObservers();
            }
        }
        
    }
    
     @Override
    public String toString(){
        return "File";
    }

}