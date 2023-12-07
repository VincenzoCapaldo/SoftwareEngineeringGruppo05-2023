package triggers.FileTrigger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTrigger extends Observable implements Trigger{
    
    private String directoryPath;
    private String filePath;
    private boolean verified;
    
    public FileTrigger(String directoryPath, String filePath){
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }
    
    @Override
    public boolean isVerified() {
        return verified;
    }

    @Override
    public void checkTrigger() {
        
        Path sourcePath = Paths.get(directoryPath +  "/" + filePath);
        
        if(Files.exists(sourcePath)){
            verified = true;
            setChanged();
            notifyObservers();
        }else{
            verified = false;
            setChanged();
            notifyObservers();
            while (!verified) {
                if (Files.exists(sourcePath)) {
                    verified = true;
                    setChanged();
                    notifyObservers();
                }
            }
        }
        
    }

}