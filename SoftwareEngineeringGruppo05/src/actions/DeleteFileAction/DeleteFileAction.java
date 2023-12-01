package actions.DeleteFileAction;

import actions.Action;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class DeleteFileAction implements Action{
    private String filePath; //path del file da eliminare

    public DeleteFileAction(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            Path sourcePath = Paths.get(filePath); //ottiene un oggetto Path a partire dalla stringa
            Files.delete(sourcePath); //elimina il file con il Path indicato
        } catch (IOException ex) {
            Logger.getLogger(DeleteFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
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