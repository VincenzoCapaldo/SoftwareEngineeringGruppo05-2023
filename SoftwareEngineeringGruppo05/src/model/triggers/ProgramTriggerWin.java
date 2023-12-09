/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.triggers;

import model.actions.ProgramActionWin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ProgramTriggerWin extends ProgramTrigger{

    public ProgramTriggerWin(String programPath, String commandLine, int expectedExitValue) {
        super(programPath, commandLine, expectedExitValue);
    }

    @Override
    public void checkTrigger() {
        
        this.setVerified(false);
        setChanged();
        
        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + this.getProgramPath() + " " + this.getCommandLine());
            int exitCode = process.waitFor();
            
            if(exitCode == this.getExpectedExitValue()){
                this.setVerified(true);
                setChanged();
                notifyObservers();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ProgramTriggerWin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgramTriggerWin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
