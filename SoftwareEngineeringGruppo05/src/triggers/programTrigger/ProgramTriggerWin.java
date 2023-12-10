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
        
        String command = "cmd /c ";  //comando per eseguire un programma in Windows
        
        if(this.getProgramPath().contains(".jar")){
            command += "java -jar "; //comando per gli eseguibili .jar
        }
        
        try {
            Process process = Runtime.getRuntime().exec(command + this.getProgramPath() + " " + this.getCommandLine()); //esegue il comando
            int exitCode = process.waitFor(); //attende la terminazione del programma
            
            //controlla se l'exit code è uguale a quello atteso
            if(exitCode == this.getExpectedExitValue()){
                this.setVerified(true);
                setChanged();
                notifyObservers();
            }
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ProgramTriggerWin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
