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
        
        String command = "xdg-open "; //comando per avviare un programma in Unix-like (Linux, macOS, ecc.)
        
        if(this.getProgramPath().contains(".jar")){
            command = "java -jar "; //comando per gli eseguibili .jar
        }
        
        try {
            Runtime.getRuntime().exec(command + this.getProgramPath() + " " + this.getCommandLine()); //esegue il comando
        } catch (IOException ex) {
            Logger.getLogger(ProgramActionWin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}