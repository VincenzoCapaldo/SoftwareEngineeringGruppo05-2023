package triggers.FileSizeTrigger;

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
    
    private String filePath;
    private long size; 
    private boolean verified;
    
    public FileSizeTrigger(String filePath, long size){
        this.filePath = filePath;
        this.size = size;
    }
    
    @Override
    public boolean isVerified() {
        return verified;
    }

    @Override
    public void checkTrigger() {
        
        Path sourcePath = Paths.get(filePath);
               
        verified = false;
        
        while (!verified) {
            try {
                if(Files.size(sourcePath) > size){
                    verified = true;
                    setChanged();
                    notifyObservers();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileSizeTrigger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}