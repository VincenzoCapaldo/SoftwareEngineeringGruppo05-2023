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

/**
 *
 * @author maria
 */
public class DeleteFileAction implements Action{
    private String filePath;

    public DeleteFileAction(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            Path sourcePath = Paths.get(filePath);
            Files.delete(sourcePath);
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
