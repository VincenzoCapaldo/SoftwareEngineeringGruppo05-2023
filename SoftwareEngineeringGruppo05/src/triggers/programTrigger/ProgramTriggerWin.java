package triggers.programTrigger;

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
        
        String command = "cmd /c ";
        
        if(this.getProgramPath().contains(".jar")){
            command += "java -jar ";
        }
        
        try {
            Process process = Runtime.getRuntime().exec(command + this.getProgramPath() + " " + this.getCommandLine());
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
