package model.actions;

import java.util.Observable;

/**
 *
 * @author maria
 */
public class MessageAction extends Observable implements Action {
    
    private String message; //messaggio da visualizzare nel pop up

    public MessageAction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute(){
        //notifica il Controller per fargli visualizzare il pop up
        setChanged(); 
        notifyObservers();
    }
             
    @Override
    public String toString(){
        return "Message";
    }
    
    @Override
    public void add(Action a){
         throw new UnsupportedOperationException("Cannot add an action to MessageAction.");
    }
    
    @Override
    public void remove(Action a){
        throw new UnsupportedOperationException("Cannot remove an action from MessageAction.");
    }
    
}
