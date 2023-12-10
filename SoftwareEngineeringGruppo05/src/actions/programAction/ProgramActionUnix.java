package actions.programAction;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luca
 */
public class ProgramActionUnix extends ProgramAction{

    public ProgramActionUnix(String programPath, String commandLine) {
        super(programPath, commandLine);
    }

    @Override
    public void execute() {
        
        String command = "xdg-open ";
        
        if(this.getProgramPath().contains(".jar")){
            command = "java -jar ";
        }
        
        try {
            Runtime.getRuntime().exec(command + this.getProgramPath() + " " + this.getCommandLine());
        } catch (IOException ex) {
            Logger.getLogger(ProgramActionWin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
