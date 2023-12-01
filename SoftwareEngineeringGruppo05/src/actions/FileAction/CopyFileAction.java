/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions.FileAction;

import actions.Action;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.stage.FileChooser;

/**
 *
 * @author maria
 */
public class CopyFileAction implements Action{
    private String filePath;
    private String newPath;

    public CopyFileAction(String filePath, String newPath) {
        this.filePath = filePath;
        this.newPath = newPath;
    }

    @Override
    public void execute() {
        try {
            Path sourcePath = Paths.get(filePath);
            Path destinationPath = Paths.get(newPath, sourcePath.getFileName().toString());

            // Copia il file con sovrascrittura
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void add(Action a) {
         throw new UnsupportedOperationException("Cannot add an action to CopyFileAction.");
    }

    @Override
    public void remove(Action a) {
        throw new UnsupportedOperationException("Cannot remove an action from CopyFileAction.");
    }
    
}
