/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actions.ProgramAction;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ProgramActionWin extends ProgramAction{

    public ProgramActionWin(String programPath, String commandLine) {
        super(programPath, commandLine);
    }

    @Override
    public void execute() {
        
        try {
            Runtime.getRuntime().exec("cmd /c start " + this.getProgramPath() + " " + this.getCommandLine());
        } catch (IOException ex) {
            Logger.getLogger(ProgramActionWin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
