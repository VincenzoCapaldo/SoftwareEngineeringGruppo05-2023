package triggers.FileTrigger;

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
    public boolean isRepeated() {
        return false;
    }

    @Override
    public void checkTrigger() {
        
        Path sourcePath = Paths.get(directoryPath +  "/" + filePath);
        
        verified = false;
        
        while (!verified) {
            if (Files.exists(sourcePath)) {
                verified = true;
                setChanged();
                notifyObservers();
            }
        }
        
    }

}