package actions.writeFileAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import actions.Action;

/**
 *
 * @author Paolo
 */
public class WriteFileAction implements Action{
    
    private String filePath; //path del file su cui scrivere
    private String message; //messaggio da scrivere alla fine del file

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
            throw new RuntimeException();
        }
    }
    
    @Override
    public String toString(){
        return "WriteFile";
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