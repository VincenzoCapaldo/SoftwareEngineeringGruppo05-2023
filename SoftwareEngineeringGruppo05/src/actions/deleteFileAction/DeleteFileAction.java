package actions.deleteFileAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import actions.Action;

/**
 *
 * @author maria
 */
public class DeleteFileAction implements Action{
    
    private String directoryPath; //directory del file da eliminare
    private String nameFile; //nome del file da eliminare
    
    public DeleteFileAction(String directoryPath, String namePath) {
        this.directoryPath = directoryPath;
        this.nameFile = namePath;
    }

    @Override
    public void execute() {
        try {
            
            Path sourcePath = Paths.get(directoryPath +  "/" + nameFile);
            //ottiene un oggetto Path a partire dalla stringa
            
            Files.delete(sourcePath);
            //elimina il file con il Path indicato
        
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString(){
        return "DeleteFile";
    }
    
    @Override
    public void add(Action a) {
        throw new UnsupportedOperationException("Cannot add an action to DeleteFileAction.");
    }

    @Override
    public void remove(Action a) {
        throw new UnsupportedOperationException("Cannot remove an action from DeleteFileAction.");
    }
    
}