package actions.ProgramAction;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import actions.Action;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ProgramAction implements Action{
    
    private String programPath;
    private String commandLine;

    public ProgramAction(String programPath, String commandLine) {
        this.programPath = programPath;
        this.commandLine = commandLine;
    }

    @Override
    public void execute() {
        try{
            // Crea un processo per eseguire il programma specificato nel percorso
            ProcessBuilder processBuilder = new ProcessBuilder(programPath, commandLine);
            
            // Avvia il processo e attendi che termini
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ProgramAction.class.getName()).log(Level.SEVERE, null, ex);
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
