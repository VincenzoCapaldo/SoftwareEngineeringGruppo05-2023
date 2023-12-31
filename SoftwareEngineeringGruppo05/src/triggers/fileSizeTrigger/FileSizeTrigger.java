package triggers.fileSizeTrigger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileSizeTrigger extends Observable implements Trigger{
    
    private String filePath; //file che fa scattare il trigger
    private long size; //dimensione del file che fa scattare il trigger
    private boolean verified;
    
    public FileSizeTrigger(String filePath, long size){
        this.filePath = filePath;
        this.size = size;
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
        
        Path sourcePath = Paths.get(filePath);
               
        verified = false;
        setChanged();
            
        while (!verified) { //controlla finchè non è verificato
            try {
                if(Files.size(sourcePath) > size){ //se la dimensione del file è maggiore di quella specificata
                    verified = true;
                    setChanged();
                    notifyObservers();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileSizeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

     @Override
    public String toString(){
        return "FileSize";
    }
}