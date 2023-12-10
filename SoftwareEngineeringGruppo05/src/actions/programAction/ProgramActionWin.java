package actions.programAction;

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
        
        String command = "cmd /c start "; //comando per avviare un programma in Windows
        
        if(this.getProgramPath().contains(".jar")){
            command += "java -jar "; //comando per gli eseguibili .jar
        }
        
        try {
            Runtime.getRuntime().exec(command + this.getProgramPath() + " " + this.getCommandLine()); //esegue il comando
        } catch (IOException ex) {
            Logger.getLogger(ProgramActionWin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
