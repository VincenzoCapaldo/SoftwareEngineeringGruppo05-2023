package model.actions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class MoveFileAction implements Action{
    
    private String filePath; //path del file da spostare
    private String newPath; //path della cartella di destinazione

    public MoveFileAction(String filePath, String newPath) {
        this.filePath = filePath;
        this.newPath = newPath;
    }

    @Override
    public void execute() {
        try {
            
            Path sourcePath = Paths.get(filePath);
            //ottiene un oggetto Path a partire dalla stringa           
            
            Path destinationPath = Paths.get(newPath, sourcePath.getFileName().toString());
            //il path di destinazione è dato dal path della cartella di destinazione unito al nome del file da spostare

            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            //sposta il file dal sourcePath al destinationPath con eventuale sovrascrittura
            
        } catch (IOException ex) {
            Logger.getLogger(CopyFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void add(Action a) {
         throw new UnsupportedOperationException("Cannot add an action to MoveFileAction.");
    }

    @Override
    public void remove(Action a) {
        throw new UnsupportedOperationException("Cannot remove an action from MoveFileAction.");
    }
    
}