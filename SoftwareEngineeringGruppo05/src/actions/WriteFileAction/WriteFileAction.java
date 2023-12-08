package actions.WriteFileAction;

import actions.Action;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class WriteFileAction implements Action{
    public String filePath;
    public String message;

    public WriteFileAction(String filePath, String message) {
        this.filePath = filePath;
        this.message = message;
    }

    @Override
    public void execute() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
        //il parametro true imposterà la modalità di apertura del file su "append", consentendo di scrivere alla fine del file anziché sovrascriverlo.
            pw.append(message);
        } catch (IOException ex) {
            Logger.getLogger(WriteFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void add(Action a){
         throw new UnsupportedOperationException("Cannot add an action to WriterAction.");
    }
    
    @Override
    public void remove(Action a){
        throw new UnsupportedOperationException("Cannot remove an action from WriterAction.");
    }
    
}