package actions.MessageAction;

import actions.Action;
import java.util.Observable;

/**
 *
 * @author maria
 */
public class MessageAction extends Observable implements Action {
    private String message;
    private boolean condition;

    public MessageAction(String message) {
        this.message = message;
        condition = false;
    }

    public String getMessage() {
        return message;
    }

    public boolean getCondition() {
        return condition;
    }

    public void setCondition() {

        condition = !condition;
        setChanged(); 
        notifyObservers(); 
    }
    
    @Override
    public void execute(){
        setCondition();
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
