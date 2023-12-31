package actions.programAction;

import actions.Action;

/**
 *
 * @author Luca
 */
public abstract class ProgramAction implements Action{
    
    private String programPath; 
    private String commandLine;

    public ProgramAction(String programPath, String commandLine) {
        this.programPath = programPath;
        this.commandLine = commandLine;
    }

    public String getProgramPath() {
        return programPath;
    }

    public String getCommandLine() {
        return commandLine;
    }
    
    @Override
    public abstract void execute();

     @Override
    public void add(Action a) {
         throw new UnsupportedOperationException("Cannot add an action to ProgramAction.");
    }

    @Override
    public void remove(Action a) {
        throw new UnsupportedOperationException("Cannot remove an action from ProgramAction.");
    }
    
    @Override
    public String toString(){
        return "Program";
    }

}