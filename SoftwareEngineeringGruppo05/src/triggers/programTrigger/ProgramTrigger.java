/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triggers.programTrigger;

import java.util.Observable;
import triggers.Trigger;
import triggers.Trigger;

/**
 *
 * @author Luca
 */
public abstract class ProgramTrigger extends Observable implements Trigger{
    
    private String programPath;
    private String commandLine;
    private boolean verified;
    private int expectedExitValue;

    public ProgramTrigger(String programPath, String commandLine, int expectedExitValue) {
        this.programPath = programPath;
        this.commandLine = commandLine;
        this.expectedExitValue = expectedExitValue;
    }

    public String getProgramPath() {
        return programPath;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public int getExpectedExitValue() {
        return expectedExitValue;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    
    @Override
    public boolean isVerified() {
        return this.verified;
    }

    @Override
    public boolean isRepeated() {
        return false;
    }

    @Override
    public abstract void checkTrigger();
    
     @Override
    public String toString(){
        return "Program";
    }
    
    
}
