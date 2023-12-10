/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.programTrigger;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ProgramTriggerUnix extends ProgramTrigger{

    public ProgramTriggerUnix(String programPath, String commandLine, int expectedExitValue) {
        super(programPath, commandLine, expectedExitValue);
    }

    @Override
    public void checkTrigger() {
        
        this.setVerified(false);
        setChanged();
        try {
            Process process = Runtime.getRuntime().exec("./" + this.getProgramPath() + " " + this.getCommandLine());
            int exitCode = process.waitFor();
            
            if(exitCode == this.getExpectedExitValue()){
                this.setVerified(true);
                setChanged();
                notifyObservers();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ProgramTriggerUnix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgramTriggerUnix.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
